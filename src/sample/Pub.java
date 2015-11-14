package sample;

/**
 * Created by Marco on 15-09-27.
 */
public class Pub {
    int id;
    int age;
    String open;
    String close;
    String name;
    String picture;
    String location;
    String type;
    String street;
    String city;
    int zip;
    double lat;
    double lon;

    public Pub(int id, int age, String open, String close, String name, String picture, String type, String street, String city, int zip, double lat, double lon) {
        this.id = id;
        this.age = age;
        this.open = open;
        this.close = close;
        this.name = name;
        this.picture = picture;
        this.type = type;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
    }

    public int getAge() {
        return age;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public static int getIndexById(int id)
    {

        /*
        for (int i = 0; i < PubDataAccessor.pubs.size(); i++) {
            Pub pub = PubDataAccessor.pubs.get(i);
            if (pub.id == id) {
                return i;
            }
        }
        return -1;
        */


        int index = 0;
        // for (int = 0; i < PubDataAccessor.pubs.size(); i++) {
        //     Pub pub = PubDataAccessor.pubs.get(i);
        for(Pub pub : PubDataAccessor.pubs)
        {
            if (id == pub.id){
                index = PubDataAccessor.pubs.indexOf(pub);
            }
        }
        return index;

    }
    public static String getName(int index) {
            return PubDataAccessor.pubs.get(index).name;
    }
    public static int getAge(int index){
        return PubDataAccessor.pubs.get(index).age;
    }
    public static String getOpening(int index){
        return PubDataAccessor.pubs.get(index).open + " " + "-" + " " + PubDataAccessor.pubs.get(index).close;
    }
    public static String getAdress(int index){
        return PubDataAccessor.pubs.get(index).street + " " + PubDataAccessor.pubs.get(index).city + " " + PubDataAccessor.pubs.get(index).zip ;
    }
    public static String getImage(int index){
        return PubDataAccessor.pubs.get(index).picture;
    }
    public static String getType(int index){
        return PubDataAccessor.pubs.get(index).type;
    }
    public static double getLat(int index){
        return PubDataAccessor.pubs.get(index).lat;
    }
    public static double getLon(int index){
        return PubDataAccessor.pubs.get(index).lon;
    }
}
