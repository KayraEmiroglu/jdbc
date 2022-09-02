package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBWork {

    //PostgreSql baglantisi methodu
    public Connection connect_to_db(String dbName, String user, String sifre) {
        Connection con=null;
        try {
            Class.forName("org.postgresql.Driver");
             con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, sifre);
            if(con!=null){
                System.out.println("Baglanti saglandi");
            }else{
                System.out.println("Baglanti saglanamadi");
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }

    //Yeni tablo olusturma methodu
    public void createTable(Connection con,String tableName){
        //Statement objesi olustur
        Statement st;

        try{
            String query="Create Table "+tableName+"(empId serial, name Varchar(200), email varchar(200), salary integer, primary key(empId))";
            st = con.createStatement();

            st.executeUpdate(query);
            System.out.println("Table olusturuldu.");


        }catch (Exception e){
            System.out.println(e);
        }


    }


}