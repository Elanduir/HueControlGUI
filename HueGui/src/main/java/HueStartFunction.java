import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HueStartFunction extends Application{
    public void start(Stage stage) {
        var ui = new HueGui();
        stage.setScene(new Scene(ui));
        stage.setTitle("Hue Control");
        stage.show();
    }


}
