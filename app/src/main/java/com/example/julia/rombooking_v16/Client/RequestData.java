package com.example.julia.rombooking_v16.Client;

import android.content.Context;

/**
 * Created by sitha on 28.05.2016.
 */
public class RequestData {

    /*
    public void connectToDatabase (String username, String password) {

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name", username));

        RequestTask task = new RequestTask();
        task.execute(username);
    }
    */

    public void registerUser(String fornavn, String etternavn, String epost, String passord,
                             String bruker_kode, Context context) {
        String urlString = "/registerUser.php?fornavn=" + fornavn + "&etternavn=" + etternavn + "&epost=" + epost
                + "&passord=" + passord + "&bruker_kode=" + bruker_kode;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        new RequestTask().execute(contextUrl);
    }

    public void login(String email, String passord, Context context) {
        String urlString = "/login.php?epost=" + email + "&passord= " + passord;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        new RequestTask().execute(contextUrl);
    }

    public void getRoom(String sessionKey, String brukerkode, Context context) {
        String urlString = "/getRoom.php?sessionkey=" + sessionKey + "&bruker_kode=" + brukerkode;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        GetRoomAsyncTask getroom = new GetRoomAsyncTask();
        getroom.execute(contextUrl);
    }

    public void getAvailableRooms(String sessionKey, String brukerkode, String fra,
                                  String til, Context context) {
        String urlString = "/getRoom.php?sessionkey=" + sessionKey + "&bruker_kode=" + brukerkode
                + "&fra=" + fra + "&til=" + til;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        GetRoomAsyncTask getroom = new GetRoomAsyncTask();
        getroom.execute(contextUrl);
    }

    public void getReservation(String sessionKey, String brukerkode, int reservasjonsId, Context context) {
        String urlString = "/getReservation.php?sessionkey=" + sessionKey + "&bruker_kode=" + brukerkode
                + "&reservasjons_id=" + reservasjonsId;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        GetReservationAsyncTask getReservation = new GetReservationAsyncTask();
        getReservation.execute(contextUrl);
    }

    public void getUser(String sessionKey, String brukerkode, Context context) {
        String urlString = "/getUser.php?sessionkey=" + sessionKey + "&bruker_kode=" + brukerkode;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        GetUserAsyncTask getUser = new GetUserAsyncTask();
        getUser.execute(contextUrl);
    }

    public void addReservation(String sessionKey, String brukerkode, String fra, String til,
                               String romkode, int gruppekode, String gruppeleder, String formal, Context context) {
        String urlString = "/addReservation.php?sessionkey=" + sessionKey + "&bruker_kode=" + brukerkode
                + "&fra=" + fra + "&til=" + til + "&rom_kode=" + romkode + "&gruppe_kode=" + gruppekode
                + "&gruppeleder=" + gruppeleder + "&formal=" + formal;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        new RequestTask().execute(contextUrl);
    }

    // TODO: Lage addGroup og editGroup' 

    public void editReservation(String sessionKey, String brukerkode, String fra, String til,
                                String formal, String reservasjonsId, Context context) {
        String urlString = "/editReservation.php?sessionkey=" + sessionKey + "&bruker_kode=" + brukerkode
                + "&fra=" + fra + "&til=" + til + "&formal=" + formal + "&reservasjons_id=" + reservasjonsId;
        ContextUrl contextUrl = new ContextUrl(context, urlString);
        EditReservationAsyncTask editReservation = new EditReservationAsyncTask();
        editReservation.execute(contextUrl);
    }
}