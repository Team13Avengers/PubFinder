package Pubfinder;

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
/** Done by Ahmad
 */
import java.util.Random;
/** End of Ahmad's Work
 */
import javafx.scene.input.KeyCode;
/** Done by aseel
 */
import javafx.scene.control.CheckBox;
/** End of Aseel's Work
 */

public class Main extends Application implements EventHandler<javafx.event.ActionEvent> {
    /** Done by marco
     */
    Label noPub;
    public static Scene pubScene, pubPage, adminLoginScene, adminChoiceScene, adminAddScene, adminEditScene,  welcomeScene;
    public int id;
    public Button pubButton;
    /** Done by Ahmad
     */
    public Button randomPub;
    /** End of Ahmad's Work
     */
    String searchStreet = "";
    /** Done by Ahmad
     */
    String searchName = "";
    /** End of Ahmad's Work
     */

    /** Done by Shafiq& Antonino
     */
    boolean searchEvent = false;
    /** End of Shafiq&Antonino's Work
     */

    /** Done by Ahmad
     */
    int searchAge;
    /** End of Ahmad's Work
     */

    StackPane pubLayout;
    GridPane pubs;
    Button search;
    /** Done by Ahmad
     */
    TextField searchStreetInput;
    TextField searchNameInput;
    TextField searchAgeInput;
    /** End of Ahmad's Work
     */
    static Stage primaryStage;

    /* PUB SCENE */
    /** Done by Shafiq& Antonino
     */
    public int numberOfStars = 0;
    /** End of Shafiq&Antonino's Work
     */
    public int area = 255;
    /** Done by Antonino & Aseel
     */
    public int area_checker = 2;
    /** End of Antonino & Aseel's Work
     */
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
    /** Done by Aseel
     */
    Label discountForStudents = new Label();
    Label entranceFees = new Label();
    /** End of aseel's work
     */
    WebView map = new WebView();
    WebEngine browser = map.getEngine();
    StackPane description;
    StackPane rating;
    StackPane events;
    Label eventLabel = new Label("Events");
    Label rates;
    Label eventDescription = new Label("some text");
    Label eventName = new Label("Karaoke");
    StackPane eventPane = new StackPane();
    GridPane eventDescriptionGrid = new GridPane();
    GridPane eventGrid = new GridPane();
    /* PUB SCENE */

    /* ADMIN SCENE */
    /** Done by Aseel
     */
    public int discount;
    /** End of aseel's work
     */

    /** Done by Shafiq & Antonino
     */
    public int fee = 2;
    /** End of Shafiq and Antonino's Work
     */
    /* ADMIN SCENE */
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PubFinder");
        primaryStage.setResizable(false);
        PubDataAccessor.PubDataAccessor();

        /*Welcome scene*/
        WelcomeScene.welcomeScene();
        welcomeScene = WelcomeScene.welcomeScene;
        /*Welcome scene*/
        /** Done by Shafiq and Anotnino
         */
        /* Admin add scene*/
        AdminAddScene.primaryStage = primaryStage;
        AdminAddScene.adminAddscene();
        adminAddScene = AdminAddScene.adminAddScene;
        /* Admin add scene*/

        /*Admin login scene*/
        AdminLoginScene.primaryStage = primaryStage;
        AdminLoginScene.adminloginscene();
        adminLoginScene = AdminLoginScene.adminLoginScene;
         /*Admin login scene*/

        /*Admin choice scene*/
        AdminChoiceScene.primaryStage = primaryStage;
        AdminChoiceScene.adminchoicescene();
        adminChoiceScene = AdminChoiceScene.adminChoiceScene;
        /*Admin choice scene*/

        /* Admin Edit Scene*/
        adminEditScene = AdminEditScene.editScene;
        /* Admin Edit Scene*/
        /** End of Shafiq and Anotonino's Work
         */

