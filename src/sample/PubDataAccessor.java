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
                pubs.add( new Pub(res.getInt("id"), res.getInt("age"), res.getString("open"), res.getString("close"),
                        res.getString("name"), res.getString("picture"), res.getString("type_of_pub"), res.getString("street"), res.getString("city"), res.getInt("zip"), res.getDouble("lat"),res.getDouble("lon")));

            }

            for (Pub pub: pubs) {
                System.out.println(pub.name + " " + pub.age + " " + pub.street);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public static void addPub(String name, String picture, int age, int open, int close, String street, double lat, double lon, int type_id, int location_id) {
         Connection conn = getConnection();

         //int id, int age, int open, int close, String name, String picture, String location, String type, String street, String city, int zip
         String query = "INSERT INTO pubs ("
                 + " name,"
                 + " picture,"
                 + " age,"
                 + " open,"
                 + " close,"
                 + " street,"
                 + " lat,"
                 + " lon,"
                 + " type_id,"
                 + " location_id ) VALUES ("
                 + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

         try {
             // set all the preparedstatement parameters
             PreparedStatement st = conn.prepareStatement(query);

             st.setString(1, name);
             st.setString(2, picture);
             st.setInt(3, age);
             st.setInt(4, open);
             st.setInt(5, close);
             st.setString(6, street);
             st.setDouble(7, lat);
             st.setDouble(8, lon);
             st.setInt(9, type_id);
             st.setInt(10, location_id);

             // execute the prepared statement insert
             st.executeUpdate();
             st.close();
             conn.close();
         } catch (SQLException se) {
         }
     }
 }


