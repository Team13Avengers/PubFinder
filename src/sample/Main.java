package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Random;

import javafx.scene.input.KeyCode;
import javafx.scene.control.CheckBox;

public class Main extends Application implements EventHandler<javafx.event.ActionEvent> {

    Label noPub;
    public static Scene pubScene, pubPage, adminLoginScene, adminChoiceScene,
            adminAddScene, adminDeleteScene, adminEditScene, editPubScene, welcomeScene;
    public int id;
    public Button pubButton;
    public Button randomPub;
    String searchStreet = "";
    String searchName = "";
    boolean searchEvent = false;
    int searchAge;
    StackPane pubLayout;
    GridPane pubs;
    Button search;
    TextField searchStreetInput;
    TextField searchNameInput;
    TextField searchAgeInput;
    TextField nameOfevent;
    TextField descriptionOfevent;

    static Stage primaryStage;

    /* PUB SCENE */
    public int numberofStars = 0;
    public int area =255;
    public int area_checker = 2;
    ImageView header = new ImageView("http://www.thaizeit.de/uploads/tx_thaizeit2/Club_808_02.jpg");
    GridPane xPane = new GridPane();
    GridPane descriptionGrid = new GridPane();
    Button back = new Button("Back");
    Button star = new Button();
    javafx.scene.shape.Rectangle overlay = new javafx.scene.shape.Rectangle();
    Label pubName = new Label();
    Label open = new Label();
    Label age = new Label();
    Label address = new Label();
    Label type = new Label();
    Label discountForStudents = new Label();
    Label entranceFees = new Label();
    WebView map = new WebView();
    WebEngine browser = map.getEngine();
    StackPane description;
    StackPane rating;
    StackPane events;
    Label eventLabel = new Label("Events");
    Label rates;
    Label eventDescription = new Label("rgjoeoioejqew efq wfe qwfe ew qfq wfqew");
    Label eventName = new Label("Karaoke");
    StackPane eventPane = new StackPane();
    GridPane eventDescriptionGrid = new GridPane();
    GridPane eventGrid = new GridPane();
    /* PUB SCENE */

    /* ADMIN SCENE */
    StackPane adminLoginLayout;
    public int typeId;
    public int locationId;
    public int discount;
    public int fee = 2;
    public int eventId=0;
    public int ratingstarts =1;
    /* ADMIN SCENE */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PubFinder");
        primaryStage.setResizable(false);
        PubDataAccessor.PubDataAccessor();

        /*Welcome scene*/
        sample.welcomeScene.welcomeScene();
        welcomeScene = sample.welcomeScene.welcomeScene;
        /*Welcome scene*/


        /*Admin login scene*/
        adminLoginLayout = new StackPane();
        adminLoginLayout.setId("welcome");
        GridPane login = new GridPane();
        login.setAlignment(Pos.TOP_CENTER);
        Label loginLabel = new Label("Login to admin panel");
        Label error = new Label("Your credentials are invalid. Please try again!");

        loginLabel.setId("login_message");
        login.setHalignment(error,HPos.CENTER);
        error.setId("error");

        TextField username = new TextField();
        username.setId("login_fields");
        login.setHalignment(username,HPos.CENTER);
        username.setPromptText("USERNAME");
        PasswordField password = new PasswordField();
        password.setId("login_fields");
        login.setHalignment(password, HPos.CENTER);
        password.setPromptText("PASSWORD");

        Button loginButton = new Button("LOGIN");
        loginButton.setId("login_button");
        login.setHalignment(loginButton, HPos.CENTER);

        Button backBtn = new Button("BACK");
        backBtn.setId("adminButton");
        login.setHalignment(backBtn, HPos.CENTER);

        backBtn.setOnAction(e -> {
            primaryStage.setScene(welcomeScene);
        });

        login.add(loginLabel,1,1);
        login.add(username,1,2);
        login.add(password,1,3);
        login.add(loginButton,1,4);
        login.add(backBtn, 1, 5);

        loginButton.setOnAction(e -> {
            if (Objects.equals(username.getText(), "admin") && Objects.equals(password.getText(), "password")){
                username.clear();
                password.clear();
                primaryStage.setScene(adminChoiceScene);
            }
            else
            if (!login.getChildren().contains(error)) {
                login.add(error, 1, 6);
            }
        });

