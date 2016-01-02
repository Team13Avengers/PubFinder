package Pubfinder;
/** done by marco
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class AdminEditScene {
    public static Scene editScene;
    static StackPane addLayout = new StackPane();
    static Button pubButton;
    static GridPane pubs;
    static int x = 1, y = 1;
    public static int editId;
    /** done  by shafiq & antonino
     */
    public static int editEventId;
    /** end of shafiq & antonino's work
     */
    public static void adminEditScene(){
        pubs = new GridPane();
        Label deleteLabel = new Label("Click a pub to edit");
        deleteLabel.setId("login_message");
        Button backBtn = new Button("BACK");
        backBtn.setId("button");
        backBtn.setOnAction(event -> {
            Main.primaryStage.setScene(Main.adminChoiceScene);
        });

        showPubsToEdit();

        pubs.setHgap(10);
        pubs.setVgap(10);
        pubs.setAlignment(Pos.CENTER);
        addLayout.setId("welcome");
        StackPane.setAlignment(backBtn, Pos.TOP_LEFT);
        StackPane.setAlignment(deleteLabel, Pos.TOP_CENTER);
        addLayout.getChildren().addAll(pubs, backBtn, deleteLabel);
        editScene = new Scene(addLayout, 1000, 600);
        EditPubScene.editPubScene = editScene;
        editScene.getStylesheets().addAll(Main.class.getResource("style.css").toExternalForm());
    }
    public static void showPubsToEdit(){
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
                            .then("Edit \uF040")
                            .otherwise(pub.name));

            pubs.getChildren().add(pubButton);

            pubButton.setOnAction(event -> {
                editId = pub.id;
                /** done by shafiq & antonino
                 */
                editEventId = pub.event_id;
                /** end of shafiq & antonino's work
                 */
                EditPubScene.deleteComponents();
                EditPubScene.updateEditScene();
                Main.primaryStage.setScene(EditPubScene.editPubScene);
            });

            GridPane.setRowIndex(pubButton, y);
            GridPane.setColumnIndex(pubButton, x);
            x++;
            if(x > 4){
                y++;
                x = 1;
            }
        }
    }
}
/** end of marco's work
 */
