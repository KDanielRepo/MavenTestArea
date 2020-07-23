import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Compass compass = new Compass();
        compass.setX(200.0);
        compass.setY(200.0);
        compass.setNorthPositionX(0.0);
        compass.setNorthPositionY(0.0);
        compass.rotateNeedle();
        borderPane.setLeft(compass);
        /*SlidersTest slidersTest = new SlidersTest(1);*/
        /*SlidersTest slidersTest = new SlidersTest();
        //slidersTest.create(8);

        borderPane.setCenter(slidersTest);
        TextField tx = new TextField();
        borderPane.setRight(tx);
        Button eee = new Button("eee");
        borderPane.setBottom(eee);
        eee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                slidersTest.clear();
                slidersTest.create(Integer.parseInt(tx.getText()));
            }
        });*/
        /*VBox a = new VBox();
        Button aa = new Button("test");
        VBox b = new VBox();
        Button bb = new Button("test2");
        a.getChildren().addAll(aa);
        b.getChildren().addAll(bb);
        BorderPane c = new BorderPane();
        c.setLeft(a);*/
        //c.setPadding(new Insets(0,100,0,0));
        /*ComboBox tt = new ComboBox();
        tt.getSelectionModel().getSelectedItem();
        c.setRight(b);
        a.setAlignment(Pos.BOTTOM_CENTER);
        b.setAlignment(Pos.BOTTOM_RIGHT);
        borderPane.setBottom(c);*/
        //borderPane.setLeft(slidersTest);
       /* TextField textField = new TextField();
        Captcha captcha = new Captcha();
        Button button = new Button("check");
        VBox vBox = new VBox();
        VBox vBox1 = new VBox();
        Button button1 = new Button("generate");
        TextField textField1 = new TextField();
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                captcha.setB(captcha.generate());
                textField1.setText(captcha.getB());
            }
        });
        vBox.getChildren().addAll(textField1,button1);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(textField.getText()!=null){
                    captcha.setA(textField.getText());
                    if(captcha.compare(captcha.getA(),captcha.getB())){
                        textField1.setText("hura");
                    }else{
                        textField1.setText("chooj");
                    }
                }
            }
        });
        //borderPane.setRight(vBox);
        vBox1.getChildren().addAll(textField,button);*/
        //borderPane.setLeft(vBox1);
        Scene scene = new Scene(borderPane);
        //borderPane.setStyle("-fx-background-color: #8472FF;");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UI.class,args);
    }
}
