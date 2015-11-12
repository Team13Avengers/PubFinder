package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marco on 15-09-25.
 */
public class PubDataAccessor {
    public static List<Pub> pubs = new ArrayList<>();
    //public static Map<Integer, Pub> pubsById = new HashMap<>();
    //public static Map<Integer, Integer> pubIndexesByid = new HashMap<>();

    public static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://marcokoivisto.me:3306/Pubs", "marco", "Marcok1996");
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void PubDataAccessor() {
        try {
            Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM pubs LEFT OUTER JOIN type ON pubs.type_id = type.id JOIN location ON pubs.location_id = location.id");

            while (res.next()) {
                String name = res.getString("name");
                pubs.add( new Pub(res.getInt("id"), res.getInt("age"), res.getInt("open"), res.getInt("close"),
                        res.getString("name"), res.getString("picture"), res.getString("type_of_pub"), res.getString("street"), res.getString("city"), res.getInt("zip")));

            }

            for (Pub pub: pubs) {
                System.out.println(pub.name + " " + pub.age + " " + pub.street);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPub(Pub pub) {
        Connection conn = getConnection();

        //int id, int age, int open, int close, String name, String picture, String location, String type, String street, String city, int zip
        String query = "INSERT INTO pubs ("
                + " age,"
                + " open,"
                + " close,"
                + " name,"
                + " picture,"
                //+ " type,"
                + " location_id,"
                + " street ) VALUES ("
                + "?, ?, ?, ?, ?, ?, ?)";

        try {
            // set all the preparedstatement parameters
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, pub.getAge());
            st.setInt(2, pub.getOpen());
            st.setInt(3, pub.getClose());
            st.setString(4, pub.getName());
            st.setString(5, pub.getPicture());
            //st.setString(6, pub.getLocation());
            //st.setString(7, pub.getType());
            st.setString(7, pub.getStreet());
            st.setInt(6, 0);

            // execute the prepared statement insert
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException se) {
        }
    }
}

