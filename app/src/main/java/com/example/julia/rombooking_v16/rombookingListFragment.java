package com.example.julia.rombooking_v16;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class rombookingListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private romListInterface parentActivity;
    private CustomAdapter myCustomAdapter;
    private ArrayList<Rom> romList;

    public rombookingListFragment() {
        //Mandatory empty constructor
    }

    @SuppressWarnings("unused")
    public interface romListInterface {
        void reserverRom(String rom_kode);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initListViewAndRefreshLayout (String bruker_kode, String sessionkey, String fra, String til) {
        romList = new ArrayList<>();

        myCustomAdapter = new CustomAdapter((Context)parentActivity, R.layout.rombooking_list_item, romList);

        ListView myListView = (ListView) getActivity().findViewById(R.id.lv_rombooking_list);
        myListView.setAdapter(myCustomAdapter);
        myListView.setOnItemClickListener(this);

        new RomListTask().execute(bruker_kode, sessionkey, fra, til);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        parentActivity.reserverRom(romList.get(position).getRom_kode());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            parentActivity = (romListInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " The activity must implement" +
                    " rombookingListFragment.romListInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rombooking_list_fragment, container, false);
    }

    public class CustomAdapter extends ArrayAdapter<Rom> {
        private int res;
        private Context context;

        public CustomAdapter(Context context, int resource, List<Rom> items) {
            super(context, resource, items);
            this.res = resource;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout romView;
            Rom rom = getItem(position);

            //Re-use of view
            if (convertView == null) {
                romView = new LinearLayout(context);
                LayoutInflater infl = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                infl.inflate(res, romView, true);
            } else {
                romView = (LinearLayout)convertView;
            }

            ((TextView) romView.findViewById(R.id.column_romkode)).setText(rom.getRom_kode());
            ((TextView) romView.findViewById(R.id.column_alias)).setText(rom.getRom_navn());
            ((TextView) romView.findViewById(R.id.column_type)).setText(rom.getRom_type_kode());
            ((TextView) romView.findViewById(R.id.column_kapasitet)).setText(rom.getKapasitet_und());

            return romView;
        }
    }

    private class RomListTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            //Params[0] = Bruker_kode
            //Params[1] = Sessionkey
            //Params[2] = Fra
            //Params[3] = Til

            String link = "https://android-rombooking-mbruksaas.c9users.io/getRoom.php";

            HttpURLConnection conn = null;
            StringBuilder serverResponse = new StringBuilder();

            try {
                URL url = new URL(link);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                BufferedWriter wr = new BufferedWriter(
                        new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                wr.write("?bruker_kode=" + params[0] + "&sessionkey=" + params[1]
                        + "&fra=" + params[2] + "&til=" + params[3]);
                wr.flush();
                wr.close();

                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String line;
                    try {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));

                        while ((line = br.readLine()) != null)
                            serverResponse.append(line);

                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn!=null)
                    conn.disconnect();
            }

            Type type = new TypeToken<ArrayList<Rom>>(){}.getType();
            ArrayList<Rom> dlList = new Gson().fromJson(String.valueOf(serverResponse), type);

            //Add() must be called for the ListView to update
            for(Rom r : dlList)
                    romList.add(r);

            return null;
        }

        @Override
        protected void onPostExecute(String reading) {
            myCustomAdapter.notifyDataSetChanged();
        }
    }
}
