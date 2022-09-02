package jdbc;

import java.sql.*;

public class CallableStatenent01 {
    /*
    Java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandırılır.
    Sql'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" denir.
     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc","postgres","kayra1460");
        Statement st=con.createStatement();

        //1.adım:fonksiyon kodunuz yaz
        String sql1="Create function addf(x numeric,y numeric)\n" +
                         "returns numeric \n" +
                         "language plpgsql \n" +
                         "as \n" +
                         "$$\n" +
                         "begin\n" +
                         "\n" +
                         "return x+y;\n" +
                         "\n" +
                         "end\n" +
                         "$$";

        //2.adım: fonksiyonu calistir.
        st.execute(sql1);

        //3.adım: fonsiyonu çağır
        CallableStatement cst1 =con.prepareCall("{? = call addf(?, ?)}");

        //4.adım:Return icin registerOutParameter() methodunu, parametreler icin set() methodlarından uygun olanları kullan.
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,20);
        cst1.setInt(3,25);

        //5. adım :Calistırmak icin execute() methodunu kullan.
        cst1.execute();

        //6.adım : sonucu cağırmak icin return data tipine gore "get" methodlarından uygun olanı kullanıcaz
        System.out.println(cst1.getBigDecimal(1));


    }
}
