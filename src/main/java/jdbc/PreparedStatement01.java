package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc", "postgres", "kayra1460");
        Statement st = con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1. Adım:  Prepared statement query'sini oluştur.
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2. Adım: PreparedStatement objesini oluştur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. Adım: set...() methodları ile soru işaretleri için değer gir.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4. Adım: Execute query
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println(updateRowSayisi + " satir guncellendi.");
        String sql2 = "SELECT * FROM companies";
        ResultSet result2 = st.executeQuery(sql2);

        while (result2.next()) {

            System.out.println(result2.getInt(1) + "--" + result2.getString(2) + "--" + result2.getInt(3));
        }

        //Google için değişiklik
        pst1.setInt(1, 15000);
        pst1.setString(2, "GOOGLE");

        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println(updateRowSayisi2 + " satir guncellendi.");

        String sql3 = "SELECT * FROM companies";
        ResultSet result3 = st.executeQuery(sql3);

        while (result3.next()) {

            System.out.println(result3.getInt(1) + "--" + result3.getString(2) + "--" + result3.getInt(3));
        }

        // String sql4 = "SELECT * FROM ---";
        //
        // PreparedStatement pst2= con.prepareStatement(sql4);
        //
        // ResultSet result4= pst2.executeQuery();

        // while (result4.next()){

        //     System.out.println(result4.getInt(1)+"--"+result4.getString(2)+"--"+result4.getInt(3));
        // }
        read_Data(con,"companies");
        con.close();
        st.close();
        result2.close();
        result3.close();


    }

    public static void read_Data(Connection con, String tableName) {
        try {
            String query = String.format("SELECT * FROM %s", tableName);
            //SQL query'i calistir.
            Statement statement = con.createStatement();

            ResultSet rs =statement.executeQuery(query);//Data'yi cagirip ResultSet konteynırına koyuyuoruz.
            System.out.println("Method:");
            while (rs.next()){
                System.out.println(+rs.getInt(1)+"--"+rs.getString(2)+"--"+rs.getInt(3));
            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }


}
