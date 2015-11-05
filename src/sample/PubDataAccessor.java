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

    public static void PubDataAccessor() {
        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://marcokoivisto.me:3306/Pubs","marco","Marcok1996");
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM pubs LEFT OUTER JOIN type ON pubs.type_id = type.id JOIN location ON pubs.location_id = location.id");



                while (res.next()) {
                    String name = res.getString("name");
                    pubs.add( new Pub(res.getInt("id"), res.getInt("age"), res.getInt("open"), res.getInt("close"),
                            res.getString("name"), res.getString("picture"), res.getString("location"), res.getString("type_of_pub"), res.getString("street"), res.getString("city"), res.getInt("zip")));

                }
            for (Pub pub: pubs) {
                System.out.println(pub.name + " " + pub.age + " " + pub.street);
            }
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

