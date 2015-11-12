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
import javafx.stage.Stage;
import java.util.Objects;
import javafx.scene.input.KeyCode;


public class Main extends Application implements EventHandler<javafx.event.ActionEvent> {

    Button btnEnter, btnAdmin;
    Label welcome, warning, noPub;
    Scene pubScene, pubPage, adminLoginScene, adminChoiceScene, adminAddScene;

    int pubId;
    public int id;
    public Button pubButton;
    String searchStreet = "";
    StackPane pubLayout;
    GridPane pubs;
    Button search;
    TextField searchInput;
    Stage primaryStage;

    /* PUB SCENE */
    ImageView header = new ImageView("http://www.thaizeit.de/uploads/tx_thaizeit2/Club_808_02.jpg");
    GridPane xPane = new GridPane();
    Button back = new Button("Back");
    javafx.scene.shape.Rectangle overlay = new javafx.scene.shape.Rectangle();
    Label pubName = new Label();
    Label open = new Label();
    Label age = new Label();
    Label adress = new Label();
    Label type = new Label();
    /* PUB SCENE */

    /* ADMIN SCENE */
    StackPane adminLoginLayout;
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
        btnAdmin = new Button("Settings");
        btnAdmin.setId("adminButton");
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
        adminLoginLayout.setId("pubs");
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
        StackPane adminLayout = new StackPane();
        adminLayout.setId("welcome");
        Button add = new Button("Add pub");
        Label choiceLabel = new Label("Options");

        add.setOnAction(e -> {
            primaryStage.setScene(adminAddScene);
        });

        add.setId("admin_button");
        Button delete = new Button("Delete pub");
        delete.setId("admin_button");
        Button logOut = new Button("LOG OUT");
        logOut.setOnAction(e -> {
            primaryStage.setScene(welcomeScene);
        });
        logOut.setId("button-logout");
        choiceLabel.setId("login_message");


        adminLayout.setAlignment(Pos.CENTER);
        adminLayout.setAlignment(choiceLabel,Pos.TOP_CENTER);
        adminLayout.getChildren().addAll(logOut,add, choiceLabel);

        adminLayout.setAlignment(logOut, Pos.TOP_LEFT);
        adminLayout.setAlignment(add,Pos.CENTER);

        adminChoiceScene = new Scene(adminLayout, 1000, 600);
        adminChoiceScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        /*Admin choice scene*/

        /*Admin add scene*/
        StackPane addLayout = new StackPane();
        GridPane fields = new GridPane();
        Button backToAdmin = new Button("BACK");
        Label addLabel = new Label("Add a new pub");
        addLabel.setId("login_message");

        /*INPUT FIELDS*/
        TextField nameOfPub = new TextField();
        TextField ageOfPub = new TextField();
        TextField openTime = new TextField();
        TextField closeTime = new TextField();
        TextField streetOfPub = new TextField();
        ComboBox city = new ComboBox(FXCollections.observableArrayList(
                "Gothenburg"));
        ComboBox typeOfPub = new ComboBox(FXCollections.observableArrayList(
                "Sport", "Karaoke", "Club"));
        Button addBtn = new Button("ADD PUB");

        addBtn.setOnAction(e -> {
            Pub pub = new Pub(20, Integer.valueOf(ageOfPub.getText()),
                    Integer.valueOf(openTime.getText()),
                    Integer.valueOf(closeTime.getText()),
                    nameOfPub.getText(),
                    "",
                    typeOfPub.getValue().toString(),
                    streetOfPub.getText(),
                    "",
                    41451);
            PubDataAccessor.addPub(pub);

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
        fields.add(streetOfPub, 2, 1);
        fields.add(city, 2, 2);
        fields.add(typeOfPub, 2, 3);
        fields.add(addBtn, 2, 4);
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
        searchInput = new TextField();
        searchInput.setId("input-field");
        searchInput.setPromptText("STREET");
        pubLayout.setId("pubs");
        search = new Button("SEARCH");
        search.setId("button-search");
        pubLayout.setAlignment(search, Pos.TOP_RIGHT);
        pubLayout.setAlignment(searchInput, Pos.TOP_LEFT);
        search.setOnAction(e -> searchForPubs());
        searchInput.setOnKeyReleased(event1 -> {
            if (event1.getCode() == KeyCode.ENTER) {
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
        pubLayout.getChildren().add(search);
        pubLayout.getChildren().add(searchInput);
        noPub = new Label("No pubs found");
        searchForPubs();

        pubScene = new Scene(pubLayout ,1000, 600);
        pubScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        /*Pub button scene*/

        /*Pub scene*/

        ScrollPane pubPageLayout = new ScrollPane();
        pubPageLayout.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pubPageLayout.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pubPageLayout.setFitToWidth(true);
        pubPageLayout.setContent(xPane);
        pubPageLayout.setId("gej");

        back.setOnAction((event) ->{
            primaryStage.setScene(pubScene);
            xPane.getChildren().removeAll(header, back, overlay, pubName, age, open, adress, type);
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
        String upperCaseName = Pub.getName(Pub.getIndexById(this.id));
        pubName = new Label(upperCaseName.toUpperCase());
        header = new ImageView(Pub.getImage(Pub.getIndexById(this.id)));
        age = new Label("" + Pub.getAge(Pub.getIndexById(this.id)));
        open = new Label(Pub.getOpening(Pub.getIndexById(this.id)));
        adress = new Label(Pub.getAdress(Pub.getIndexById(this.id)));
        type = new Label(Pub.getType(Pub.getIndexById(this.id)));


        //xPane.getChildren().addAll(header, pubInfo);
        xPane.add(header, 1, 1);
        xPane.add(overlay, 1, 1);
        xPane.add(pubName, 1, 2);
        xPane.add(age, 1, 3);
        xPane.add(open, 1, 4);
        xPane.add(adress, 1, 5);
        xPane.add(type, 1, 6);
        pubName.setId("pub_name");
        xPane.setHalignment(pubName, HPos.CENTER);
        xPane.setValignment(pubName, VPos.TOP);
        xPane.setValignment(back, VPos.TOP);

        xPane.add(back, 1, 1);
        back.setId("button");
        header.setFitWidth(1000);
        header.setPreserveRatio(true);
    }
    public void searchForPubs(){
        pubLayout.getChildren().remove(noPub);
        System.out.println("Search was pressed");
        int y = 1;
        int x = 1;
        searchStreet = searchInput.getText();
        pubs.getChildren().clear();
        for (Pub pub: PubDataAccessor.pubs){
            if (pub.street != null && (pub.street.toLowerCase().contains(searchStreet.toLowerCase()))) {
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