        adminLoginLayout.getChildren().addAll(login);
        adminLoginScene = new Scene(adminLoginLayout ,1000, 600);
        adminLoginScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        /*Admin login scene*/

        /*Admin choice scene*/
        sample.adminDeleteScene.adminDeleteScene();
        sample.adminEditScene.adminEditScene();
        sample.editPubScene.editPubScene();
        adminDeleteScene = sample.adminDeleteScene.deleteScene;
        adminEditScene = sample.adminEditScene.editScene;
        editPubScene = sample.editPubScene.editPubScene;

        StackPane adminLayout = new StackPane();
        adminLayout.setId("welcome");
        GridPane adminBtnGrid = new GridPane();
        Button add = new Button("Add pub");
        Button delete = new Button("Delete pub");
        Button edit = new Button("Edit pub");
        Label choiceLabel = new Label("Options");

        add.setOnAction(e -> {
            primaryStage.setScene(adminAddScene);
        });
        delete.setOnAction(e -> {
            PubDataAccessor.clearCache();
            sample.adminDeleteScene.showPubsToDelete();
            primaryStage.setScene(adminDeleteScene);
        });
        edit.setOnAction(e -> {
            PubDataAccessor.clearCache();
            sample.adminEditScene.showPubsToEdit();
            primaryStage.setScene(adminEditScene);
        });

        add.setId("admin_button");
        delete.setId("admin_button");
        edit.setId("admin_button");

        Button logOut = new Button("LOG OUT");
        logOut.setOnAction(e -> {
            primaryStage.setScene(welcomeScene);
        });
        logOut.setId("button-logout");
        choiceLabel.setId("login_message");

        adminBtnGrid.add(add, 1, 1);
        adminBtnGrid.add(delete, 2, 1);
        adminBtnGrid.add(edit, 3, 1);
        adminBtnGrid.setAlignment(Pos.CENTER);

        adminLayout.setAlignment(Pos.CENTER);
        adminLayout.setAlignment(choiceLabel,Pos.TOP_CENTER);
        adminLayout.getChildren().addAll(adminBtnGrid,choiceLabel,logOut);

        adminLayout.setAlignment(logOut, Pos.TOP_LEFT);

        adminChoiceScene = new Scene(adminLayout, 1000, 600);
        adminChoiceScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        /*Admin choice scene*/

        /*Admin add scene*/
        StackPane addLayout = new StackPane();
        GridPane fields = new GridPane();
        fields.setId("fields");
        Button backToAdmin = new Button("BACK");
        Label addLabel = new Label("Add a new pub");
        addLabel.setId("login_message");

        /*INPUT FIELDS*/
        TextField nameOfPub = new TextField();
        TextField ageOfPub = new TextField();
        TextField openTime = new TextField();
        TextField closeTime = new TextField();
        TextField streetOfPub = new TextField();
        TextField urlImage = new TextField();
        TextField lat = new TextField();
        TextField lon = new TextField();
        ComboBox city = new ComboBox(FXCollections.observableArrayList(
                "Avenyn", "Linné", "Haga", "Järntorget", "Magasinsgatan", "Vasastaden", "Gamlestaden", "Heden", "Masthugget", "Stigberget", "Other"));
        ComboBox typeOfPub = new ComboBox(FXCollections.observableArrayList(
                "Sport", "Karaoke", "Club"));
        ComboBox Rating = new ComboBox(FXCollections.observableArrayList(
                "\uF005", "\uF005\uF005", "\uF005\uF005\uF005", "\uF005\uF005\uF005\uF005", "\uF005\uF005\uF005\uF005\uF005"));
        ComboBox studentDiscount = new ComboBox(FXCollections.observableArrayList(
                "No", "Yes"));
        ComboBox pubFee = new ComboBox(FXCollections.observableArrayList(
                "No", "Yes"));
        nameOfevent = new TextField();
        descriptionOfevent = new TextField();

        Button addBtn = new Button("ADD PUB");

