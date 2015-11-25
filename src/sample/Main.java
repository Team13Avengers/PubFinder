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
import javafx.scene.input.KeyCode;
import javafx.scene.control.CheckBox;

public class Main extends Application implements EventHandler<javafx.event.ActionEvent> {

    Button btnEnter, btnAdmin;
    Label welcome, warning, noPub;
    public static Scene pubScene, pubPage, adminLoginScene, adminChoiceScene, adminAddScene, adminDeleteScene, adminEditScene,
    editPubScene;

    public int id;
    public Button pubButton;
    String searchStreet = "";
    String searchName = "";
    boolean searchDiscounts;
    int searchAge;
    StackPane pubLayout;
    GridPane pubs;
    Button search;
    TextField searchStreetInput;
    TextField searchNameInput;
    TextField searchAgeInput;
    CheckBox searchStudentDiscounts;
    static Stage primaryStage;

    /* PUB SCENE */
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
    WebView map = new WebView();
    WebEngine browser = map.getEngine();
    StackPane description;
    StackPane rating;
    Label rates;
    /* PUB SCENE */

    /* ADMIN SCENE */
    StackPane adminLoginLayout;
    public int typeId;
    public int locationId;
    public int discount;
    /* ADMIN SCENE */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PubFinder");
        primaryStage.setResizable(false);
        PubDataAccessor.PubDataAccessor();

        /*Welcome scene*/
        StackPane layout = new StackPane();
        layout.setId("welcome");

