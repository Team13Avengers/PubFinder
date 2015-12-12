package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            ResultSet res = st.executeQuery("SELECT * FROM pubs LEFT OUTER JOIN type ON pubs.type_id = type.id JOIN location ON pubs.location_id = location.id JOIN events ON pubs.event_id = events.id");

            while (res.next()) {
                String name = res.getString("name");
                pubs.add( new Pub(res.getInt("id"), res.getInt("age"), res.getString("open"), res.getString("close"),
                        res.getString("name"), res.getString("picture"), res.getString("type_of_pub"), res.getString("street"),
                        res.getString("city"), res.getInt("zip"), res.getDouble("lat"),res.getDouble("lon"), res.getInt("nrStars"),
                        res.getInt("hasStudentDiscount"), res.getInt("entranceFee"), res.getString("eventname"), res.getString("description"), res.getInt("location_id"), res.getInt("event_id")));

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void addEvent(String eventName, String eventDescription) {
        Connection conn = getConnection();


        String query = "INSERT INTO events ("
                + " eventname,"
                + " description ) VALUES ("
                + "?, ?);";

        try {
            // set all the preparedstatement parameters
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, eventName);
            st.setString(2, eventDescription);

            // execute the prepared statement insert
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException se) {
        }
    }
    public static void deleteEvent(int idOfEvent) {
        Connection conn = getConnection();
        String query = "DELETE FROM Pubs.events WHERE ("
                + " id ) = "
                + "?;";
        try {
            // set all the prepared statement parameters
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idOfEvent);
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException se) {
        }
    }
    public static int addingEventId(){
        int addEventId = 0;
        try {
            Connection conn = getConnection();

            String query = "SELECT MAX(id) FROM Pubs.events";

            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                addEventId = rs.getInt("MAX(id)");
            }
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException se) {
        }
        return addEventId;
    }


    public static void editEvent (String eventName, String eventDescription, int eventId) {
        Connection conn = getConnection();
        //Statement statement = conn;

        String Update = "UPDATE Pubs.events "
                + "SET eventname =  '" + eventName
                + "', description =  '"+ eventDescription
                + "' WHERE id = '"+eventId+"';";

        try {
            // set all the preparedstatement parameters
            PreparedStatement st = conn.prepareStatement(Update);
            st.executeUpdate(Update);
        } catch (SQLException se) {
        }
    }


    public static void addPub(String name, String picture, int age, int open, int close, String street, double lat, double lon, int nrStars, int type_id,
                              int location_id, int event_id, int hasStudentDiscount, int hasFee) {
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
                + " nrStars,"
                + " type_id,"
                + " location_id,"
                + " event_id,"
                + " hasStudentDiscount,"
                + " entranceFee ) VALUES ("

                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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
            st.setInt(9, nrStars);
            st.setInt(10, type_id);
            st.setInt(11, location_id);
            st.setInt(12, event_id);
            st.setInt(13, hasStudentDiscount);
            st.setInt(14, hasFee);

            // execute the prepared statement insert
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException se) {
        }
    }

    public static void deletePub(int idOfPub){
         /* SQL FOR DELETING THE PUB OF ID "idOfPub" */
        Connection conn = getConnection();
        String query = "DELETE FROM pubs WHERE ("
                + " id ) = "
                + "?;";
        try {
            // set all the prepared statement parameters
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idOfPub);
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException se) {
        }
    }
    public static void updateRate(int idOfPub){
        /* SQL FOR UPDATING the rating of the pub*/
        Connection conn = getConnection();
        String query = "UPDATE Pubs.pubs SET "
                + " rate = rate+1  WHERE  pubs.id = "
                + "?;";
        try {
            // set all the prepared statement parameters
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idOfPub);
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException se) {
        }
    }
    public static int checkRate (int pubID) {
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT rate FROM pubs WHERE id = " + pubID
            );
            if (rs.next()) return rs.getInt("rate");
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }


    //public static void ratingSearch ()
    public static void editPub (String name, String picture, int age, int open, int close, String street, double lat, double lon, int nrStars,
                                int type_id, int location_id , int event_id , int pubID, int hasStudentDiscount, int hasFee) {
        Connection conn = getConnection();
        //Statement statement = conn;

        String Update = "UPDATE pubs "
                + "SET name =  '" + name
                + "',  picture = '" + picture
                + "', age =  '" + age
                + "',  open =  '" + open
                + "', close =  '" + close
                + "', street =  '" +street
                + "',  lat =  '"+lat
                + "', lon = '"+lon
                + "', nrStars = '"+nrStars
                + "', type_id= '"+type_id
                + "', location_id=  '"+location_id
                + "', event_id=  '"+event_id
                + "', hasStudentDiscount=  '"+hasStudentDiscount
                + "', entranceFee=  '"+ hasFee
                + "' WHERE id = '"+pubID+"';";

        try {
            // set all the preparedstatement parameters
            PreparedStatement st = conn.prepareStatement(Update);
            st.executeUpdate(Update);
        } catch (SQLException se) {
        }
    }

    public static void clearCache(){
        pubs.clear();
        PubDataAccessor();
    }
}


