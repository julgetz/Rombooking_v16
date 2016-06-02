package com.example.julia.rombooking_v16;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class rombookingListFragment extends Fragment {

    private romListInterface parentActivity;
    private CustomRomAdapter myCustomAdapter;
    private ArrayList<Rom> romList;

    public rombookingListFragment() {
        //Mandatory empty constructor
    }

    @SuppressWarnings("unused")
    public interface romListInterface {
        void reserverRom(String rom_kode);
    }

    public void initListViewAndRefreshLayout(String bruker_kode, String sessionkey, String fra, String til) {
        romList = new ArrayList<>();

        myCustomAdapter = new CustomRomAdapter((Context) parentActivity, romList);

        ListView myListView = (ListView) getActivity().findViewById(R.id.lv_rombooking_list);
        myListView.setAdapter(myCustomAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentActivity.reserverRom(romList.get(position).getRom_kode());
            }
        });

        new RomListTask().execute(bruker_kode, sessionkey, fra, til);
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 100, 100, 500};
        v.vibrate(pattern, -1);
    }

    private void updateList(String rooms) {

        romList.clear();

        if (rooms.equals("-3")) {

            Toast.makeText(getActivity().getApplicationContext(),
                    getString(R.string.general_nettverksfeil), Toast.LENGTH_LONG).show();
            vibrate();

        } else if(!rooms.equals("Error: No rooms available in provided timeframe")) {

            Type type = new TypeToken<ArrayList<Rom>>() {
            }.getType();
            ArrayList<Rom> dlList = new Gson().fromJson(rooms, type);

            //Add() must be called for the ListView to update
            for (Rom r : dlList)
                romList.add(r);
        }
        myCustomAdapter.notifyDataSetChanged();
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

    public class CustomRomAdapter extends ArrayAdapter<Rom> {
        private final int res;
        private final Context context;

        public CustomRomAdapter(Context context, List<Rom> items) {
            super(context, R.layout.rombooking_list_item, items);
            this.res = R.layout.rombooking_list_item;
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
                romView = (LinearLayout) convertView;
            }

            ((TextView) romView.findViewById(R.id.column_romkode)).setText(rom.getRom_kode());
            ((TextView) romView.findViewById(R.id.column_alias)).setText(rom.getRom_navn());
            ((TextView) romView.findViewById(R.id.column_type)).setText(rom.getRom_type_kode());
            ((TextView) romView.findViewById(R.id.column_kapasitet)).setText(String.valueOf(rom.getKapasitet_und()));

            return romView;
        }
    }

    private class RomListTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            //Params[0] = Epost
            //Params[1] = Sessionkey
            //Params[2] = Fra
            //Params[3] = Til

            String link = "https://android-rombooking-mbruksaas.c9users.io/getRoom.php";
            String paramString = "?epost=" + params[0] + "&sessionkey=" + params[1]
                    + "&fra=" + params[2] + "&til=" + params[3];

            HttpURLConnection conn = null;
            StringBuilder serverResponse = new StringBuilder();

            try {
                URL url = new URL(link + paramString);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

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
                } else {
                    return "-3";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    conn.disconnect();
            }
            return String.valueOf(serverResponse);
        }

        @Override
        protected void onPostExecute(String reading) {
            updateList(reading);
        }
    }
}
