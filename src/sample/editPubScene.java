package sample;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ComboBox;

/**
 * Created by Marco on 2015-11-19.
 */
public class editPubScene {
    public static Scene editPubScene;
    static StackPane editLayout = new StackPane();
    public static int pubID;
    public static int locationId;
    public static int eventId;
    public static int typeId;
    public static int discount;
    public static int fee;
    public static int ratingStars;
    public static Label editLabel;
    public static Button backToAdmin;
    public static GridPane fields;
    public static TextField nameOfPub;
    public static TextField ageOfPub;
    public static TextField openTime;
    public static TextField closeTime;
    public static TextField streetOfPub;
    public static TextField urlImage;
    public static TextField lat;
    public static TextField lon;
    public static ComboBox city;
    public static ComboBox typeOfPub;
    public static ComboBox Rating;
    public static ComboBox studentDiscount;
    public static ComboBox pubFee;
    public static TextField nameOfevent;
    public static TextField descriptionOfevent;





    public static void editPubScene(){
        fields = new GridPane();
        fields.setId("fields");
        backToAdmin = new Button("BACK");
        editLabel = new Label("");

        /*INPUT FIELDS*/
        nameOfPub = new TextField();
        ageOfPub = new TextField();
        openTime = new TextField();
        closeTime = new TextField();
        streetOfPub = new TextField();
        urlImage = new TextField();
        lat = new TextField();
        lon = new TextField();
        city = new ComboBox(FXCollections.observableArrayList(
                "Avenyn", "Linné", "Haga", "Järntorget", "Magasinsgatan", "Vasastaden", "Gamlestaden", "Heden", "Masthugget", "Stigberget", "Other"));
        typeOfPub = new ComboBox(FXCollections.observableArrayList(
                "Sport", "Karaoke", "Club"));
        Rating = new ComboBox(FXCollections.observableArrayList(
                "\uF005", "\uF005\uF005", "\uF005\uF005\uF005", "\uF005\uF005\uF005\uF005", "\uF005\uF005\uF005\uF005\uF005"));
        studentDiscount = new ComboBox(FXCollections.observableArrayList(
                "No", "Yes"));
        pubFee = new ComboBox(FXCollections.observableArrayList(
                "No", "Yes"));
        nameOfevent = new TextField();
        descriptionOfevent = new TextField();
        Button editBtn = new Button("EDIT PUB");

        editBtn.setOnAction(e -> {
            if (city.getSelectionModel().isSelected(0)){
                locationId = 0;
            }
            else if (city.getSelectionModel().isSelected(1)){
                locationId = 2;
            }
            else if (city.getSelectionModel().isSelected(2)){
                locationId = 3;
            }
            else if (city.getSelectionModel().isSelected(3)){
                locationId = 4;
            }
            else if (city.getSelectionModel().isSelected(4)){
                locationId = 5;
            }
            else if (city.getSelectionModel().isSelected(5)){
                locationId = 6;
            }
            else if (city.getSelectionModel().isSelected(6)){
                locationId = 7;
            }
            else if (city.getSelectionModel().isSelected(7)){
                locationId = 8;
            }
            else if (city.getSelectionModel().isSelected(8)){
                locationId = 9;
            }
            else if (city.getSelectionModel().isSelected(9)){
                locationId = 10;
            }
            else if (city.getSelectionModel().isSelected(10)){
                locationId = 11;
            }
            if (typeOfPub.getSelectionModel().isSelected(0)){
                typeId = 0;
            }
            else if (typeOfPub.getSelectionModel().isSelected(1)){
                typeId = 1;
            }
            else if (typeOfPub.getSelectionModel().isSelected(2)){
                typeId = 2;
            }
            if (studentDiscount.getSelectionModel().isSelected(0)){
                discount = 0;
            }
            else if (studentDiscount.getSelectionModel().isSelected(1)){
                discount = 1;
            }
            if (pubFee.getSelectionModel().isSelected(0)){
                fee = 0;
            }
            else if (pubFee.getSelectionModel().isSelected(1)){
                fee = 1;
            }
            if (Rating.getSelectionModel().isSelected(0)){
                ratingStars = 1;

            }
            else if (Rating.getSelectionModel().isSelected(1)){
                ratingStars = 2;

            }
            else if (Rating.getSelectionModel().isSelected(2)) {
                ratingStars = 3;

            }
            else if (Rating.getSelectionModel().isSelected(3)) {
                ratingStars = 4;

            }
            else if (Rating.getSelectionModel().isSelected(4)){
                ratingStars = 5;

            }
            /*
            if(nameOfevent.getText().isEmpty()){
                event = 0;
            }
            */
            /* EDIT PUB SQL QUERY........*/
            String pubNameTmp = nameOfPub.getText();
            pubID = adminEditScene.editId;
            eventId = adminEditScene.editEventId;
            System.out.println(eventId);
            PubDataAccessor.editEvent(nameOfevent.getText(), descriptionOfevent.getText(), eventId);
            PubDataAccessor.editPub(pubNameTmp,urlImage.getText(),Integer.parseInt(ageOfPub.getText()),Integer.parseInt(openTime.getText() + "0000"),Integer.parseInt(closeTime.getText() + "0000"),
                    streetOfPub.getText(), Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()), ratingStars, typeId, locationId, eventId, pubID, discount, fee);
            PubDataAccessor.PubDataAccessor();

        });

