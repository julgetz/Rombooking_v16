package com.example.julia.rombooking_v16;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private Activity parent;


    public String[] loginCol = {
            LoginTable.EMAIL,
            LoginTable.PASSORD,
            LoginTable.SESSIONKEYE,
    };

    public String[] romCol = {
            RomTable.ROM_KODE,
            RomTable.ROM_NAVN,
            RomTable.ROM_TYPE_KODE,
            RomTable.KAPASITET_UND,
            RomTable.CAMPUS_ID,
            RomTable.ER_AKTIVT
    };

    private String[] romTyperCol = {
            RomTypeTable.ROM_TYPE_KODE
    };

    private String[] romUtstyrCol = {
            RomUtstyrTable.UTSTYR_KODE,
            RomUtstyrTable.ROM_KODE
    };

    private String[] utstyrCol = {
            UtstyrTable.UTSTYR_KODE,
            UtstyrTable.UTSTYR_NAVN,
    };

    private String[] brukerCol = {
            BrukerTable.BRUKER_KODE,
            BrukerTable.FORNAVN,
            BrukerTable.ETTERNAVN,
            BrukerTable.EPOST,
            BrukerTable.BRUKER_TYPE_ID,
            BrukerTable.PASSORD,
            BrukerTable.RANDOM,
            BrukerTable.VERTIFISERT,
            BrukerTable.OPPRETTET
    };

    private String[] brukerTypeCol = {
            BrukerTyperTable.BRUKER_TYPE_ID,
            BrukerTyperTable.BRUKER_TYPE_NAVN,
    };

    private String[] campusCol = {
            CampusTable.CAMPUS_ID,
            CampusTable.CAMPUS_NAVN,
    };

    private String[] gruppeNavnCol = {
            GruppenavnTable.GRUPPE_KODE,
            GruppenavnTable.GRUPPENAVN,
    };

    private String[] grupperCol = {
            GrupperTable.GRUPPE_KODE,
            GrupperTable.BRUKER_KODE,
    };

    private String[] reservasjonerCol = {
            ReservasjonerTable.RESERVASJONS_ID,
            ReservasjonerTable.FRA,
            ReservasjonerTable.TIL,
            ReservasjonerTable.ROM_KODE,
            ReservasjonerTable.GRUPPE_KODE,
            ReservasjonerTable.GRUPPE_LEDER,
            ReservasjonerTable.FORMAL,
    };

    public DataSource(MainActivity mainActivity) {
        parent = mainActivity;
        dbHelper = new MySQLiteHelper(parent);
    }

    public DataSource(LoginActivity loginActivity) {
        parent = loginActivity;
        dbHelper = new MySQLiteHelper(parent);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //LOGIN
    // adds login data to the datbase
    public boolean createLoginData(Login login){
     ContentValues values = new ContentValues();
        values.put(LoginTable.EMAIL, login.getEmail());
        values.put(LoginTable.PASSORD, login.getPassord());
        values.put(LoginTable.SESSIONKEY, login.getSessionkeye());

        long insertId = database.insert(LoginTable.LOGIN_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }

    public Login cursorToLogin(Cursor cursor){
        Login login = new Login();

        int email = cursor.getColumnIndexOrThrow(LoginTable.EMAIL);
        int passord = cursor.getColumnIndexOrThrow(LoginTable.PASSORD);

        login.setEmail(cursor.getString(email));
        login.setPassord(cursor.getString(passord));

        return login;
    }

    //BRUKER
    // add users to the database
    public boolean createBrukerData(Bruker bruker) {
        ContentValues values = new ContentValues();
        values.put(BrukerTable.BRUKER_KODE, bruker.getBruker_kode());
        values.put(BrukerTable.FORNAVN, bruker.getFornavn());
        values.put(BrukerTable.ETTERNAVN, bruker.getEtternavn());
        values.put(BrukerTable.EPOST, bruker.getEpost());
        values.put(BrukerTable.BRUKER_TYPE_ID, bruker.getBruker_type_id());
        values.put(BrukerTable.PASSORD, bruker.getPassord());
        values.put(BrukerTable.RANDOM, bruker.getRandom());
        values.put(BrukerTable.VERTIFISERT, bruker.getVertifisert());
        values.put(BrukerTable.OPPRETTET, bruker.getOpprettet());

        long insertId = database.insert(BrukerTable.BRUKER_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }

    // BRRUKER TYPE
    // add bruker typer to the database
    public boolean creatBrukerTyperData(Bruker bruker) {
        ContentValues values = new ContentValues();
        values.put(BrukerTyperTable.BRUKER_TYPE_ID, bruker.getBruker_type_id());

        long insertId = database.insert(BrukerTyperTable.BRUKER_TYPER_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }




    // GRUPPE NAVN
    // add gruppe navn to the database
    public boolean createGruppeNavnData(Gruppenavn gruppenavn){
        ContentValues values = new ContentValues();
        values.put(GruppenavnTable.GRUPPE_KODE, gruppenavn.getGruppe_kode());
        values.put(GruppenavnTable.GRUPPENAVN, gruppenavn.getGruppenavn());

        long insertId = database.insert(GruppenavnTable.GRUPPE_NAVN_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }

    // GRUPPER
    // add grupper to the datbase
    public boolean createGrupperData(Grupper grupper){
        ContentValues values = new ContentValues();
        values.put(GrupperTable.GRUPPE_KODE, grupper.getGruppe_kode());
        values.put(GrupperTable.BRUKER_KODE, grupper.getBruker_kode());

        long insertId = database.insert(GrupperTable.GRUPPER_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }

    // RESERVASJON
    // adds a new reservasjoner til databsen
    public boolean createReservasjonData(Reservasjon reservasjon) {
        ContentValues values = new ContentValues();
        values.put(ReservasjonerTable.RESERVASJONS_ID, reservasjon.getReservasjons_id());
        values.put(ReservasjonerTable.FRA, reservasjon.getFra());
        values.put(ReservasjonerTable.TIL, reservasjon.getTil());
        values.put(ReservasjonerTable.ROM_KODE, reservasjon.getRom_kode());
        values.put(ReservasjonerTable.GRUPPE_KODE, reservasjon.getGruppe_kode());
        values.put(ReservasjonerTable.GRUPPE_LEDER, reservasjon.getGruppe_leder());
        values.put(ReservasjonerTable.FORMAL, reservasjon.getFormal());

        long insertId = database.insert(ReservasjonerTable.RESERVASJONER_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }

    // ROM
    // adds rom to the database
    public boolean createRomData(Rom rom) {
        ContentValues values = new ContentValues();
        values.put(RomTable.ROM_KODE, rom.getRom_kode());
        values.put(RomTable.ROM_NAVN, rom.getRom_navn());
        values.put(RomTable.ROM_TYPE_KODE, rom.getRom_type_kode());
        values.put(RomTable.KAPASITET_UND, rom.getKapasitet_und());

        long insertId = database.insert(RomTable.ROM_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }

    // ROM TYPER
    // add rom type to the databse
    public boolean createRomTyper(Rom rom) {
        ContentValues values = new ContentValues();
        values.put(RomTypeTable.ROM_TYPE_DATA_TABLE, rom.getRom_type_kode());

        long insertId = database.insert(UtstyrTable.UTSTYR_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }

    // ROM UTSTYR
    // adds rom utsytr to the database
    public boolean createRomUtstyrData(RomUtstyr romUtstyr){
        ContentValues values = new ContentValues();
        values.put(RomUtstyrTable.UTSTYR_KODE, romUtstyr.getUtstyr_kode());
        values.put(RomUtstyrTable.ROM_KODE, romUtstyr.getRom_kode());

        long insertId = database.insert(RomUtstyrTable.ROM_UTSTYR_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;

    }

    // UTSYTR
    // adds utstyr to the database
    public boolean createUtstyrData(Utstyr utstyr) {
        ContentValues values = new ContentValues();
        values.put(UtstyrTable.UTSTYR_KODE, utstyr.getUtstyr_kode());
        values.put(UtstyrTable.UTSTYR_NAVN, utstyr.getUtstyr_navn());

        long insertId = database.insert(UtstyrTable.UTSTYR_DATA_TABLE, null, values);
        if (insertId >= 0)
            return true;
        else
            return false;
    }

    // henter alle rom
    public Cursor getAllRoms() {
        Cursor cursor = database.query(RomTable.ROM_DATA_TABLE, romCol, null, null, null, null, null);
        return cursor;
    }

    // henter all rom data
    public Cursor getRomDataById(String romKode) {
        String selectQuery = "SELECT * FROM rom WHERE rom_kode = ?";
        String[] selectParams = new String[]{String.valueOf(romKode)};
        Cursor cursor = database.rawQuery(selectQuery, selectParams);
        return cursor;
    }

    // henter alle reservasjoner
    public Cursor GetAllReservasjoner() {
        Cursor cursor = database.query(ReservasjonerTable.RESERVASJONER_DATA_TABLE, reservasjonerCol, null, null, null, null, null);
        return cursor;
    }

    /*
    // henter alle brukere
    public  Cursor getAllBrukere(){
        Cursor cursor = database.query(BrukerTable.BRUKER_DATA_TABLE, brukerCol, null, null, null, null, null);
        return cursor;
    */

    // henter reservasjon basert på bruker
    public Cursor getReservationDataByBruker(String bruker_kode) {
        String query = "SELECT * FROM reservasjoner WHERE gruppe_leder = ?";
        String[] params = new String[]{String.valueOf(bruker_kode)};
        Cursor cursor = database.rawQuery(query, params);
        return cursor;
    }

    // henter reservasjoner basert på reservasjons id
    public Cursor getReservationDataById(int id) {
        String query = "SELECT * gruppenavn FROM reservasjoner JOIN gruppenavn ON reservasjoner.gruppe_kode = gruppenavn.gruppe_kode WHERE reservasjobs_id = ? ";
        String[] params = new String[]{String.valueOf(id)};
        Cursor cursor = database.rawQuery(query, params);
        return cursor;
    }

    // henter alle reservasjoner
    public  Cursor getAllReservations(){
        String query = "SELECT *, gruppenavn" +
                "                FROM reservasjoner" +
                "                JOIN gruppenavn" +
                "                ON reservasjoner.gruppe_kode = gruppenavn.gruppe_kode";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    // henter bruker basert på bruker_kode
    public Cursor getBrukerByBrukerKode(String bruker_kode) {
        String query = "SELECT *, bruker_type_navn" +
                "                FROM brukere" +
                "                JOIN bruker_typer" +
                "                ON brukere.bruker_type_id = bruker_typer.bruker_type_id" +
                "                WHERE bruker_kode = ?";

        String[] params = new String[]{String.valueOf(bruker_kode)};
        Cursor cursor = database.rawQuery(query, params);
        return cursor;
    }

    // henter alle brukere
    public Cursor getAllBrukere() {
        String query = "SELECT *, bruker_type_navn" +
                "                FROM brukere" +
                "                JOIN bruker_typer" +
                "                ON brukere.bruker_type_id = bruker_typer.bruker_type_id";


        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    // rediger reservasjon basert på reservasjons id
    public Cursor EditReservasjon(int reservasjon_id, String fra, String til, String formal) {
        String query = "UPDATE reservasjoner" +
                "            SET fra = ?, til = ?, formal = ?" +
                "            WHERE reservasjons_id = ?";
        String[] params = new String[]{String.valueOf(reservasjon_id), String.valueOf(fra), String.valueOf(til), String.valueOf(formal)};
        Cursor cursor = database.rawQuery(query, params);
        return cursor;

    }

    // henter rom basert på rom_kode
    public Cursor getromByRomKode(String rom_kode) {
        String query = "SELECT * FROM rom WHERE rom_kode = ?";
        String[] params = new String[]{String.valueOf(rom_kode)};
        Cursor cursor = database.rawQuery(query, params);
        return cursor;
    }

    // returnerer alle rom
    public Cursor getAllRooms() {
        String query = " SELECT * FROM rom";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    // creates and returns a User object based on the currently selected pointer in referre cursor
    public Bruker cursorToBruker(Cursor cursor) {

        Bruker bruker = new Bruker();

        int bruker_kode = cursor.getColumnIndexOrThrow(BrukerTable.BRUKER_KODE);
        int fornavn = cursor.getColumnIndexOrThrow(BrukerTable.FORNAVN);
        int etternavn = cursor.getColumnIndexOrThrow(BrukerTable.ETTERNAVN);
        int bruker_tyoe_id = cursor.getColumnIndexOrThrow(BrukerTable.BRUKER_TYPE_ID);
        int passord = cursor.getColumnIndexOrThrow(BrukerTable.PASSORD);
        int random = cursor.getColumnIndexOrThrow(BrukerTable.RANDOM);
        int vertifisert = cursor.getColumnIndexOrThrow(BrukerTable.VERTIFISERT);
        int opptettet = cursor.getColumnIndexOrThrow(BrukerTable.OPPRETTET);

        bruker.setBruker_kode(cursor.getString(bruker_kode));
        bruker.setFornavn(cursor.getString(fornavn));
        bruker.setEtternavn(cursor.getString(etternavn));
        bruker.setBruker_type_id(cursor.getInt(bruker_tyoe_id));
        bruker.setPassord(cursor.getString(passord));
        bruker.setRandom(cursor.getString(random));
        bruker.setVertifisert(cursor.getInt(vertifisert));
        bruker.setOpprettet(cursor.getString(opptettet));

        return bruker;
    }

    // creates and returns a BrukerTyper object
    public BrukerTyper cursorToBrukerTyper(Cursor cursor) {

        BrukerTyper brukerTyper = new BrukerTyper();

        int bruker_type_id = cursor.getColumnIndexOrThrow(BrukerTyperTable.BRUKER_TYPE_ID);
        int bruker_type_navn = cursor.getColumnIndexOrThrow(BrukerTyperTable.BRUKER_TYPE_NAVN);

        brukerTyper.setBruker_type_id(cursor.getInt(bruker_type_id));
        brukerTyper.setBruker_type_navn(cursor.getString(bruker_type_navn));

        return  brukerTyper;
    }

    // creates and returns a Campus object
    public Campus cursorToCampus(Cursor cursor) {

        Campus campus = new Campus();

        int campus_id = cursor.getColumnIndexOrThrow(CampusTable.CAMPUS_ID);
        int campus_navn = cursor.getColumnIndexOrThrow(CampusTable.CAMPUS_NAVN);

        campus.setCampus_id(cursor.getString(campus_id));
        campus.setCampus_navn(cursor.getString(campus_navn));

        return  campus;
    }

    // creates and returns a gruppe navn object
    public Gruppenavn cursorToGruppenavn(Cursor cursor) {

        Gruppenavn gruppeNavn = new Gruppenavn();

        int gruppe_kode = cursor.getColumnIndexOrThrow(GruppenavnTable.GRUPPE_KODE);
        int gruppe_navn = cursor.getColumnIndexOrThrow(GruppenavnTable.GRUPPENAVN);

        gruppeNavn.setGruppe_kode(cursor.getInt(gruppe_kode));
        gruppeNavn.setGruppenavn(cursor.getString(gruppe_navn));

        return  gruppeNavn;
    }

    // create and reutns grupper object
    public Grupper cursorToGrupper(Cursor cursor) {

        Grupper grupper = new Grupper();

        int gruppe_kode = cursor.getColumnIndexOrThrow(GrupperTable.GRUPPE_KODE);
        int bruker_kode = cursor.getColumnIndexOrThrow(GrupperTable.BRUKER_KODE);

        grupper.setGruppe_kode(cursor.getInt(gruppe_kode));
        grupper.setBruker_kode(cursor.getString(bruker_kode));

        return  grupper;
    }

    // creates and returns a resertvation object
    public Reservasjon cursortoReservasjon(Cursor cursor) {
        Reservasjon reservasjon = new Reservasjon();
        int reservasjons_id = cursor.getColumnIndexOrThrow(ReservasjonerTable.RESERVASJONS_ID);
        int fra = cursor.getColumnIndexOrThrow(ReservasjonerTable.FRA);
        int til = cursor.getColumnIndexOrThrow(ReservasjonerTable.TIL);
        int rom_kode = cursor.getColumnIndexOrThrow(ReservasjonerTable.ROM_KODE);
        int gruppe_kode = cursor.getColumnIndexOrThrow(ReservasjonerTable.GRUPPE_KODE);
        int gruppe_leder = cursor.getColumnIndexOrThrow(ReservasjonerTable.GRUPPE_LEDER);
        int formal = cursor.getColumnIndexOrThrow(ReservasjonerTable.FORMAL);

        reservasjon.setReservasjons_id(cursor.getInt(reservasjons_id));
        reservasjon.setFra(cursor.getString(fra));
        reservasjon.setTil(cursor.getString(til));
        reservasjon.setRom_kode(cursor.getString(rom_kode));
        reservasjon.setGruppe_kode(cursor.getInt(gruppe_kode));
        reservasjon.setGruppe_leder(cursor.getString(gruppe_leder));
        reservasjon.setFormal(cursor.getString(formal));

        return  reservasjon;
    }

    // crate and returns an rom object
    public Rom cursorToRom(Cursor cursor) {

        Rom rom = new Rom();

        int rom_kode = cursor.getColumnIndexOrThrow(RomTable.ROM_KODE);
        int rom_navn = cursor.getColumnIndexOrThrow(RomTable.ROM_NAVN);
        int rom_type_kode = cursor.getColumnIndexOrThrow(RomTable.ROM_TYPE_KODE);
        int kapasitet_und = cursor.getColumnIndexOrThrow(RomTable.KAPASITET_UND);

        rom.setRom_kode(cursor.getString(rom_kode));
        rom.setRom_navn(cursor.getString(rom_navn));
        rom.setRom_type_kode(cursor.getString(rom_type_kode));
        rom.setKapasitet_und(cursor.getInt(kapasitet_und));

        return  rom;
    }

    // creates and returns rom typer object
    public RomTyper cursorToRomTyper(Cursor cursor) {

        RomTyper romTyper = new RomTyper();

        int rom_type_kode = cursor.getColumnIndexOrThrow(RomTypeTable.ROM_TYPE_KODE);

        romTyper.setRom_type_kode(cursor.getString(rom_type_kode));

        return  romTyper;
    }

    // create and return rom utstyr object
    public RomUtstyr cursorToRomUtstyr(Cursor cursor) {

        RomUtstyr romUtstyr = new RomUtstyr();

        int utstyr_kode = cursor.getColumnIndexOrThrow(RomUtstyrTable.UTSTYR_KODE);
        int rom_kode = cursor.getColumnIndexOrThrow(RomUtstyrTable.ROM_KODE);

        romUtstyr.setUtstyr_kode(cursor.getString(utstyr_kode));
        romUtstyr.setRom_kode(cursor.getString(rom_kode));

        return  romUtstyr;
    }

    // create and reutnr a utstyrs object
    public Utstyr cursorToUtsyr(Cursor cursor) {
        Utstyr utstyr = new Utstyr();

        int utstyr_kode = cursor.getColumnIndexOrThrow(UtstyrTable.UTSTYR_KODE);
        int utstyr_navn = cursor.getColumnIndexOrThrow(UtstyrTable.UTSTYR_NAVN);

        utstyr.setUtstyr_kode(cursor.getString(utstyr_kode));
        utstyr.setUtstyr_navn(cursor.getString(utstyr_navn));

        return  utstyr;
    }
}