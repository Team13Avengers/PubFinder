package Pubfinder;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.util.Objects;

/** Done by Marco
 */
public class AdminLoginScene {
    static StackPane adminLoginLayout;
    static Scene adminChoiceScene, adminLoginScene;

    public static void adminloginscene(){
        adminLoginLayout = new StackPane();
        adminLoginLayout.setId("welcome");
        GridPane login = new GridPane();
        login.setAlignment(Pos.TOP_CENTER);
        Label loginLabel = new Label("Login to admin panel");
        Label error = new Label("Your credentials are invalid. Please try again!");

        loginLabel.setId("login_message");
        GridPane.setHalignment(error, HPos.CENTER);
        error.setId("error");

        TextField username = new TextField();
        username.setId("login_fields");
        GridPane.setHalignment(username, HPos.CENTER);
        username.setPromptText("USERNAME");
        PasswordField password = new PasswordField();
        password.setId("login_fields");
        GridPane.setHalignment(password, HPos.CENTER);
        password.setPromptText("PASSWORD");

        Button loginButton = new Button("LOGIN");
        loginButton.setId("login_button");
        GridPane.setHalignment(loginButton, HPos.CENTER);

        Button backBtn = new Button("BACK");
        backBtn.setId("adminButton");
        GridPane.setHalignment(backBtn, HPos.CENTER);

        backBtn.setOnAction(e -> {
            Main.primaryStage.setScene(Main.welcomeScene);
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
                Main.primaryStage.setScene(adminChoiceScene);
            }
            else
            if (!login.getChildren().contains(error)) {
                login.add(error, 1, 6);
            }
        });

        adminLoginLayout.getChildren().addAll(login);
        adminLoginScene = new Scene(adminLoginLayout ,1000, 600);
        adminLoginScene.getStylesheets().addAll(AdminAddScene.class.getResource("style.css").toExternalForm());
    }
}
/** End of Marco's Work
 */