        editBtn.setId("add_button");
        nameOfPub.setId("add_fields");
        nameOfPub.setPromptText("Name of pub");
        Rating.setId("ratingStars");
        Rating.setPromptText("Rating of the pub");
        ageOfPub.setId("add_fields");
        ageOfPub.setPromptText("Age limit of the pub");
        openTime.setId("add_fields");
        openTime.setPromptText("Time when the pub opens");
        closeTime.setId("add_fields");
        closeTime.setPromptText("Time when the pub closes");
        streetOfPub.setId("add_fields");
        streetOfPub.setPromptText("The street");
        city.setTooltip(new Tooltip(""));
        urlImage.setId("add_fields");
        urlImage.setPromptText("URL to image for pub");
        lat.setId("add_fields");
        lat.setPromptText("Latitude of pub");
        lon.setId("add_fields");
        lon.setPromptText("Longitude of pub");

        city.setTooltip(new Tooltip("Select the Area"));
        city.setPromptText("Select the Area");
        city.setId("comboBox");
        typeOfPub.setTooltip(new Tooltip("Select the type of pub"));
        typeOfPub.setPromptText("Select the type of pub");
        typeOfPub.setId("comboBox");
        studentDiscount.setTooltip(new Tooltip("We offer student discounts."));
        studentDiscount.setPromptText("We offer student discounts.");
        studentDiscount.setId("comboBox");
        pubFee.setTooltip(new Tooltip("Entrance Fee"));
        pubFee.setPromptText("Entrance Fee");
        pubFee.setId("comboBox");
        nameOfevent.setId("add_fields");
        descriptionOfevent.setId("add_fields");
        nameOfevent.setPromptText("Special event name");
        descriptionOfevent.setPromptText("Description of the event");

        fields.add(nameOfPub, 0, 1);
        fields.add(ageOfPub, 0, 2);
        fields.add(openTime, 0, 3);
        fields.add(closeTime, 0, 4);
        fields.add(streetOfPub, 0, 5);
        fields.add(studentDiscount, 1, 1);
        fields.add(pubFee, 1, 2);
        fields.add(city, 1, 3);
        fields.add(Rating,1,4);
        fields.add(typeOfPub, 1, 5);
        fields.add(urlImage, 2, 1);
        fields.add(lat, 2, 2);
        fields.add(lon, 2, 3);
        fields.add(nameOfevent ,2,4);
        fields.add(descriptionOfevent,2,5);
        fields.add(editBtn, 2, 6);




        backToAdmin.setId("button-logout");
        backToAdmin.setOnAction(e -> {
            Main.primaryStage.setScene(Main.adminEditScene);
            System.out.println("back button ");
        });

        fields.setAlignment(Pos.CENTER);
        editLayout.setAlignment(backToAdmin, Pos.TOP_LEFT);

        editLayout.setId("welcome");

        updateEditScene();