        /*Pub button scene*/
        pubLayout = new StackPane();
        /** Done by Ahmad
         */
        searchNameInput = new TextField();
        searchNameInput.setId("search-field");
        searchNameInput.setPromptText("NAME");
        /** End of Ahmad's Work
         */
        searchStreetInput = new TextField();
        searchStreetInput.setId("search-field");
        searchStreetInput.setPromptText("STREET");
        searchAgeInput = new TextField();
        searchAgeInput.setId("search-field");
        searchAgeInput.setPromptText("AGE");
        /** Done by aseel
         */
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
        /** End of aseel's Work
         */
        pubLayout.setId("pubs");
        search = new Button("SEARCH");
        search.setId("button-search");
        GridPane inputGrid = new GridPane();
        inputGrid.setMaxHeight(100);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setId("searchGrid");
        StackPane.setAlignment(inputGrid, Pos.TOP_LEFT);
        StackPane.setAlignment(search, Pos.TOP_RIGHT);

        search.setOnAction(e -> searchForPubs());
        /** Done by Ahmad
         */
        searchNameInput.setOnKeyReleased(event1 -> {
            if (event1.getCode() == KeyCode.ENTER) {
                searchForPubs();
            }
        });
        /** End of Ahmad's Work
         */

        /** Done by Marco
         */
        searchStreetInput.setOnKeyReleased(event2 -> {
            if (event2.getCode() == KeyCode.ENTER) {
                searchForPubs();
            }
        });
        /** End of Marco's Work
         */


        /** Done by Ahmad
         */
        searchAgeInput.setOnKeyReleased(event3 -> {
            if (event3.getCode() == KeyCode.ENTER) {
                searchForPubs();
            }
        });
        /** End of Ahmad's Work
         */

