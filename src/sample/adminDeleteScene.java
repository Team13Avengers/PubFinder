package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.beans.binding.*;
import sample.*;

import javax.script.Bindings;

/**
 * Created by Marco on 2015-11-15.
 */
public class adminDeleteScene {
    public static Scene deleteScene;
    static StackPane addLayout = new StackPane();
    static Button pubButton;
    static GridPane pubs;
    static int x = 1, y = 1;
    static int deleteId;

    public static void adminDeleteScene(){
        pubs = new GridPane();
        Label deleteLabel = new Label("Click a pub to delete");
        deleteLabel.setId("login_message");
        Button backBtn = new Button("BACK");
        backBtn.setId("button");
        backBtn.setOnAction(event -> {
            Main.primaryStage.setScene(Main.adminChoiceScene);
        });

        showPubsToDelete();

        pubs.setHgap(10);
        pubs.setVgap(10);
        pubs.setAlignment(Pos.CENTER);
        addLayout.setId("pubs");
        addLayout.setAlignment(backBtn, Pos.TOP_LEFT);
        addLayout.setAlignment(deleteLabel, Pos.TOP_CENTER);
        addLayout.getChildren().addAll(pubs, backBtn, deleteLabel);
        deleteScene = new Scene(addLayout, 1000, 600);
        deleteScene.getStylesheets().addAll(Main.class.getResource("style.css").toExternalForm());
    }
    public static void showPubsToDelete(){
        pubs.getChildren().clear();
        x = 1;
        y = 1;
        for (Pub pub: PubDataAccessor.pubs){
            pubButton = new Button(pub.name);
            pubButton.setId("deletePubButton");
            pubButton.setMinWidth(200);
            pubButton.setMaxWidth(200);
            pubButton.setMinHeight(70);
            pubButton.setAlignment(Pos.CENTER);

            pubButton.textProperty().bind(
                    javafx.beans.binding.Bindings.when(pubButton.hoverProperty())
                            .then("Delete \uF057")
                            .otherwise(pub.name));

            pubs.getChildren().add(pubButton);

            pubButton.setOnAction(event -> {
                /* deleteId is the database id that should be deleted --> */
                deleteId = pub.id;
                /* DELETE METHOD GOES HERE */
                PubDataAccessor.deletePub(deleteId);
                /*To show the pubs that are left...*/
//                showPubsToDelete();
            });

            pubs.setRowIndex(pubButton, y);
            pubs.setColumnIndex(pubButton, x);
            x++;
            if(x > 4){
                y++;
                x = 1;
            }
        }
    }
}
