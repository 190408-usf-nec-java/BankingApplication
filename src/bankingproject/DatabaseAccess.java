package bankingproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {

    public static ResultSet DataBaser(String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/afdemp_java_1", "root", "root");
        Statement stmt = (Statement) con.createStatement();
        stmt.executeQuery(query);
        ResultSet rs = stmt.getResultSet();
        return rs;
    }

    public static void UpdateDbAmount(double finalamount, int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        String query1 = "UPDATE accounts\n"
                + "    SET amount = " + finalamount + "\n"
                + "        \n"
                + "    WHERE id = " + id + ";";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/afdemp_java_1", "root", "root");
        Statement stmt = (Statement) con.createStatement();
        stmt.executeUpdate(query1);
        con.close();

    }

    public static String IdToUsername(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String Username = null;
        String id1 = Integer.toString(id);
        String QUERY = "SELECT * FROM users WHERE id=";
        String QUERY1 = QUERY.concat(id1);
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/afdemp_java_1", "root", "root");
        Statement stmt = (Statement) con.createStatement();
        stmt.executeQuery(QUERY1);
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            Username = rs.getString("username");
        }
        con.close();
        return Username;

    }

    public static int UsernameToId(String username) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        int idr = 0;
        String QUERY = "SELECT * FROM users WHERE username=";
        String QUERY1 = QUERY.concat("\"" + username + "\"");
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/afdemp_java_1", "root", "root");
        Statement stmt = (Statement) con.createStatement();
        stmt.executeQuery(QUERY1);
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            idr = rs.getInt("id");
        }
        con.close();
        return idr;

    }

}
