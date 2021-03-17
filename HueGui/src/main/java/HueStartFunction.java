import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;
import java.util.prefs.BackingStoreException;

public class HueStartFunction extends Application{
    public void start(Stage stage) throws ExecutionException, InterruptedException, BackingStoreException {
        var ui = new HueGui();
        stage.setScene(new Scene(ui));
        stage.setTitle("Hue Control");
        stage.show();
    }


}
