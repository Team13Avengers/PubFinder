package Pubfinder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminChoiceScene {
    /** Done by Marco
     */
    static Scene adminDeleteScene, adminEditScene, editPubScene , adminChoiceScene, adminAddScene, welcomeScene;
    static Stage primaryStage;

    public static void adminchoicescene(){
        /**  Done by Shafiq & Antonino
         */
        AdminDeleteScene.adminDeleteScene();
        AdminEditScene.adminEditScene();
        EditPubScene.editPubScene();
        adminDeleteScene = AdminDeleteScene.DeleteScene;
        adminEditScene = AdminEditScene.editScene;
        editPubScene = EditPubScene.editPubScene;
        adminAddScene = AdminAddScene.adminAddScene;
        /** End of Shafiq and Antonino's Work
         */
        StackPane adminLayout = new StackPane();

        adminLayout.setId("welcome");
        GridPane adminBtnGrid = new GridPane();
        Button add = new Button("Add pub");
        Button delete = new Button("Delete pub");
        Button edit = new Button("Edit pub");
        Label choiceLabel = new Label("Options");

        add.setOnAction(e -> {
            AdminAddScene.clear();
            primaryStage.setScene(adminAddScene);
        });
        delete.setOnAction(e -> {
            PubDataAccessor.clearCache();
            AdminDeleteScene.showPubsToDelete();
            primaryStage.setScene(adminDeleteScene);
        });
        edit.setOnAction(e -> {
            PubDataAccessor.clearCache();
            AdminEditScene.showPubsToEdit();
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
        StackPane.setAlignment(choiceLabel, Pos.TOP_CENTER);
        adminLayout.getChildren().addAll(adminBtnGrid,choiceLabel,logOut);

        StackPane.setAlignment(logOut, Pos.TOP_LEFT);

        adminChoiceScene = new Scene(adminLayout, 1000, 600);
        /** Done by Shafiq & Antonino
         */
        AdminAddScene.adminChoiceScene = adminChoiceScene;
        AdminLoginScene.adminChoiceScene=adminChoiceScene;
        /** End of Shafiq & Antonino's Work
         */

        adminChoiceScene.getStylesheets().addAll(AdminChoiceScene.class.getResource("style.css").toExternalForm());
    }
    /** End of Marco's Work
     */
}
