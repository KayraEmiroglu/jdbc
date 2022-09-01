package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. adım:Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2.adım:Database'a bağlan
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc","postgres","kayra1460");

        //3.adım:Statement olustur
        Statement st = con.createStatement();

        //4.adım: Query calistir.
        //1.Örnek: "workers" adında bir table olusturup "worker_id ,worker_name, worker_salary" sutunlarını ekleyin
        String sql="Create table workers(worker_id varchar(50),worker_name varchar(50),worker_salary int)";
        st.execute(sql);

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2="Alter table workers add worker_address varchar(80)";
        st.execute(sql2);

        //3.Örnek: Drop workers table
        String sql3="drop table workers";
        st.execute(sql3);

        //5. Adım: Bağlantı ve Statement'ı kapat.
        con.close();
        st.close();

    }
}
