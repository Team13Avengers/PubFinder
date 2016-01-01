package Pubfinder;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

public class AdminAddScene {
    public static Scene adminAddScene, adminChoiceScene;
    static TextField nameOfevent;
    static TextField descriptionOfevent;
    static StackPane addLayout = new StackPane();
    static GridPane fields = new GridPane();
    static Button backToAdmin = new Button("BACK");
    static Label addLabel = new Label("Add a new pub");
    static Stage primaryStage;

    /*INPUT FIELDS*/
    static int typeId;
    static int locationId;
    static int discount;
    static int fee = 2;
    static int eventId = 0;
    static int ratingStars = 1;
    static TextField nameOfPub = new TextField();
    static TextField ageOfPub = new TextField();
    static TextField openTime = new TextField();
    static TextField closeTime = new TextField();
    static TextField streetOfPub = new TextField();
    static TextField urlImage = new TextField();
    static TextField lat = new TextField();
    static TextField lon = new TextField();
    static ComboBox city = new ComboBox(FXCollections.observableArrayList(
            "Avenyn", "Linné", "Haga", "Järntorget", "Magasinsgatan", "Vasastaden", "Gamlestaden", "Heden", "Masthugget", "Stigberget", "Other"));
    static ComboBox typeOfPub = new ComboBox(FXCollections.observableArrayList("Sport", "Karaoke", "Club"));
    static ComboBox Rating = new ComboBox(FXCollections.observableArrayList(
            "\uF005", "\uF005\uF005", "\uF005\uF005\uF005", "\uF005\uF005\uF005\uF005", "\uF005\uF005\uF005\uF005\uF005"));
    static ComboBox studentDiscount = new ComboBox(FXCollections.observableArrayList(
            "No", "Yes"));
    static ComboBox pubFee = new ComboBox(FXCollections.observableArrayList(
            "No", "Yes"));

    public static void adminAddscene() {
        nameOfevent = new TextField();
        descriptionOfevent = new TextField();
        fields.setId("fields");
        addLabel.setId("login_message");
        Button addBtn = new Button("ADD PUB");

        addBtn.setOnAction(e -> {
            if (isFilled()) {
                if (city.getSelectionModel().isSelected(0)) {
                    locationId = 0;
                }
                else if (city.getSelectionModel().isSelected(1)) {
                    locationId = 2;
                }
                else if (city.getSelectionModel().isSelected(2)) {
                    locationId = 3;
                }
                else if (city.getSelectionModel().isSelected(3)) {
                    locationId = 4;
                }
                else if (city.getSelectionModel().isSelected(4)) {
                    locationId = 5;
                }
                else if (city.getSelectionModel().isSelected(5)) {
                    locationId = 6;
                }
                else if (city.getSelectionModel().isSelected(6)) {
                    locationId = 7;
                }
                else if (city.getSelectionModel().isSelected(7)) {
                    locationId = 8;
                }
                else if (city.getSelectionModel().isSelected(8)) {
                    locationId = 9;
                }
                else if (city.getSelectionModel().isSelected(9)) {
                    locationId = 10;
                }
                else if (city.getSelectionModel().isSelected(10)) {
                    locationId = 11;
                }

                if (typeOfPub.getSelectionModel().isSelected(0)) {
                    typeId = 0;
                } else if (typeOfPub.getSelectionModel().isSelected(1)) {
                    typeId = 1;
                } else if (typeOfPub.getSelectionModel().isSelected(2)) {
                    typeId = 2;
                }
                if (studentDiscount.getSelectionModel().isSelected(0)) {
                    discount = 0;
                } else if (studentDiscount.getSelectionModel().isSelected(1)) {
                    discount = 1;
                }
                if (pubFee.getSelectionModel().isSelected(0)) {
                    fee = 1;
                } else if (pubFee.getSelectionModel().isSelected(1)) {
                    fee = 0;
                }

                if (Rating.getSelectionModel().isSelected(0)) {
                    ratingStars = 1;
                } else if (Rating.getSelectionModel().isSelected(1)) {
                    ratingStars = 2;
                } else if (Rating.getSelectionModel().isSelected(2)) {
                    ratingStars = 3;
                } else if (Rating.getSelectionModel().isSelected(3)) {
                    ratingStars = 4;
                } else if (Rating.getSelectionModel().isSelected(4)) {
                    ratingStars = 5;
                }

                PubDataAccessor.addEvent(nameOfevent.getText(), descriptionOfevent.getText());
                eventId = PubDataAccessor.addingEventId();
                PubDataAccessor.addPub(nameOfPub.getText(), urlImage.getText(), Integer.parseInt(ageOfPub.getText()), Integer.parseInt(openTime.getText() + "0000"), Integer.parseInt(closeTime.getText() + "0000"),
                        streetOfPub.getText(), Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()), ratingStars, typeId, locationId, eventId, discount, fee);
                clear();
                MessageBox.show(primaryStage, "\nThe pub was successfully added to PubFinder.", "Success", MessageBox.OK);
                PubDataAccessor.clearCache();
            } else
                MessageBox.show(primaryStage, "\nPlease fill out all fields which are marked with *", "Error", MessageBox.OK);
        });

