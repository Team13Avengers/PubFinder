package Pubfinder;
/** Done by Marco
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class WelcomeScene {
    public static StackPane layout = new StackPane();
    public static Scene welcomeScene;
    public static Button btnEnter, btnAdmin;
    public static void welcomeScene(){
        Label welcome, warning;
        layout.setId("welcome");

        btnEnter = new Button("ENTER");
        btnEnter.setId("button");
        btnEnter.setOnAction(e -> Main.primaryStage.setScene(Main.pubScene));
        btnEnter.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                Main.primaryStage.setScene(Main.pubScene);
            }
        });
        btnAdmin = new Button("\uF013");
        btnAdmin.setId("faIcon");
        btnAdmin.setOnAction(e -> Main.primaryStage.setScene(Main.adminLoginScene));

        welcome = new Label("Welcome to PubFinder");
        warning = new Label("Under 18? Don't enter!");
        welcome.setId("welcome_message");
        warning.setId("warning_message");
        layout.getChildren().addAll(btnEnter,welcome,warning, btnAdmin);
        StackPane.setAlignment(welcome, Pos.TOP_CENTER);
        StackPane.setAlignment(btnEnter, Pos.CENTER);
        StackPane.setAlignment(warning, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(btnAdmin, Pos.TOP_RIGHT);
        welcomeScene = new Scene(layout ,1000, 600);
        AdminChoiceScene.welcomeScene = welcomeScene;
        welcomeScene.getStylesheets().addAll(Main.class.getResource("style.css").toExternalForm());
    }
}
/** End of Marco's Work
 */
