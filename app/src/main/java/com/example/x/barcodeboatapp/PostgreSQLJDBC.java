package com.example.x.barcodeboatapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by x on 03.04.2017.
 */

public class PostgreSQLJDBC {



    public static void writetoDB(String EAN, String artikelName) throws SQLException {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://balrog:5432/h1451938",
                            "h1451938", "h1451938");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO ARTIKEL(Artikel_ID,Artikelname) "
                + "VALUES (" + EAN + ", '" + artikelName + "');";
        stmt.executeUpdate(sql);

    }
}