        editPubScene = new Scene(editLayout, 1000, 600);
        editPubScene.getStylesheets().addAll(Main.class.getResource("style.css").toExternalForm());
    }
    public static void updateEditScene(){
        int thisID = adminEditScene.editId;
        editLabel = new Label("Edit " + Pub.getName(Pub.getIndexById(thisID)));
        editLabel.setId("login_message");
        editLayout.setAlignment(editLabel, Pos.TOP_CENTER);

        /*Inputs*/
        nameOfPub.setText(Pub.getName(Pub.getIndexById(thisID)));
        ageOfPub.setText("" + Pub.getAge(Pub.getIndexById(thisID)));
        openTime.setText(Pub.getOpen(Pub.getIndexById(thisID)).substring(0, Pub.getOpen(Pub.getIndexById(thisID)).length()-6));
        closeTime.setText(Pub.getClose(Pub.getIndexById(thisID)).substring(0, Pub.getClose(Pub.getIndexById(thisID)).length()-6));
        lat.setText("" + Pub.getLat(Pub.getIndexById(thisID)));
        lon.setText("" + Pub.getLon(Pub.getIndexById(thisID)));
        lat.setText("" + Pub.getLat(Pub.getIndexById(thisID)));
        streetOfPub.setText(Pub.getStreet(Pub.getIndexById(thisID)));
        urlImage.setText(Pub.getImage(Pub.getIndexById(thisID)));
        nameOfevent.setText(Pub.getEventName(Pub.getIndexById(thisID)));
        descriptionOfevent.setText(Pub.getEventDescription(Pub.getIndexById(thisID)));

        if (Pub.getType(Pub.getIndexById(thisID)).equals("Sport")){
            typeOfPub.getSelectionModel().select(0);
        }
        else if (Pub.getType(Pub.getIndexById(thisID)).equals("Karaoke")){
            typeOfPub.getSelectionModel().select(1);
        }
        else
            typeOfPub.getSelectionModel().select(2);

        if (Pub.getArea(Pub.getIndexById(thisID)).equals("Avenyn")){
            city.getSelectionModel().select(0);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Linné")){
            city.getSelectionModel().select(1);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Haga")){
            city.getSelectionModel().select(2);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Järntorget")){
            city.getSelectionModel().select(3);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Magasinsgatan")){
            city.getSelectionModel().select(4);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Vasastaden")){
            city.getSelectionModel().select(5);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Gamlestaden")){
            city.getSelectionModel().select(6);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Heden")){
            city.getSelectionModel().select(7);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Masthugget")){
            city.getSelectionModel().select(8);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Stigberget")){
            city.getSelectionModel().select(9);
        }
        else if (Pub.getArea(Pub.getIndexById(thisID)).equals("Other")){
            city.getSelectionModel().select(10);
        }

        if (Pub.getHasStudentDiscount(Pub.getIndexById(thisID)) == 0){
            studentDiscount.getSelectionModel().select(0);
        }
        else {
            studentDiscount.getSelectionModel().select(1);
        }
        if (Pub.getHasFee(Pub.getIndexById(thisID)) == 0){
            pubFee.getSelectionModel().select(0);
        }
        else {
            pubFee.getSelectionModel().select(1);
        }
        if (Pub.getNrStars(Pub.getIndexById(thisID))==(1)){
            Rating.getSelectionModel().select(0);
        }
        else if (Pub.getNrStars(Pub.getIndexById(thisID))==(2)){
            Rating.getSelectionModel().select(1);
        }
        else if (Pub.getNrStars(Pub.getIndexById(thisID))==(3)){
            Rating.getSelectionModel().select(2);
        }
        else if (Pub.getNrStars(Pub.getIndexById(thisID))==(4)){
            Rating.getSelectionModel().select(3);
        }
        else if (Pub.getNrStars(Pub.getIndexById(thisID))==(5)) {
            Rating.getSelectionModel().select(4);
        }


        editLayout.getChildren().addAll(fields, backToAdmin, editLabel);
    }
    public static void deleteComponents(){
        editLayout.getChildren().removeAll(fields,backToAdmin, editLabel);
    }
}
