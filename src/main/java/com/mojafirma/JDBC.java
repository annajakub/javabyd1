package com.mojafirma;

import com.mojafirma.DataBase.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;
//STEP 1. Import required packages

public class JDBC {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/j1b";
    // Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            conn = DataBase.getConnection();
// poniżej usunęłam kod, zastąpiony połączeniem 1-razowym powyższym
//            //STEP 2: Register JDBC driver
//            Class.forName("com.mysql.jdbc.Driver");
//            //STEP 3: Open a connection
//            System.out.println("Connecting to database...


            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            String sql2;
            //
//            ResultSet rs = stmt.executeQuery(sql2);

            //STEP 5: Extract data from result set
            Scanner scanner = new Scanner(System.in);

            System.out.println("Podaj liczbę 1(wyświetl) lub 2(dodaj)");
            int z = scanner.nextInt();

            //Retrieve by column name
            switch (z) {
                case (1):
                    sql = "SELECT id_adresu, ulica, miasto, numer_domu FROM adresy";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        int id_adresu = rs.getInt("id_adresu");
                        int numer_domu = rs.getInt("numer_domu");
                        String ulica = rs.getString("ulica");
                        String miasto = rs.getString("miasto");
                        //Display values
                        System.out.print("id_adresu: " + id_adresu);
                        System.out.print(", ulica: " + ulica);
                        System.out.print(", numer_domu: " + numer_domu);
                        System.out.println(", miasto: " + miasto);
                    }
                    rs.close(); //przekljony z dołu
                    break;

                case (2):
                    System.out.println("Wpisz ulicę:");
                    String ulica = scanner.next();
                    System.out.println("Wpisz miasto:");
                    String miasto = scanner.next();
                    System.out.println("Podaj numer mieszkania");
                    int numer_mieszkania = scanner.nextInt();
                    System.out.println("Podaj id adresu");
                    int id_adresu = scanner.nextInt();


                    sql2 = "INSERT INTO adresy (id_adresu, ulica, miasto, numer_mieszkania)" +
                            " VALUES (" + id_adresu + ", '" + ulica + "', '" + miasto + "', " + numer_mieszkania + ")";
                                                                            // tylko stringi opakować trzeba dodatkowo ''
                    int result = stmt.executeUpdate(sql2);                   //update robimy przez executUpdate
                    //czy result musi być też zamknięty
                    break;

            }


            //STEP 6: Clean-up environment

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample


