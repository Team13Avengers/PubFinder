package sample;

import com.sun.javafx.geom.*;
import com.sun.javafx.geom.Rectangle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.Objects;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Main extends Application implements EventHandler<javafx.event.ActionEvent> {

    Button btnEnter, btnAdmin;
    Label welcome, warning, noPub;
    Scene pubScene, pubPage, adminLoginScene, adminScene;

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
        Label loginLabel = new Label("Login to gain admin access");
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
        backBtn.setId("button");
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
                primaryStage.setScene(adminScene);
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

        /*Admin scene*/
        GridPane adminLayout = new GridPane();
        adminLayout.setId("welcome");
        adminScene = new Scene(adminLayout, 1000, 600);
        adminScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        /*Admin scene*/

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

        pubs = new GridPane();
        pubs.setId("pub-grid");
        pubs.setAlignment(Pos.CENTER);
        pubs.setHgap(10);
        pubs.setVgap(10);

        pubLayout.getChildren().add(pubs);
        pubLayout.getChildren().add(search);
        pubLayout.getChildren().add(searchInput);
        noPub = new Label("No pubs found");
        searchForPubs();

        pubScene = new Scene(pubLayout ,1000, 600);
        pubScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        /*Pub button scene*/

        /*Pub scene*/

        javafx.scene.control.ScrollPane pubPageLayout = new ScrollPane();
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
        int y = 0;
        int x = 0;
        searchStreet = searchInput.getText();
        pubs.getChildren().clear();
        for (Pub pub: PubDataAccessor.pubs){
            if (pub.street != null && (pub.street.toLowerCase().contains(searchStreet.toLowerCase()))) {
                pubButton = new Button(pub.name);
                pubButton.setId("pub-button");
                pubButton.setMinWidth(230);
                pubButton.setMinHeight(100);
                pubButton.setOnAction((event) -> {
                    idOfButton(pub.id);
                    primaryStage.setScene(pubPage);
                    setPubScene();
                });
            /*pubButton.setStyle("-fx-background-image: url(" + "\"" + pub.picture + "\"" + "); ");*/

                pubs.getChildren().add(pubButton);
                pubs.setRowIndex(pubButton, y);
                pubs.setColumnIndex(pubButton, x);
                x++;
                if (x == 4) {
                    y++;
                    x = 0;
                }
            }
        }
        if (pubs.getChildren().size() == 0){
            pubLayout.getChildren().add(noPub);
            noPub.setId("nopubs_message");
        }

        /* new elements */

        pubs.setHgap(10);
        pubs.setVgap(10);
    }
}
