package jdbc;

import java.sql.Connection;

public class Runner {
    public static void main(String[] args) {

        //DBWork objesini olustur.
        DBWork db=new DBWork();
        //Connection methodu cagir.
        Connection con=db.connect_to_db("jdbc","postgres","kayra1460");

        //Yeni table olusturma methodunu cagir.
        db.createTable(con,"employees");




    }
}
