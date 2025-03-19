package vidmot;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
import vinnsla.EventModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import vinnsla.Flokkur;

import java.io.File;


public class EventView extends VBox {

    private EventModel eventModel;

    @FXML private TextField heiti;
    @FXML private ComboBox<Flokkur> flokkur;
    @FXML private DatePicker dagur;
    @FXML private TextField timi;
    @FXML private MediaView mediaView;
    private MediaPlayer mediaPlayer;

    public EventView() {
        this.eventModel = new EventModel("", null, LocalDate.now(), LocalTime.now(), null);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("event-view.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initialize();
    }

    @FXML
    private void initialize() {
        flokkur.getItems().setAll(Flokkur.values());

        heiti.textProperty().unbind();
        flokkur.itemsProperty().unbind();
        dagur.valueProperty().unbind();
        timi.textProperty().unbind();

        heiti.textProperty().bind(eventModel.heitiProperty());
        flokkur.valueProperty().bindBidirectional(eventModel.flokkurProperty());
        dagur.valueProperty().bindBidirectional(eventModel.dagsProperty());
        timi.textProperty().bind(eventModel.timiProperty().asString());

        eventModel.myndbandProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (mediaPlayer != null) {
                    mediaPlayer.dispose();
                }
                Media media = new Media(newValue.toString());
                media.setOnError(() -> System.out.println("Media Error: " + media.getError().getMessage()));
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setOnError(() ->
                        System.out.println("Player Error: " + mediaPlayer.getError().getMessage())
                );
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
            }
        });
    }

    public void playPauseAction(ActionEvent actionEvent) {
        Button playButton = (Button) actionEvent.getSource();
        System.out.println(playButton.getText());
        if (playButton.getText().equals(">")) {
            mediaPlayer.play();
            System.out.println("play " + mediaPlayer.getStatus());
            playButton.setText("||");
        } else {
            mediaPlayer.pause();
            System.out.println("pause " + mediaPlayer.getStatus());
            playButton.setText(">");
        }
    }

    public void rewindAction(ActionEvent actionEvent) {
        mediaPlayer.seek(Duration.ZERO);
    }



    public void setEventModel(EventModel model) {
        this.eventModel = model;
        initialize();
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void OpnaSkra() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Veldu Myndband");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Myndbönd", "*.mp4", "*.avi", "*.mov", "*.mkv", "*.wmv"));


        Window ownerWindow = this.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(ownerWindow);

        if (selectedFile != null) {
            try {
                // Breyti í streng og geri nýtt "Media"
                String mediaUrl = selectedFile.toURI().toString();
                Media newMedia = new Media(mediaUrl);

                // Uppfæri myndbandið, bind sér um rest
                eventModel.myndbandProperty().set(newMedia);
            } catch (Exception e) {
                System.out.println("Villa að ná í myndband: " + e.getMessage());
            }
        }
    }
}
