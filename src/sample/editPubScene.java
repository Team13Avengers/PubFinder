package sample;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Created by Marco on 2015-11-19.
 */
public class editPubScene {
    public static Scene editPubScene;
    static StackPane editLayout = new StackPane();
    public static int pubID;
    public static int locationId;
    public static int typeId;
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
                "Gothenburg"));
        typeOfPub = new ComboBox(FXCollections.observableArrayList(
                "Sport", "Karaoke", "Club"));
        Button editBtn = new Button("EDIT PUB");

        editBtn.setOnAction(e -> {
            if (city.getSelectionModel().isSelected(0)){
                locationId = 0;
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

            /* EDIT PUB SQL QUERY........*/
            String pubNameTmp = nameOfPub.getText();
            pubID = adminEditScene.editId;
            PubDataAccessor.editPub(pubNameTmp,urlImage.getText(),Integer.parseInt(ageOfPub.getText()),Integer.parseInt(openTime.getText() + "0000"),Integer.parseInt(closeTime.getText() + "0000"),
                    streetOfPub.getText(), Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()), typeId, locationId, pubID );
            PubDataAccessor.PubDataAccessor();

        });

        editBtn.setId("add_button");
        nameOfPub.setId("add_fields");
        nameOfPub.setPromptText("Name of pub");

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

        city.setTooltip(new Tooltip("Select the city"));
        city.setPromptText("Select the city");
        city.setId("comboBox");
        typeOfPub.setTooltip(new Tooltip("Select the type of pub"));
        typeOfPub.setPromptText("Select the type of pub");
        typeOfPub.setId("comboBox");

        fields.add(nameOfPub, 1, 1);
        fields.add(ageOfPub, 1, 2);
        fields.add(openTime, 1, 3);
        fields.add(closeTime, 1, 4);
        fields.add(lat, 1, 5);
        fields.add(streetOfPub, 2, 1);
        fields.add(city, 2, 2);
        fields.add(typeOfPub, 2, 3);
        fields.add(urlImage, 2, 4);
        fields.add(lon, 2, 5);
        fields.add(editBtn, 2, 6);
        /*INPUT FIELDS*/


        backToAdmin.setId("button-logout");
        backToAdmin.setOnAction(e -> {
            Main.primaryStage.setScene(Main.adminEditScene);
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

        if (Pub.getType(Pub.getIndexById(thisID)).equals("Sport")){
            typeOfPub.getSelectionModel().select(0);
        }
        else if (Pub.getType(Pub.getIndexById(thisID)).equals("Karaoke")){
            typeOfPub.getSelectionModel().select(1);
        }
        else
            typeOfPub.getSelectionModel().select(2);

        if (Pub.getCity(Pub.getIndexById(thisID)).equals("Gothenburg")){
            city.getSelectionModel().select(0);
        }

        editLayout.getChildren().addAll(fields, backToAdmin, editLabel);
    }
    public static void deleteComponents(){
        editLayout.getChildren().removeAll(fields,backToAdmin, editLabel);
    }
}
