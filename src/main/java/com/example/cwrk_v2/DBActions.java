package com.example.cwrk_v2;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class DBActions {
    //SE PERIPTWSI ALLAGIS DB, prepei na allaxtoun ta parakatw stoixeia
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String User = "sa";
    private static final String Pass = "";
    private Connection conn = null;
    private Statement stmt = null;

    public DBActions() throws ClassNotFoundException, SQLException {
        //fortwnw tin vivliothiki
        Class.forName("org.h2.Driver");
        //open a connections
        conn = DriverManager.getConnection(DB_URL, User, Pass);

        //execute a query
        stmt = conn.createStatement();
    }

    //dimiourgei ola ta tables pou xriazetai i efarmogi
    public void createTables() throws SQLException, FileNotFoundException {
        FileActions FA = new FileActions();
        String sql = FA.load("createTables");
        //ektelesi tou querry
        int row = stmt.executeUpdate(sql);
    }

    //"gemizei" ta tables me dedomena
    public void populateTables() throws SQLException, IOException {
        FileActions FA = new FileActions();
        //create admin acc
        String sql = "insert into users (uName, uPass, isAdmin) values ('admin', ?, 1);";
        PreparedStatement pst = conn.prepareStatement(sql);
        //password hashing for security
        pst.setString(1, MD5.getMd5("1234"));
        //insert the admin user
        pst.executeUpdate();

        //insert every 2021 and 2022 driver
        sql = FA.load("driverInfo");
        //execute the insert driver query
        int row = stmt.executeUpdate(sql);

        //insert every 2021 race result
        sql = FA.load("2021RaceInfo");
        //execute the insert driver query
        row = stmt.executeUpdate(sql);
    }

    //elenxos gia log in stin vasi
    public boolean[] userLogIn(String username, String password) throws SQLException {
        ResultSet res = stmt.executeQuery("SELECT * FROM users");
        boolean[] result = new boolean[2];
        result[0] = false;
        //diatrexoume olo to table users, se periptwsi pou vrethei antistoixeia metaksi tis vasis kai twn dedomenwn
        //pou eisigage o xristiw, tha girisei true, se antitheti periptvsi false
        while (res.next()) {
            String uID = (res.getObject("uID").toString());
            int admin = Integer.parseInt(res.getObject("isAdmin").toString());
            String uName = (res.getObject("uName").toString());
            String uPass = (res.getObject("uPass").toString());
            if (username.equals(uName) && MD5.getMd5(password).equals(uPass)) {
                System.out.println("Succefully loged in " + uName);
                result[0] = true;
                if (admin == 1) {
                    result[1] = true;
                } else {
                    result[1] = false;
                }
            }
        }
        return result;
    }

    //eisagontai ta dedomena stin vasi mesw tou register scene
    public boolean userRegister(String username, String password) throws SQLException {
        //se periptwsi pou yparxei to username den tha dimiourgithei o xristis
        boolean result = checkIfUniqueUser(username);
        if (result) {
            // to ?, ?, 0 simenei oti oloi oi users mesw tou register form den tha exoun admin priviliges
            String sql = "INSERT INTO users (uName, uPass, isAdmin) VALUES (?, ?, 0)";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username.trim());
            //kriptografisi kwdikou
            pst.setString(2, MD5.getMd5(password.trim()));
            //ektelesi query
            pst.executeUpdate();
        }
        return result;

    }


    public boolean insertDriver(int driverID, String driverName) throws SQLException {
        //se periptwsi pou yparxei to dID den dimiourgeite o odigos
        //apo to 2014 kai epeita o odigos exei monadiko gia thn f1 anagnwristiko (enas arithmos apo to 0 mexri to 99)
        boolean result = checkIfDriverExists(driverID);
        if (result) {
            // to ?, ?, 0 simenei oti oloi oi users mesw tou register from den tha exoun admin priviliges
            String sql = "INSERT INTO drivers (dID, dName) VALUES (?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, driverID);
            pst.setString(2, driverName);
            //ektelesi query
            pst.executeUpdate();
        }
        return result;

    }

    public boolean insertRace(String trackName, int year, int round, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9, int p10, int p11, int p12, int p13, int p14, int p15, int p16, int p17, int p18, int p19, int p20) throws SQLException {
        //se periptwsi pou o sindiasmos event kai xronias iparxei, den tha dimiourgeite i engrafi
        //vasi kanonismwn Formula One Management, den mporei na dieksaxthoun 2 agwnes stin idia pista xwris diaforetiko onoma
        //p.x. to 2020 gia na ginoun 2 agwnes stin idia pista o enas onomastike "FORMULA 1 ROLEX GROSSER PREIS VON ÖSTERREICH 2020" kai o allos "FORMULA 1 PIRELLI GROSSER PREIS DER STEIERMARK 2020"
        //EXEI MPEI CONSTRAINT KAI STIN VASI!!!!
        boolean result = checkIfRaceExists(year, trackName);
        if (result) {
            String sql = "INSERT INTO \"RACES\"(\"TRACKNAME\",\"DATE\",\"ROUND\",\"P1\",\"P2\",\"P3\",\"P4\",\"P5\",\"P6\",\"P7\",\"P8\",\"P9\",\"P10\"," +
                    "\"P11\",\"P12\",\"P13\",\"P14\",\"P15\",\"P16\",\"P17\",\"P18\",\"P19\",\"P20\")" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, trackName);
            pst.setInt(2, year);
            pst.setInt(3, round);
            pst.setInt(4, p1);
            pst.setInt(5, p2);
            pst.setInt(6, p3);
            pst.setInt(7, p4);
            pst.setInt(8, p5);
            pst.setInt(9, p6);
            pst.setInt(10, p7);
            pst.setInt(11, p8);
            pst.setInt(12, p9);
            pst.setInt(13, p10);
            pst.setInt(14, p11);
            pst.setInt(15, p12);
            pst.setInt(16, p13);
            pst.setInt(17, p14);
            pst.setInt(18, p15);
            pst.setInt(19, p16);
            pst.setInt(20, p17);
            pst.setInt(21, p18);
            pst.setInt(22, p19);
            pst.setInt(23, p20);
            //execute query
            try {
                pst.executeUpdate();
            }catch (JdbcSQLIntegrityConstraintViolationException e){
                System.out.println("Most likely you have entered a driver id that does not exist in the DB \n more details: "+e);
                result = false;

            }
            /*int updateResult =
            if (updateResult==0){
                return false;
            }
            else return true;*/
        }
        System.out.println(result);
        return result;

    }


    //elenxos oti o kainourgios xristis den exei idio username me proiparxwn
    public boolean checkIfUniqueUser(String username) throws SQLException {
        boolean uniqueUser = true;
        //pairnei ola to username apo tin ontotita stin vasi
        ResultSet res = stmt.executeQuery("SELECT * FROM users");
        while (res.next()) {
            String uNanme = (res.getObject("uName")).toString();
            //ean uparxei to antistoi username gyrna false
            if (uNanme.equals(username)) {
                uniqueUser = false;
            }
        }
        return uniqueUser;
    }

    //elenxos monadikotitas agwna prin apostalei stin vasi
    public boolean checkIfRaceExists(int year, String trackName) throws SQLException {
        boolean uniqueRace = true;
        //get every race from the database
        ResultSet res = stmt.executeQuery("SELECT * FROM races");
        while (res.next()) {
            String tname = (res.getObject("TRACKNAME")).toString();
            int date = Integer.parseInt((res.getObject("date")).toString());
            //if the trackname is already saved, check the year
            if (tname.equals(trackName)) {
                if (date == year) {
                    //if there is a race done in the same track, the same year
                    //then do not update the DB
                    uniqueRace = false;
                }
            }
        }
        return uniqueRace;
    }

    //check that the new user submited unique username
    public boolean checkIfDriverExists(int driverID) throws SQLException {
        boolean doesNotExist = true;
        //get every username from the database
        ResultSet res = stmt.executeQuery("SELECT * FROM drivers");
        while (res.next()) {
            int dID = Integer.parseInt(String.valueOf((res.getObject("dID"))));
            //if the same username is found, return false and do not proceed
            if (dID == driverID) {
                doesNotExist = false;
            }
        }
        return doesNotExist;
    }


    public ArrayList<ArrayList> showRacesPerYear(int year) throws SQLException {

        //exporting array list in order to have the oportinity to create a better layout
        ArrayList<ArrayList> results = new ArrayList<>();
        ArrayList<String> driverNames = new ArrayList<String>();
        ArrayList<String> trackNames = new ArrayList<String>();
        results.add(trackNames);
        results.add(driverNames);
        //show every race of a specific year sorted by the order they were done
        ResultSet res = stmt.executeQuery("SELECT * FROM RACES where date = " + year + " order by round");

        while (res.next()) {

            String trackName = (res.getObject("TRACKNAME")).toString();
            String tName = trackName.substring(0, 5).toUpperCase(Locale.ROOT);


            trackNames.add(tName + "\n");
            //parse evey position and save it in the string
            for (int i = 0; i < 20; i++) {
                driverNames.add("");

            }
            //every year there are 20 drivers
            for (int i = 1; i < 21; i++) {
                driverNames.set(i - 1, driverNames.get(i - 1) + findDriverName(Integer.parseInt((res.getObject("p" + i)).toString())) + "\n");
            }

        }
        return results;

    }

    public String findDriverName(int dID) throws SQLException {
        String dName = "";
        //conn.CreateStatement xriazete dioti kanw polla querries sto showRacesperYear kai emfanizei exception
        //psaxnw ton odigo me to sigkekrimeno ID gia na emfanisei apo tin sxesiaki vasi to onoma
        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM drivers where did = " + dID);
        while (res.next()) {
            int driverID = Integer.parseInt(String.valueOf((res.getObject("dID"))));
            String driverName = String.valueOf((res.getObject("dName")));
            //ean uparxei to antisto driverName to apothikevi
            if (dID == driverID) {
                //gia na girisei mono ta 3 prwta gramma tou epithetou se UPPERcase
                //p.x. o Charles Leclerc tha ginei LEC
                dName = driverName.substring(driverName.indexOf(" ") + 1).substring(0, 3).toUpperCase(Locale.ROOT);
            }
        }
        return dName;
    }

    public String[] showDriversPerYear(int year) throws SQLException {
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        Map<Integer, Integer> HMResult = new TreeMap<Integer, Integer>();
        ArrayList<Integer> driverIDs = parseDrivers();
        //start every driver with 0 points
        System.out.println(driverIDs.size());
        for (int i = 0; i < driverIDs.size(); i++) {
            HMResult.put(driverIDs.get(i), 0);
        }
        //show every race of a specific year sorted by the order they were done
        ResultSet res = stmt.executeQuery("SELECT * FROM RACES where date = " + year + " order by round");
        while (res.next()) {
            //parse the query result
            for (Map.Entry<Integer, Integer> set : HMResult.entrySet()) {
                //depending on the finishing position. award the proper points
                if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p1")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 25);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p2")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 18);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p3")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 15);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p4")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 12);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p5")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 10);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p6")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 8);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p7")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 6);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p8")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 4);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p9")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 2);
                } else if (Integer.parseInt(set.getKey().toString()) == Integer.parseInt((res.getObject("p10")).toString())) {
                    HMResult.put(set.getKey(), set.getValue() + 1);
                }
            }

        }
        //save the sorted result
        Map sortedResult = entriesSortedByValues(HMResult);
        //create a set and iterator to parse the key, value pairs
        Set set = sortedResult.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            //save the sorted result to an array in order to return them
            Map.Entry mp = (Map.Entry) iterator.next();
            //apothikeusi mono ton odigwn pou exoun pontous dioti i list exei kai allous pou den simetixan tin sigkekrimeni xronia
            if (Integer.parseInt(mp.getValue().toString()) != 0) {
                result[0] += findDriverName((Integer) mp.getKey()) + "\n";
                result[1] += mp.getValue() + "\n";
            }
        }


        return result;
    }

    public ArrayList<Integer> parseDrivers() throws SQLException {
        ArrayList<Integer> results = new ArrayList<Integer>();
        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM drivers order by did");
        int i = 0;
        while (res.next()) {
            results.add((Integer.parseInt((res.getObject("DID")).toString())));
            i++;
        }
        return results;

    }

    public void cleanUp() throws SQLException {
// This part might also be repeated in every method
// close statements and connection
        stmt.close();
        conn.close();
    }


    public static <K, V extends Comparable<V>> Map<K, V> entriesSortedByValues(final Map<K, V> map) {
        // Static Method with return type Map and
        // extending comparator class which compares values
        // associated with two keys
        Comparator<K> valueComparator = new Comparator<K>() {

            // return comparison results of values of
            // two keys
            public int compare(K k1, K k2) {
                int comp = map.get(k2).compareTo(
                        map.get(k1));
                if (comp == 0)
                    return 1;
                else
                    return comp;
            }

        };
        // SortedMap created using the comparator
        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);

        sorted.putAll(map);

        return sorted;
    }


}