        addBtn.setOnAction(e -> {
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
                fee = 1;
            }
            else if (pubFee.getSelectionModel().isSelected(1)){
                fee = 0;
            }
            if (Rating.getSelectionModel().isSelected(0)){
                ratingstarts = 1;
            }
            else if (Rating.getSelectionModel().isSelected(1)){
                ratingstarts = 2;
            }
            else if (Rating.getSelectionModel().isSelected(2)) {
                ratingstarts = 3;
            }
            else if (Rating.getSelectionModel().isSelected(3)) {
                ratingstarts = 4;
            }
            else if (Rating.getSelectionModel().isSelected(4)){
                ratingstarts = 5;
            }

            PubDataAccessor.addEvent(nameOfevent.getText(), descriptionOfevent.getText());

            eventId = PubDataAccessor.addingEventId();

            System.out.println(eventId);
            PubDataAccessor.addPub(nameOfPub.getText(),urlImage.getText(),Integer.parseInt(ageOfPub.getText()),Integer.parseInt(openTime.getText() + "0000"),Integer.parseInt(closeTime.getText() + "0000"),
                    streetOfPub.getText(), Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()), ratingstarts, typeId, locationId, eventId, discount, fee);
            PubDataAccessor.PubDataAccessor();
        });

        addBtn.setId("add_button");
        nameOfPub.setId("add_fields");
        nameOfPub.setPromptText("Name of pub");
        Rating.setId("addRatingStars");
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
        nameOfevent.setId("add_fields");
        descriptionOfevent.setId("add_fields");
        nameOfevent.setPromptText("Special event name");
        descriptionOfevent.setPromptText("Description of the event");

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
        fields.add(addBtn, 2, 6);
        /*INPUT FIELDS*/


        backToAdmin.setId("button-logout");
        backToAdmin.setOnAction(e -> {
            primaryStage.setScene(adminChoiceScene);
        });

        fields.setAlignment(Pos.CENTER);
        addLayout.setAlignment(backToAdmin, Pos.TOP_LEFT);
        addLayout.setAlignment(addLabel, Pos.TOP_CENTER);

        addLayout.setId("welcome");


        addLayout.getChildren().addAll(fields,backToAdmin, addLabel);
        adminAddScene = new Scene(addLayout, 1000, 600);
        adminAddScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        /*Admin add scene*/

        /*Pub button scene*/
        pubLayout = new StackPane();
        searchNameInput = new TextField();
        searchNameInput.setId("search-field");
        searchNameInput.setPromptText("NAME");
        searchStreetInput = new TextField();
        searchStreetInput.setId("search-field");
        searchStreetInput.setPromptText("STREET");
        searchAgeInput = new TextField();
        searchAgeInput.setId("search-field");
        searchAgeInput.setPromptText("AGE");
        CheckBox searchStudentDiscount = new CheckBox("DISCOUNTS");
        CheckBox searchBySpecialEvents = new CheckBox("EVENTS");
        CheckBox searchWithoutFees = new CheckBox("NO FEES");
        searchStudentDiscount.setId("check-search");
        searchBySpecialEvents.setId("check-search");
        searchWithoutFees.setId("check-search");
        ComboBox searchByRating = new ComboBox(FXCollections.observableArrayList(
                "\uF005", "\uF005\uF005", "\uF005\uF005\uF005", "\uF005\uF005\uF005\uF005", "\uF005\uF005\uF005\uF005\uF005"));
        searchByRating.setTooltip(new Tooltip("RATING"));
        searchByRating.setPromptText("RATING");
        searchByRating.setId("combo-search");
        ComboBox searchByArea = new ComboBox(FXCollections.observableArrayList(
                "All", "Avenyn", "Linné", "Haga", "Järntorget", "Magasinsgatan", "Vasastaden", "Gamlestaden", "Heden", "Masthugget", "Stigberget", "Other"));
        searchByArea.setTooltip(new Tooltip("AREA"));
        searchByArea.setPromptText("AREA");
        searchByArea.setId("combo-search");
        pubLayout.setId("pubs");
        search = new Button("SEARCH");
        search.setId("button-search");
        GridPane inputGrid = new GridPane();
        inputGrid.setMaxHeight(100);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setId("searchGrid");
        pubLayout.setAlignment(inputGrid, Pos.TOP_LEFT);
        pubLayout.setAlignment(search, Pos.TOP_RIGHT);

        search.setOnAction(e -> searchForPubs());
        searchNameInput.setOnKeyReleased(event1 -> {
            if (event1.getCode() == KeyCode.ENTER) {
                searchForPubs();
            }
        });
        searchStreetInput.setOnKeyReleased(event2 -> {
            if (event2.getCode() == KeyCode.ENTER) {
                searchForPubs();
            }
        });

        searchAgeInput.setOnKeyReleased(event3 -> {
            if (event3.getCode() == KeyCode.ENTER) {
                searchForPubs();
            }
        });



        searchStudentDiscount.setOnAction(event4 -> {
            if (searchStudentDiscount.isSelected()) {
                discount = 1;
            }
            if (!searchStudentDiscount.isSelected()) {
                discount = 0;
            }
        });

        searchWithoutFees.setOnAction(event5 -> {
            if(searchWithoutFees.isSelected()) {
                fee = 0;
            }
            if(!searchWithoutFees.isSelected()) {
                fee = 1;
            }
        });

        searchBySpecialEvents.setOnAction(event6 ->{
            if (searchBySpecialEvents.isSelected()) {

                searchEvent = true;
            }
            if (!searchBySpecialEvents.isSelected()) {
                searchEvent = false;
            }
        });




        searchByRating.setOnAction(event7 -> {
            if (searchByRating.getSelectionModel().isSelected(0)){
                numberofStars = 1;

            }
            else if (searchByRating.getSelectionModel().isSelected(1)){
                numberofStars = 2;

            }
            else if (searchByRating.getSelectionModel().isSelected(2)) {
                numberofStars = 3;

            }
            else if (searchByRating.getSelectionModel().isSelected(3)) {
                numberofStars = 4;

            }
            else if (searchByRating.getSelectionModel().isSelected(4)){
                numberofStars = 5;

            }
        });
        searchByArea.setOnAction(event8 -> {
            if (searchByArea.getSelectionModel().isSelected(0)){
                area_checker = 2;
            }
            if (searchByArea.getSelectionModel().isSelected(1)){
                area = 0;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(2)){
                area = 2;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(3)) {
                area = 3;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(4)) {
                area = 4;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(5)){
                area = 5;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(6)){
                area = 6;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(7)) {
                area = 7;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(8)) {
                area = 8;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(9)){
                area = 9;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(10)) {
                area = 10;
                area_checker = 1;
            }
            else if (searchByArea.getSelectionModel().isSelected(11)) {
                area = 11;
                area_checker = 1;
            }
        });


        ScrollPane pubScroll = new ScrollPane();
        pubScroll.setId("scroll");
        pubScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pubScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        pubs = new GridPane();
        pubScroll.setContent(pubs);
        pubs.setId("pub-grid");
        pubs.setAlignment(Pos.CENTER);
        pubLayout.getChildren().add(pubScroll);
        inputGrid.add(searchNameInput, 1, 1);
        inputGrid.add(searchStreetInput, 2, 1);
        inputGrid.add(searchAgeInput, 3, 1);
        inputGrid.add(searchByRating, 4, 1);
        inputGrid.add(searchByArea, 5, 1);
        inputGrid.add(searchStudentDiscount, 6, 1);
        inputGrid.add(searchBySpecialEvents, 7, 1);
        inputGrid.add(searchWithoutFees, 8, 1);
        pubLayout.getChildren().add(inputGrid);
        pubLayout.getChildren().add(search);
        noPub = new Label("No pubs found");
        searchForPubs();

        pubScene = new Scene(pubLayout ,1000, 600);
        pubScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        /*Pub button scene*/

        /*Pub scene*/
        ScrollPane pubPageLayout = new ScrollPane();
        pubPageLayout.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pubPageLayout.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pubPageLayout.setFitToWidth(true);
        pubPageLayout.setContent(xPane);
        xPane.setId("pubScene");
        pubPageLayout.setId("gej");
        star.setId("starButton");
        eventLabel.setId("eventLabel");

        /*Items*/
        xPane.add(back, 1, 1);
        back.setId("button-logout");


        back.setOnAction((event) ->{
            primaryStage.setScene(pubScene);
            xPane.getChildren().removeAll(description, rating, overlay, pubName, map, star, rates, events);
            descriptionGrid.getChildren().removeAll(age, open, address, type, discountForStudents, entranceFees);
            events.getChildren().removeAll(eventDescriptionGrid);
            eventDescriptionGrid.getChildren().removeAll(eventLabel, eventPane);
            eventPane.getChildren().removeAll(eventGrid);
            eventGrid.getChildren().removeAll(eventName, eventDescription);

            star.setText("0 \uF08A");
            star.setStyle("#starButton{-fx-text-fill: #fff;}  #starButton:hover{-fx-text-fill: #fff;}");
        });
        star.setOnAction(event -> {
            int rate = PubDataAccessor.checkRate(this.id);
            int rateUpdate = rate+1;
            star.setText((rateUpdate) + " \uF004");
            PubDataAccessor.updateRate(this.id);
            star.setStyle("-fx-text-fill: #731a2b;");
        });

        overlay.setHeight(header.getFitHeight());
        overlay.setWidth(header.getFitWidth() + 24);
        overlay.setX(0);
        overlay.setY(0);
        overlay.fillProperty().set(javafx.scene.paint.Color.rgb(115, 26, 43, 0.3));

        pubPage = new Scene(pubPageLayout ,1000, 600);
        pubPage.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        /*Pub scene*/

        primaryStage.setScene(welcomeScene);
        primaryStage.show();
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(javafx.event.ActionEvent event) {

    }
    public void idOfButton(int idOfButton){
        this.id = idOfButton;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void setPubScene(){
        xPane.setStyle("-fx-background-image: url(" + "\"" + Pub.getImage(Pub.getIndexById(this.id)) + "\"" + "); ");
        description = new StackPane();
        events = new StackPane();
        rating = new StackPane();
        rating.setId("rating");
        description.setId("description");
        descriptionGrid.setId("description-text");
        pubName = new Label("- " + Pub.getName(Pub.getIndexById(this.id)) + " -");
        age = new Label(Pub.getAge(Pub.getIndexById(this.id)) + " years \uF000");
        age.setId("infoLabel");
        open = new Label(Pub.getOpening(Pub.getIndexById(this.id)) + " \uF017");
        open.setId("infoLabel");
        address = new Label(Pub.getAdress(Pub.getIndexById(this.id)) + " \uF124");
        address.setId("infoLabel");
        type = new Label(Pub.getType(Pub.getIndexById(this.id)) + " \uF005");
        type.setId("infoLabel");
        discountForStudents = new Label(Pub.getHasStudentDiscount(Pub.getIndexById(this.id)) + " \uF02D");
        if (Pub.getHasStudentDiscount(Pub.getIndexById(this.id)) == 1) {
            discountForStudents = new Label("Discounts " + " \uF02D");
        }
        else if (Pub.getHasStudentDiscount(Pub.getIndexById(this.id)) == 0) {
            discountForStudents = new Label("No Discounts " + "\uF02D");
        }
        discountForStudents.setId("infoLabel");

        entranceFees = new Label(Pub.getHasFee(Pub.getIndexById(this.id)) + "\uf153");
        if (Pub.getHasFee(Pub.getIndexById(this.id)) == 1) {
            entranceFees = new Label("Fees " + " \uF153");
        }
        else if (Pub.getHasFee(Pub.getIndexById(this.id)) == 0) {
            entranceFees = new Label("No Fees " + "\uF153");
        }
        entranceFees.setId("infoLabel");

        map.setMinWidth(1000);
        map.setMaxHeight(250);
        browser.load("http://locateme.marcokoivisto.me/?lat=" + Pub.getLat(Pub.getIndexById(this.id)) + "&lon=" + Pub.getLon(Pub.getIndexById(this.id)));

        int nrStars = Pub.getNrStars(Pub.getIndexById(this.id));
        String stars = "";

        for (int i=0; i < nrStars; i++){
            stars += "\uF005 ";
        }

        rates = new Label(stars);
        rates.setId("ratingStars");
        rating.getChildren().add(rates);
        rating.setAlignment(Pos.CENTER);
        xPane.add(pubName, 1, 1);
        descriptionGrid.add(discountForStudents, 1, 1);
        descriptionGrid.add(entranceFees, 2, 1);
        descriptionGrid.add(age, 3, 1);
        descriptionGrid.add(open, 4, 1);
        descriptionGrid.add(type, 5, 1);
        descriptionGrid.add(address, 6, 1);

        descriptionGrid.setAlignment(Pos.CENTER);
        description.getChildren().addAll(descriptionGrid);
        eventDescriptionGrid.add(eventLabel, 1, 1);
        eventDescriptionGrid.add(eventPane, 1, 2);
        eventDescriptionGrid.setHalignment(eventLabel, HPos.CENTER);
        eventDescriptionGrid.setAlignment(Pos.CENTER);

        events.getChildren().addAll(eventDescriptionGrid);

        eventName = new Label(Pub.getEventName(Pub.getIndexById(this.id)));
        eventDescription = new Label(Pub.getEventDescription(Pub.getIndexById(this.id)));
        eventGrid.add(eventName, 1, 1);
        eventGrid.add(eventDescription, 1, 2);
        eventName.setId("eventName");
        eventDescription.setId("eventDescription");
        eventPane.getChildren().add(eventGrid);
        eventGrid.setId("event");

        events.setId("eventField");
        xPane.add(rating, 1, 2);
        xPane.add(description, 1, 3);

        xPane.add(map, 1, 4);
        if (Pub.getEventName(Pub.getIndexById(this.id)) != "") {
            xPane.add(events, 1, 5);
        }


        pubName.setId("pub_name");
        xPane.setHalignment(pubName, HPos.CENTER);
        xPane.setValignment(pubName, VPos.TOP);
        xPane.setValignment(back, VPos.TOP);
        xPane.setHalignment(star, HPos.RIGHT);
        xPane.setValignment(star, VPos.TOP);

        xPane.add(star, 1, 1);
        header.setFitWidth(1000);
        header.setPreserveRatio(true);

        int rate = PubDataAccessor.checkRate(this.id);
        star.setText(rate + " \uF08A");
    }
    public void searchForPubs(){
        pubLayout.getChildren().remove(noPub);
        int y = 1;
        int x = 1;

        searchName = searchNameInput.getText();
        searchStreet = searchStreetInput.getText();

        if (!searchAgeInput.getText().equals("")){
            searchAge = Integer.valueOf(searchAgeInput.getText());
        }
        else searchAge = 100;

        pubs.getChildren().clear();

       /* Random pub search */

        randomPub = new Button("- Random Pub -");
        randomPub.setId("randomPub-button");
        randomPub.setMinWidth(230);
        randomPub.setMinHeight(100);
        Random random = new Random();
        randomPub.setOnAction((event) -> {
            idOfButton(random.nextInt(PubDataAccessor.pubs.size()));
            primaryStage.setScene(pubPage);
            setPubScene();
        });

        randomPub.setAlignment(Pos.CENTER);
        pubs.getChildren().add(randomPub);

        pubs.setRowIndex(randomPub, 1);
        pubs.setColumnIndex(randomPub, 0);

        for (Pub pub: PubDataAccessor.pubs) {

            if (searchEvent == true) {
                if (pub.name != null && (pub.name.toLowerCase().contains(searchName.toLowerCase()))
                        && pub.street != null && (pub.street.toLowerCase().contains(searchStreet.toLowerCase()))
                        && pub.age <= searchAge && pub.nrStars >= numberofStars && pub.hasStudentDiscount >= discount
                        && pub.hasFee <= fee && pub.location_id == area && area_checker != 2 && !pub.eventName.isEmpty())

                {
                    System.out.println("You selected with events");
                    pubButton = new Button("- " + pub.name + " -");
                    pubButton.setId("pub-button");
                    pubButton.setMinWidth(230);
                    pubButton.setMinHeight(100);
                    pubButton.setOnAction((event) -> {
                        idOfButton(pub.id);
                        primaryStage.setScene(pubPage);
                        setPubScene();
                    });
                    pubButton.setStyle("-fx-background-image: url(" + "\"" + pub.picture + "\"" + "); ");
                    pubButton.setAlignment(Pos.CENTER);
                    pubs.getChildren().add(pubButton);

                    pubs.setRowIndex(pubButton, y);
                    pubs.setColumnIndex(pubButton, x);
                    x++;
                } else if (pub.name != null && (pub.name.toLowerCase().contains(searchName.toLowerCase()))
                        && pub.street != null && (pub.street.toLowerCase().contains(searchStreet.toLowerCase()))
                        && pub.age <= searchAge && pub.nrStars >= numberofStars && pub.hasStudentDiscount >= discount
                        && pub.hasFee <= fee && area_checker == 2 && !pub.eventName.isEmpty())
                //pub.nrStars >= numberofStars

                {
                    pubButton = new Button("- " + pub.name + " -");
                    pubButton.setId("pub-button");
                    pubButton.setMinWidth(230);
                    pubButton.setMinHeight(100);
                    pubButton.setOnAction((event) -> {
                        idOfButton(pub.id);
                        primaryStage.setScene(pubPage);
                        setPubScene();
                    });
                    pubButton.setStyle("-fx-background-image: url(" + "\"" + pub.picture + "\"" + "); ");
                    pubButton.setAlignment(Pos.CENTER);
                    pubs.getChildren().add(pubButton);

                    pubs.setRowIndex(pubButton, y);
                    pubs.setColumnIndex(pubButton, x);
                    x++;
                }
            }
            else if (searchEvent == false) {
                if (pub.name != null && (pub.name.toLowerCase().contains(searchName.toLowerCase()))
                        && pub.street != null && (pub.street.toLowerCase().contains(searchStreet.toLowerCase()))
                        && pub.age <= searchAge && pub.nrStars >= numberofStars && pub.hasStudentDiscount >= discount
                        && pub.hasFee <= fee && pub.location_id == area && area_checker != 2)
                //pub.nrStars >= numberofStars

                {
                    System.out.println("You selected with events");
                    pubButton = new Button("- " + pub.name + " -");
                    pubButton.setId("pub-button");
                    pubButton.setMinWidth(230);
                    pubButton.setMinHeight(100);
                    pubButton.setOnAction((event) -> {
                        idOfButton(pub.id);
                        primaryStage.setScene(pubPage);
                        setPubScene();
                    });
                    pubButton.setStyle("-fx-background-image: url(" + "\"" + pub.picture + "\"" + "); ");
                    pubButton.setAlignment(Pos.CENTER);
                    pubs.getChildren().add(pubButton);

                    pubs.setRowIndex(pubButton, y);
                    pubs.setColumnIndex(pubButton, x);
                    x++;
                } else if (pub.name != null && (pub.name.toLowerCase().contains(searchName.toLowerCase()))
                        && pub.street != null && (pub.street.toLowerCase().contains(searchStreet.toLowerCase()))
                        && pub.age <= searchAge && pub.nrStars >= numberofStars && pub.hasStudentDiscount >= discount
                        && pub.hasFee <= fee && area_checker == 2)
                //pub.nrStars >= numberofStars

                {
                    pubButton = new Button("- " + pub.name + " -");
                    pubButton.setId("pub-button");
                    pubButton.setMinWidth(230);
                    pubButton.setMinHeight(100);
                    pubButton.setOnAction((event) -> {
                        idOfButton(pub.id);
                        primaryStage.setScene(pubPage);
                        setPubScene();
                    });
                    pubButton.setStyle("-fx-background-image: url(" + "\"" + pub.picture + "\"" + "); ");
                    pubButton.setAlignment(Pos.CENTER);
                    pubs.getChildren().add(pubButton);

                    pubs.setRowIndex(pubButton, y);
                    pubs.setColumnIndex(pubButton, x);
                    x++;
                }

            }
        }

        if (pubs.getChildren().size() == 0){
            pubLayout.getChildren().add(noPub);
            noPub.setId("nopubs_message");
        }
        /* new elements */

        pubs.setHgap(30);

    }
}