        /** Done by Shafiq & Anotnino
         */
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
                numberOfStars = 1;
            }
            else if (searchByRating.getSelectionModel().isSelected(1)){
                numberOfStars = 2;
            }
            else if (searchByRating.getSelectionModel().isSelected(2)) {
                numberOfStars = 3;
            }
            else if (searchByRating.getSelectionModel().isSelected(3)) {
                numberOfStars = 4;
            }
            else if (searchByRating.getSelectionModel().isSelected(4)){
                numberOfStars = 5;
            }
        });
        /** End of Shafiq and Anotino's Work
         */

        /** Done by Aseel and Antonino
         */
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
        /** End of Aeel and Antonino's Work
         */

        ScrollPane pubScroll = new ScrollPane();
        pubScroll.setId("scroll");
        pubScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pubScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        pubs = new GridPane();
        pubScroll.setContent(pubs);
        pubs.setId("pub-grid");
        pubs.setAlignment(Pos.CENTER);
        pubLayout.getChildren().add(pubScroll);
        /** Done by marco
         */
        inputGrid.add(searchNameInput, 1, 1);
        inputGrid.add(searchStreetInput, 2, 1);
        inputGrid.add(searchAgeInput, 3, 1);
        /** End of Marco's work
         */

        /** Done by Aseel
         */
        inputGrid.add(searchByRating, 4, 1);
        inputGrid.add(searchByArea, 5, 1);
        inputGrid.add(searchStudentDiscount, 6, 1);
        inputGrid.add(searchBySpecialEvents, 7, 1);
        inputGrid.add(searchWithoutFees, 8, 1);
        /** End of aseel's work
         */

        /** Done by marco
         */
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
        /** End of marco's work
         */


        /** Done by Shafiq & Antonino
         */
        star.setOnAction(event -> {
            int rate = PubDataAccessor.checkRate(this.id);
            int rateUpdate = rate+1;
            /** End of Shafiq & Antonino's Work
             */

            /** Done by marco
             */
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
        Main.primaryStage = primaryStage;
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
        address = new Label(Pub.getAddress(Pub.getIndexById(this.id)) + " \uF124");
        address.setId("infoLabel");
        type = new Label(Pub.getType(Pub.getIndexById(this.id)) + " \uF005");
        type.setId("infoLabel");
        /** End of marco's work
         */

        /** Done by aseel
         */
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
        /** End of aseel's work
         */

        /** Done by marco
         */
        map.setMinWidth(1000);
        map.setMaxHeight(250);
        browser.load("http://locateme.marcokoivisto.me/?lat=" + Pub.getLat(Pub.getIndexById(this.id)) + "&lon=" + Pub.getLon(Pub.getIndexById(this.id)));

        int nrStars = Pub.getNrStars(Pub.getIndexById(this.id));
        String stars = "";

        for (int i=0; i < nrStars; i++){
            stars += "\uF005 ";
        }
        /** End of marco's work
         */
        rates = new Label(stars);
        /** Done by aseel
         */
        rates.setId("ratingOfStars");

        rating.getChildren().add(rates);
        rating.setAlignment(Pos.CENTER);
        xPane.add(pubName, 1, 1);
        descriptionGrid.add(discountForStudents, 1, 1);
        descriptionGrid.add(entranceFees, 2, 1);
        descriptionGrid.add(age, 3, 1);
        descriptionGrid.add(open, 4, 1);
        descriptionGrid.add(type, 5, 1);
        descriptionGrid.add(address, 6, 1);
        /** End of aseel's work
         */

        /** Done by marco
         */
        descriptionGrid.setAlignment(Pos.CENTER);
        description.getChildren().addAll(descriptionGrid);
        eventDescriptionGrid.add(eventLabel, 1, 1);
        eventDescriptionGrid.add(eventPane, 1, 2);
        GridPane.setHalignment(eventLabel, HPos.CENTER);
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
        GridPane.setHalignment(pubName, HPos.CENTER);
        GridPane.setValignment(pubName, VPos.TOP);
        GridPane.setValignment(back, VPos.TOP);
        GridPane.setHalignment(star, HPos.RIGHT);
        GridPane.setValignment(star, VPos.TOP);

        xPane.add(star, 1, 1);
        header.setFitWidth(1000);
        header.setPreserveRatio(true);
        /** End of marco's work
         */

        /** Done by Shafiq& Antonino
         */
        int rate = PubDataAccessor.checkRate(this.id);
        star.setText(rate + " \uF08A");
        /** End of shafiq & Antonino's WORK
         *
         */
    }
    public void searchForPubs() {
        pubLayout.getChildren().remove(noPub);
        int y = 1;
        int x = 1;
        searchName = searchNameInput.getText();
        searchStreet = searchStreetInput.getText();
        /** Done by Ahmad
         */
        if (!searchAgeInput.getText().equals("")) {
            searchAge = Integer.valueOf(searchAgeInput.getText());
        } else searchAge = 100;

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

        GridPane.setRowIndex(randomPub, 1);
        GridPane.setColumnIndex(randomPub, 0);
        /** End of Ahmad's Work
         */

        /** Done by Shafiq & Antonino & Marco
         */
        for (Pub pub : PubDataAccessor.pubs) {
            if (searchEvent && pub.eventName.isEmpty()) {
                continue;
            }

            if (area_checker != 2 && pub.location_id != area) {
                continue;
            }

            if (pub.name != null && (pub.name.toLowerCase().contains(searchName.toLowerCase()))
                    && pub.street != null && (pub.street.toLowerCase().contains(searchStreet.toLowerCase()))
                    && pub.age <= searchAge && pub.nrStars >= numberOfStars && pub.hasStudentDiscount >= discount
                    && pub.hasFee <= fee) {

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

                GridPane.setRowIndex(pubButton, y);
                GridPane.setColumnIndex(pubButton, x);

                x++;
            }
        }

        if (pubs.getChildren().size() == 0) {
            pubLayout.getChildren().add(noPub);
            noPub.setId("nopubs_message");
        }
        /* new elements */

        pubs.setHgap(30);

    }
    /** End of Shafiq & Antonini & Marco's Work
     */
}
