package vinnsla;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.scene.media.Media;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventModel {
    private final SimpleStringProperty heiti = new SimpleStringProperty();
    private final SimpleObjectProperty<Flokkur> flokkur = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<LocalDate> dags = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<LocalTime> timi = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Media> myndband = new SimpleObjectProperty<>();

    public SimpleStringProperty heitiProperty() {
        return this.heiti;
    }

    public SimpleObjectProperty<Flokkur> flokkurProperty() {
        return this.flokkur;
    }

    public SimpleObjectProperty<LocalDate> dagsProperty() {
        return this.dags;
    }

    public SimpleObjectProperty<LocalTime> timiProperty() {
        return this.timi;
    }

    public SimpleObjectProperty<Media> myndbandProperty() {
        return this.myndband;
    }

    public EventModel(String heiti, Flokkur flokkur, LocalDate dags, LocalTime timi, Media myndband) {
        this.heitiProperty().set(heiti);
        this.flokkurProperty().set(flokkur);
        this.dagsProperty().set(dags);
        this.timiProperty().set(timi);
        this.myndbandProperty().set(myndband);
    }


}
