import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.util.Collection;
import java.util.HashMap;

public class SlidersTest extends Pane {
    Slider[] a = new Slider[12];
    GridPane gpp = new GridPane();
    public SlidersTest(){}
    public SlidersTest(int many) {
        GridPane gp = new GridPane();
        gp.setVgap(10);
        gp.setHgap(20);
        for (int i = 0; i < many; i++) {
            Slider s = new Slider();
            s.setMin(-15);
            s.setMax(15);
            //s.setValue(1);
            s.setMinorTickCount(0);
            s.setOrientation(Orientation.VERTICAL);
            double a = Math.pow(2, 1);
            Double k = 2 * a;
            Integer aa = k.intValue();
            ComboBox t = new ComboBox();
            HashMap<String, String> c = new HashMap<>();
            c.put("kek", "nie");
            Collection cc = c.keySet();

            ObservableList tt = FXCollections.observableArrayList(cc);
            t.setItems(tt);
            for (int j = 0; j < t.getItems().size(); j++) {
                System.out.println(t.getItems().get(i));
                System.out.println(c.get(t.getItems().get(i)));
            }
            s.setMajorTickUnit(15);
            s.setLabelFormatter(new StringConverter<Double>() {
                @Override
                public String toString(Double n) {
                    if (n < -14) return "Novice";
                    if (n < 14) return "Intermediate";
                    if (n > 14) return "Advanced";

                    return "Expert";
                }

                @Override
                public Double fromString(String s) {
                    switch (s) {
                        case "Novice":
                            return 0d;
                        case "Intermediate":
                            return 1d;
                        case "Advanced":
                            return 2d;
                        case "Expert":
                            return 3d;

                        default:
                            return 3d;
                    }
                }
            });
            s.setSnapToTicks(true);
            s.setShowTickMarks(true);
            s.setShowTickLabels(true);

            GridPane.setConstraints(s, 0, i);
            gp.getChildren().add(s);
        }
        this.getChildren().add(gp);
        Spinner s = new Spinner();
    }

    public void create(int many) {
        for (int i = 0; i < many; i++) {
            Slider kek = new Slider();
                a[i+2]=kek;
                gpp.getChildren().add(a[i+2]);

        }
        if(!this.getChildren().contains(gpp)){
            this.getChildren().add(gpp);
        }
    }
    public void clear(){
        for (int i = 0; i < 10; i++) {
            if(gpp.getChildren().contains(a[i+2])){
                gpp.getChildren().remove(a[i+2]);
            }
            a[i+2] = null;

        }
    }

}
