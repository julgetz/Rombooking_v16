package com.example.julia.rombooking_v16.Client;

import android.net.Uri;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sitha on 28.05.2016.
 */
public class RequestData {

    public void ConnectToDatabase (String username, String password) {

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name", username));

        RequestTask task = new RequestTask();
        task.execute(username);
    }

    public void RegisterUser(String fornavn, String etternavn, String epost, String passord, String bruker_kode) {
        String urlString = "/registerUser.php?fornavn=" + fornavn + "&etternavn=" + etternavn + "&epost=" + epost
                + "&passord=" + passord + "&bruker_kode=" + bruker_kode;
        RequestTask registerUserTask = new RequestTask();
        registerUserTask.execute(urlString);
    }

    public void Login(String email, String passord) {
        String urlString = "/login.php?epost=" + email + "&passord= " + passord;
        LoginAyncTask loginTask = new LoginAyncTask();
        loginTask.execute(urlString);
    }

    public void GetRoom(String sessionKey, String brukerkode) {
        String urlString = "/getRoom.php?sessionkey=" + sessionKey + "&bruker_kode=" + brukerkode;
        GetRoomAsyncTask getroom = new GetRoomAsyncTask();
        getroom.execute(urlString);

    }

    public void GetReservation(String sessionKey, String brukerkode, int reservasjonsId) {
        String urlString = "/getReservation.php?sessionkey=" + sessionKey + "&bruker_kode=" + brukerkode
                + "&reservasjons_id=" + reservasjonsId;
        GetReservationAsyncTask getReservation = new GetReservationAsyncTask();
        getReservation.execute(urlString);
    }

    public void GetUser(String sessionKey, String brukerkode) {
        String urlString = "/getUser.php?sessionkey=" + sessionKey + "bruker_kode=" + brukerkode;
        GetUserAsyncTask getUser = new GetUserAsyncTask();
        getUser.execute(urlString);
    }

    public void AddReservation(String sessionKey, String brukerkode, String fra, String til,
                               int romkode, int gruppekode, String gruppeleder, String formal) {
        String urlString = "/addReservation.php";
        AddReservationAsyncTask addReservation = new AddReservationAsyncTask();
        addReservation.execute(urlString);
        //STRUKTUR (eksempel): ...?fra=2016-05-07%14:30&til=2016-05-07%15:30&
        //  rom_kode=B1060&gruppe_kode=40&gruppe_leder=jdy003&formal=siljeSkalSvetteLoLsnart
    }

    public void EditReservation(String sesstionKey, String brukerkode, String fra, String til,
                                String formal, String reservasjonsId) {
        String urlString = "editReservation.php";
        EditReservationAsyncTask editReservation = new EditReservationAsyncTask();
        editReservation.execute(urlString);
    }

}
