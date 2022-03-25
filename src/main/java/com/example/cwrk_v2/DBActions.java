package com.example.cwrk_v2;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

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
    public void createTables() throws SQLException {
        String sql = "CREATE TABLE users (\n" +
                "\tuName varchar(100) NOT NULL,\n" +
                "\tuPass varchar(100) NOT NULL,\n" +
                "\tuID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "\tisAdmin INT NOT NULL\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE drivers (\n" +
                "\tdID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                "\tdName varchar(255) NOT NULL\n" +
                "\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE races (\n" +
                "\trID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "\ttrackName varchar(255) NOT NULL,\n" +
                "\tdate INT NOT NULL,\n" +
                "\tp1 INT NOT NULL,\n" +
                "\tp2 INT NOT NULL,\n" +
                "\tp3 INT NOT NULL,\n" +
                "\tp4 INT NOT NULL,\n" +
                "\tp5 INT NOT NULL,\n" +
                "\tp6 INT NOT NULL,\n" +
                "\tp7 INT NOT NULL,\n" +
                "\tp8 INT NOT NULL,\n" +
                "\tp9 INT NOT NULL,\n" +
                "\tp10 INT NOT NULL,\n" +
                "\tp11 INT NOT NULL,\n" +
                "\tp12 INT NOT NULL,\n" +
                "\tp13 INT NOT NULL,\n" +
                "\tp14 INT NOT NULL,\n" +
                "\tp15 INT NOT NULL,\n" +
                "\tp16 INT NOT NULL,\n" +
                "\tp17 INT NOT NULL,\n" +
                "\tp18 INT NOT NULL,\n" +
                "\tp19 INT NOT NULL,\n" +
                "\tp20 INT NOT NULL\n" +
                ");\n" +
                "\n" +
                "alter table races add constraint uq_races UNIQUE(date, trackname);\n"+
                "ALTER TABLE races ADD CONSTRAINT races_fk0 FOREIGN KEY (p1) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk1 FOREIGN KEY (p2) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk2 FOREIGN KEY (p3) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk3 FOREIGN KEY (p4) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk4 FOREIGN KEY (p5) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk5 FOREIGN KEY (p6) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk6 FOREIGN KEY (p7) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk7 FOREIGN KEY (p8) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk8 FOREIGN KEY (p9) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk9 FOREIGN KEY (p10) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk10 FOREIGN KEY (p11) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk11 FOREIGN KEY (p12) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk12 FOREIGN KEY (p13) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk13 FOREIGN KEY (p14) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk14 FOREIGN KEY (p15) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk15 FOREIGN KEY (p16) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk16 FOREIGN KEY (p17) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk17 FOREIGN KEY (p18) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk18 FOREIGN KEY (p19) REFERENCES drivers(dID);\n" +
                "ALTER TABLE races ADD CONSTRAINT races_fk19 FOREIGN KEY (p20) REFERENCES drivers(dID);";
        //ektelesi tou querry
        int row = stmt.executeUpdate(sql);
    }

    //"gemizei" ta tables me dedomena
    public void populateTables() throws SQLException {
        //create admin acc
        String sql = "insert into users (uName, uPass, isAdmin) values ('admin', ?, 1);";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, MD5.getMd5("1234"));
        pst.executeUpdate();
        //insert every 2022 driver
        sql = "insert into drivers values \n" +
                "(24, 'Guanyu Zhou'),\n" +
                "(77, 'Valtteri Bottas'),\n" +
                "(10, 'Pierre Gasly'),\n" +
                "(22, 'Yuki Tsunoda'),\n" +
                "(14, 'Fernando Alonso'),\n" +
                "(31, 'Esteban Ocon'),\n" +
                "(27, 'Nico Hulkenberg'),\n" +
                "(5, 'Sebastian Vettel'),\n" +
                "(18, 'Lance Stroll'),\n" +
                "(16, 'Charles Leclerc'),\n" +
                "(55, 'Carlos Sainz Jr.'),\n" +
                "(20, 'Kevin Magnussen'),\n" +
                "(47, 'Mick Schumacher'),\n" +
                "(3, 'Daniel Ricciardo'),\n" +
                "(4, 'Lando Norris'),\n" +
                "(44, 'Lewis Hamilton'),\n" +
                "(63, 'George Russell'),\n" +
                "(1, 'Max Verstappen'),\n" +
                "(11, 'Sergio Perez'),\n" +
                "(6, 'Nicholas Latifi'),\n" +
                "(23, 'Alexander Albon')\n";
        int row = stmt.executeUpdate(sql);
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

    public boolean insertRace(String trackName, int year, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9, int p10, int p11, int p12, int p13, int p14, int p15, int p16, int p17, int p18, int p19, int p20) throws SQLException {
        //se periptwsi pou o sindiasmos event kai xronias iparxei, den tha dimiourgeite i engrafi
        //vasi kanonismwn Formula One Managment, den mporei na dieksaxthoun 2 agwnes stin idia pista xwris diaforetiko onoma
        //p.x. to 2020 gia na ginoun 2 agwnes stin idia pista o enas onomastike "FORMULA 1 ROLEX GROSSER PREIS VON ÖSTERREICH 2020" kai o allos "FORMULA 1 PIRELLI GROSSER PREIS DER STEIERMARK 2020"
        //EXEI MPEI CONSTRAINT KAI STIN VASI!!!!
        boolean result = checkIfRaceExists(year, trackName);
        if (result) {
            // to ?, ?, 0 simenei oti oloi oi users mesw tou register from den tha exoun admin priviliges
            String sql = "INSERT INTO \"RACES\"(\"TRACKNAME\",\"DATE\",\"P1\",\"P2\",\"P3\",\"P4\",\"P5\",\"P6\",\"P7\",\"P8\",\"P9\",\"P10\"," +
                    "\"P11\",\"P12\",\"P13\",\"P14\",\"P15\",\"P16\",\"P17\",\"P18\",\"P19\",\"P20\")" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, trackName);
            pst.setInt(2, year);
            pst.setInt(3, p1);
            pst.setInt(4, p2);
            pst.setInt(5, p3);
            pst.setInt(6, p4);
            pst.setInt(7, p5);
            pst.setInt(8, p6);
            pst.setInt(9, p7);
            pst.setInt(10, p8);
            pst.setInt(11, p9);
            pst.setInt(12, p10);
            pst.setInt(13, p11);
            pst.setInt(14, p12);
            pst.setInt(15, p13);
            pst.setInt(16, p14);
            pst.setInt(17, p15);
            pst.setInt(18, p16);
            pst.setInt(19, p17);
            pst.setInt(20, p18);
            pst.setInt(21, p19);
            pst.setInt(22, p20);
            //ektelesi query
            pst.executeUpdate();
        }
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
    public boolean checkIfRaceExists(int year,String trackName) throws SQLException {
        boolean uniqueUser = true;
        //pairnei ola to username apo tin ontotita stin vasi
        ResultSet res = stmt.executeQuery("SELECT * FROM races");
        while (res.next()) {
            String tname = (res.getObject("TRACKNAME")).toString();
            int date = Integer.parseInt((res.getObject("date")).toString());
            //ean uparxei to antistoi username gyrna false
            if (tname.equals(trackName)) {
                if (date==year) {
                    uniqueUser = false;
                }
            }
        }
        return uniqueUser;
    }

    //elenxos oti o kainourgios xristis den exei idio username me proiparxwn
    public boolean checkIfDriverExists(int driverID) throws SQLException {
        boolean doesNotExist = true;
        //pairnei ola to username apo tin ontotita stin vasi
        ResultSet res = stmt.executeQuery("SELECT * FROM drivers");
        while (res.next()) {
            int dID = Integer.parseInt(String.valueOf((res.getObject("dID"))));
            //ean uparxei to antistoi username gyrna false
            if (dID==driverID) {
                doesNotExist = false;
            }
        }
        return doesNotExist;
    }


    public void cleanUp() throws SQLException {
// This part might also be repeated in every method


// close statements and connection
        stmt.close();
        conn.close();
    }
    public String showRacesPerYear(int year) throws SQLException {
        String result="";
        ResultSet res = stmt.executeQuery("SELECT * FROM RACES where date = "+year);
        while (res.next()) {
      //      System.out.println("has next");
            result += (res.getObject("TRACKNAME")).toString()+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p1")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p2")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p3")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p4")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p5")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p6")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p7")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p8")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p9")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p10")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p11")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p12")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p13")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p14")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p15")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p16")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p17")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p18")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p19")).toString()))+" ";
            result +=findDriverName(Integer.parseInt((res.getObject("p20")).toString()))+" ";
            result+="\n";
        }
      //  System.out.println(result);
        return  result;
    }

    public String findDriverName(int dID) throws SQLException {
        String dName="";
        //conn.CreateStatement xriazete dioti kanw polla querries sto showRacesperYear kai emfanizei exception
        //psaxnw ton odigo me to sigkekrimeno ID gia na emfanisei apo tin sxesiaki vasi to onoma
        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM drivers where did = "+dID);
        while (res.next()) {
            int driverID = Integer.parseInt(String.valueOf((res.getObject("dID"))));
            String driverName = String.valueOf((res.getObject("dName")));
            //ean uparxei to antisto driverName to apothikevi
            if (dID==driverID) {
                dName= driverName;
            }
        }
        return dName;
    }


}
