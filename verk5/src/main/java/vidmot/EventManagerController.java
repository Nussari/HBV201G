package vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import vinnsla.EventModel;
import vinnsla.Flokkur;
import javafx.application.Platform;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventManagerController {
    @FXML
    private EventView eventView;
    @FXML
    private StackPane fxEventViews;

    private ObservableList<EventModel> list = FXCollections.observableArrayList();

    public void initialize() {
        EventModel model = new EventModel(
                "Test",
                Flokkur.Bilasyning,
                LocalDate.now(),
                LocalTime.now(),
                new Media("https://liveexample.pearsoncmg.com/common/sample.mp4")
        );
        eventView.setEventModel(model);
    }
    public static void haetta(){
        Platform.exit();
    }
}