        addBtn.setId("add_button");
        nameOfPub.setId("add_fields");
        nameOfPub.setPromptText("Name of pub *");
        Rating.setId("ratingStars");
        Rating.setPromptText("Rating of the pub *");
        ageOfPub.setId("add_fields");
        ageOfPub.setPromptText("Age limit of the pub *");
        openTime.setId("add_fields");
        openTime.setPromptText("Time when the pub opens *");
        closeTime.setId("add_fields");
        closeTime.setPromptText("Time when the pub closes *");
        streetOfPub.setId("add_fields");
        streetOfPub.setPromptText("The street *");
        city.setTooltip(new Tooltip(""));
        urlImage.setId("add_fields");
        urlImage.setPromptText("URL to image for pub *");
        lat.setId("add_fields");
        lat.setPromptText("Latitude of pub *");
        lon.setId("add_fields");
        lon.setPromptText("Longitude of pub *");
        nameOfevent.setId("add_fields");
        descriptionOfevent.setId("add_fields");
        nameOfevent.setPromptText("Special event name");
        descriptionOfevent.setPromptText("Description of the event");

        city.setTooltip(new Tooltip("Select the Area *"));
        city.setPromptText("Select the Area *");
        city.setId("comboBox");
        typeOfPub.setTooltip(new Tooltip("Select the type of pub *"));
        typeOfPub.setPromptText("Select the type of pub *");
        typeOfPub.setId("comboBox");
        studentDiscount.setTooltip(new Tooltip("We offer student discounts *"));
        studentDiscount.setPromptText("We offer student discounts *");
        studentDiscount.setId("comboBox");

        pubFee.setTooltip(new Tooltip("Entrance Fee *"));
        pubFee.setPromptText("Entrance Fee *");
        pubFee.setId("comboBox");


        fields.add(nameOfPub, 0, 1);
        fields.add(ageOfPub, 0, 2);
        fields.add(openTime, 0, 3);
        fields.add(closeTime, 0, 4);
        fields.add(streetOfPub, 0, 5);
        fields.add(studentDiscount, 1, 1);
        fields.add(pubFee, 1, 2);
        fields.add(city, 1, 3);
        fields.add(Rating, 1, 4);
        fields.add(typeOfPub, 1, 5);
        fields.add(urlImage, 2, 1);
        fields.add(lat, 2, 2);
        fields.add(lon, 2, 3);
        fields.add(nameOfevent, 2, 4);
        fields.add(descriptionOfevent, 2, 5);
        fields.add(addBtn, 2, 6);
        /*INPUT FIELDS*/


        backToAdmin.setId("button-logout");
        backToAdmin.setOnAction(e -> {
            primaryStage.setScene(adminChoiceScene);
        });

        fields.setAlignment(Pos.CENTER);
        StackPane.setAlignment(backToAdmin, Pos.TOP_LEFT);
        StackPane.setAlignment(addLabel, Pos.TOP_CENTER);

        addLayout.setId("welcome");


        addLayout.getChildren().addAll(fields, backToAdmin, addLabel);
        adminAddScene = new Scene(addLayout, 1000, 600);
        AdminChoiceScene.adminAddScene = adminAddScene;
        adminAddScene.getStylesheets().addAll(AdminAddScene.class.getResource("style.css").toExternalForm());
    }
    public static void clear() {
        nameOfPub.clear();
        ageOfPub.clear();
        openTime.clear();
        closeTime.clear();
        streetOfPub.clear();
        studentDiscount.setValue(null);
        pubFee.setValue(null);
        city.setValue(null);
        Rating.setValue(null);
        typeOfPub.setValue(null);
        urlImage.clear();
        lat.clear();
        lon.clear();
        nameOfevent.clear();
        descriptionOfevent.clear();
    }
    public static boolean isFilled() {
        if (nameOfPub.getText() != "" && ageOfPub.getText() != "" && openTime.getText() != "" && closeTime.getText() != ""
                && streetOfPub.getText() != "" && urlImage.getText() != "" && lat.getText() != "" && lon.getText() != "" && !studentDiscount.getSelectionModel().isEmpty() && !pubFee.getSelectionModel().isEmpty()
                && !city.getSelectionModel().isEmpty() && !Rating.getSelectionModel().isEmpty() && !typeOfPub.getSelectionModel().isEmpty()
                ) {
            return true;
        } else return false;
    }
}
