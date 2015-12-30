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
    String area;
    String eventName;
    String eventDescription;
    int zip;
    double lat;
    double lon;
    int nrStars;
    int hasStudentDiscount;
    int hasFee;
    int location_id;
    int event_id;

    public Pub(int id, int age, String open, String close, String name, String picture, String type, String street, String area, int zip, double lat, double lon, int nrStars, int hasStudentDiscount, int hasFee, String eventName, String eventDescription, int location_id, int event_id, String city) {
        this.id = id;
        this.age = age;
        this.open = open;
        this.close = close;
        this.name = name;
        this.picture = picture;
        this.type = type;
        this.street = street;
        this.area = area;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
        this.nrStars = nrStars;
        this.hasStudentDiscount = hasStudentDiscount;
        this.hasFee = hasFee;
        this.eventDescription = eventDescription;
        this.eventName = eventName;
        this.location_id = location_id;
        this.event_id = event_id;
        this.city = city;
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

    public int getHasStudentDiscount() {
        return hasStudentDiscount;
    }

    public int getLocation_id() {
        return location_id;
    }

    public static int getIndexById(int id) {
        int index = 0;
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
        String openTime = PubDataAccessor.pubs.get(index).open;
        String closeTime = PubDataAccessor.pubs.get(index).close;
        return openTime.substring(0,openTime.length()-3) + " " + "-" + " " + closeTime.substring(0,openTime.length()-3);
    }
    public static String getOpen(int index) {
        return PubDataAccessor.pubs.get(index).open;
    }
    public static String getClose(int index){
        return PubDataAccessor.pubs.get(index).close;
    }
    public static String getAdress(int index){
        return PubDataAccessor.pubs.get(index).street + " " + PubDataAccessor.pubs.get(index).city + " " + PubDataAccessor.pubs.get(index).zip ;
    }
    public static String getCity(int index){
        return PubDataAccessor.pubs.get(index).city;
    }
    public static String getStreet(int index){
        return PubDataAccessor.pubs.get(index).street;
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
    public static int getId(int index){
        return PubDataAccessor.pubs.get(index).id;
    }
    public static int getNrStars(int index){
        return PubDataAccessor.pubs.get(index).nrStars;
    }
    public static int getHasStudentDiscount(int index) {
        return PubDataAccessor.pubs.get(index).hasStudentDiscount;
    }
    public static int getHasFee(int index) {
        return PubDataAccessor.pubs.get(index).hasFee;
    }
    public static String getEventName(int index){
        return PubDataAccessor.pubs.get(index).eventName;
    }
    public static String getEventDescription(int index){
        return PubDataAccessor.pubs.get(index).eventDescription;
    }
    public static int getLocation_id(int index) {
        return PubDataAccessor.pubs.get(index).location_id;
    }
    public static int getEvent_id(int index) {
        return PubDataAccessor.pubs.get(index).event_id;
    }
    public static String getArea(int index){ return PubDataAccessor.pubs.get(index).area; }
}
