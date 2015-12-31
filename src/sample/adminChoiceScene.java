package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class adminChoiceScene {
    static Scene adminDeleteScene,adminEditScene,editPubScene , adminChoiceScene, adminAddScene, welcomeScene;
    static Stage primaryStage;

    public static void adminchoicescene(){
        sample.adminDeleteScene.adminDeleteScene();
        sample.adminEditScene.adminEditScene();
        sample.editPubScene.editPubScene();
        adminDeleteScene = sample.adminDeleteScene.DeleteScene;
        adminEditScene = sample.adminEditScene.editScene;
        editPubScene = sample.editPubScene.editPubScene;
        adminAddScene = sample.adminAddScene.adminAddScene;

        StackPane adminLayout = new StackPane();

        adminLayout.setId("welcome");
        GridPane adminBtnGrid = new GridPane();
        Button add = new Button("Add pub");
        Button delete = new Button("Delete pub");
        Button edit = new Button("Edit pub");
        Label choiceLabel = new Label("Options");

        add.setOnAction(e -> {
            sample.adminAddScene.clear();
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
        sample.adminAddScene.adminChoiceScene = adminChoiceScene;
        sample.adminLoginScene.adminChoiceScene=adminChoiceScene;

        adminChoiceScene.getStylesheets().addAll(adminChoiceScene.class.getResource("style.css").toExternalForm());
        /*Admin choice scene*/
    }
}