        /*Button*/
        btnEnter = new Button("ENTER");
        btnEnter.setOnAction(this);
        btnEnter.setId("button");
        btnEnter.setOnAction(e -> primaryStage.setScene(pubScene));
        btnEnter.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                primaryStage.setScene(pubScene);
            }
        });
        /*Button*/

        /*Button*/
        btnAdmin = new Button("\uF013");
        btnAdmin.setId("faIcon");
        btnAdmin.setOnAction(e -> primaryStage.setScene(adminLoginScene));
        /*Button*/

        welcome = new Label("Welcome to PubFinder");
        warning = new Label("Under 18? Don't enter!");
        welcome.setId("welcome_message");
        warning.setId("warning_message");
        layout.getChildren().addAll(btnEnter,welcome,warning, btnAdmin);
        layout.setAlignment(welcome, Pos.TOP_CENTER);
        layout.setAlignment(btnEnter, Pos.CENTER);
        layout.setAlignment(warning, Pos.BOTTOM_CENTER);
        layout.setAlignment(btnAdmin, Pos.TOP_RIGHT);
        Scene welcomeScene = new Scene(layout ,1000, 600);
        welcomeScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
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
                "Gothenburg"));
        ComboBox typeOfPub = new ComboBox(FXCollections.observableArrayList(
                "Sport", "Karaoke", "Club"));
        ComboBox studentDiscount = new ComboBox(FXCollections.observableArrayList(
                "No", "Yes"));
        Button addBtn = new Button("ADD PUB");

        addBtn.setOnAction(e -> {
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
            if (studentDiscount.getSelectionModel().isSelected(0)){
                discount = 0;
            }
            else if (studentDiscount.getSelectionModel().isSelected(1)){
                discount = 1;
            }
            PubDataAccessor.addPub(nameOfPub.getText(),urlImage.getText(),Integer.parseInt(ageOfPub.getText()),Integer.parseInt(openTime.getText() + "0000"),Integer.parseInt(closeTime.getText() + "0000"),
                    streetOfPub.getText(), Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()), typeId, locationId, discount);
            PubDataAccessor.PubDataAccessor();
        });

        addBtn.setId("add_button");
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
        studentDiscount.setTooltip(new Tooltip("We offer student discounts."));
        studentDiscount.setPromptText("We offer student discounts.");
        studentDiscount.setId("comboBox");

        fields.add(nameOfPub, 1, 1);
        fields.add(ageOfPub, 1, 2);
        fields.add(openTime, 1, 3);
        fields.add(closeTime, 1, 4);
        fields.add(lat, 1, 5);
        fields.add(studentDiscount, 1, 6);
        fields.add(streetOfPub, 2, 1);
        fields.add(city, 2, 2);
        fields.add(typeOfPub, 2, 3);
        fields.add(urlImage, 2, 4);
        fields.add(lon, 2, 5);
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
        searchNameInput.setId("input-field");
        searchNameInput.setPromptText("NAME");
        searchStreetInput = new TextField();
        searchStreetInput.setId("input-field");
        searchStreetInput.setPromptText("STREET");
        searchAgeInput = new TextField();
        searchAgeInput.setId("input-field");
        searchAgeInput.setPromptText("AGE");
        CheckBox searchStudentDiscount = new CheckBox("Discounts");
        CheckBox searchBySpecialEvents = new CheckBox("Special Events");
        CheckBox searchWithoutFess = new CheckBox("No Fess");
        ComboBox searchByRating = new ComboBox(FXCollections.observableArrayList(
                "One", "Two", "Three", "Four", "Five"));
        searchByRating.setTooltip(new Tooltip("Rating"));
        searchByRating.setPromptText("Rating");
        pubLayout.setId("pubs");
        search = new Button("SEARCH");
        search.setId("button-search");
        GridPane inputGrid = new GridPane();
        inputGrid.setMaxHeight(100);
        inputGrid.setHgap(10);
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

        searchStudentDiscount.setOnKeyReleased(event4 -> {
            if (event4.getCode() == KeyCode.ENTER) {
                searchForPubs();
            }
        });

        ScrollPane pubScroll = new ScrollPane();
        pubScroll.setId("scroll");
        pubScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        pubs = new GridPane();
        pubScroll.setContent(pubs);
        pubs.setId("pub-grid");
        pubs.setAlignment(Pos.CENTER);
        pubLayout.getChildren().add(pubScroll);
        inputGrid.add(searchNameInput, 1, 1);
        inputGrid.add(searchStreetInput, 2, 1);
        inputGrid.add(searchAgeInput, 3, 1);
        inputGrid.add(searchByRating, 4, 1);
        inputGrid.add(searchStudentDiscount, 5, 1);
        inputGrid.add(searchBySpecialEvents, 6, 1);
        inputGrid.add(searchWithoutFess, 7, 1);
        pubLayout.getChildren().add(inputGrid);
        pubLayout.getChildren().add(search);
        /*pubLayout.getChildren().add(searchNameInput);
        pubLayout.getChildren().add(searchStreetInput);
        pubLayout.getChildren().add(searchAgeInput);*/
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

        back.setOnAction((event) ->{
            primaryStage.setScene(pubScene);
            xPane.getChildren().removeAll(back, description, rating, overlay, pubName, map, star, rates);
            descriptionGrid.getChildren().removeAll(age, open, address, type, discountForStudents);
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
        System.out.println(id);
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void setPubScene(){
        xPane.setStyle("-fx-background-image: url(" + "\"" + Pub.getImage(Pub.getIndexById(this.id)) + "\"" + "); ");
        description = new StackPane();
        rating = new StackPane();
        rating.setId("rating");
        description.setId("description");
        descriptionGrid.setId("description-text");
        pubName = new Label("- " + Pub.getName(Pub.getIndexById(this.id)) + " -");
        //header = new ImageView(Pub.getImage(Pub.getIndexById(this.id)));
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

        map.setMinWidth(1000);
        map.setMaxHeight(350);
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
        descriptionGrid.add(age, 2, 1);
        descriptionGrid.add(open, 3, 1);
        descriptionGrid.add(type, 4, 1);
        descriptionGrid.add(address, 5, 1);

        descriptionGrid.setAlignment(Pos.CENTER);
        description.getChildren().addAll(descriptionGrid);
        xPane.add(rating, 1, 2);
        xPane.add(description, 1, 3);
        xPane.add(map, 1, 4);

        pubName.setId("pub_name");
        xPane.setHalignment(pubName, HPos.CENTER);
        xPane.setValignment(pubName, VPos.TOP);
        xPane.setValignment(back, VPos.TOP);
        xPane.setHalignment(star, HPos.RIGHT);
        xPane.setValignment(star, VPos.TOP);

        xPane.add(star, 1, 1);
        xPane.add(back, 1, 1);
        back.setId("button-logout");
        header.setFitWidth(1000);
        header.setPreserveRatio(true);

        int rate = PubDataAccessor.checkRate(this.id);
        star.setText(rate + " \uF08A");
    }
    public void searchForPubs(){
        pubLayout.getChildren().remove(noPub);
        System.out.println("Search was pressed");
        int y = 1;
        int x = 1;
        searchName = searchNameInput.getText();
        searchStreet = searchStreetInput.getText();
        if (!searchAgeInput.getText().equals("")){
            searchAge = Integer.valueOf(searchAgeInput.getText());
        }
        else searchAge = 100;

        pubs.getChildren().clear();
        for (Pub pub: PubDataAccessor.pubs){
            if (pub.name != null && (pub.name.toLowerCase().contains(searchName.toLowerCase()))
            		&& pub.street != null && (pub.street.toLowerCase().contains(searchStreet.toLowerCase()))
                        && pub.age <= searchAge)
                  // && pub.hasStudentDiscount == 1 && searchStudentDiscounts.isSelected());


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
        if (pubs.getChildren().size() == 0){
            pubLayout.getChildren().add(noPub);
            noPub.setId("nopubs_message");
        }

        /* new elements */

        pubs.setHgap(30);
    }
}
