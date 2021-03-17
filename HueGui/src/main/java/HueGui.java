import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.concurrent.ExecutionException;
import java.util.prefs.BackingStoreException;

public class HueGui extends BorderPane {
    public HueGui() throws ExecutionException, InterruptedException, BackingStoreException {
        HueViewLogic viewLogic = new HueViewLogic();
        HueControlLogic controlLogic = new HueControlLogic();
        RadioButtonHBox rad =  new RadioButtonHBox();
        HueGuiBtnHBoxTop hbTop = new HueGuiBtnHBoxTop(viewLogic, controlLogic, rad);
        controlLogic.initHueConnection();
        setPadding(new Insets(30));
        setTop(hbTop);
        setCenter(new CenterHBox(viewLogic, controlLogic, rad, hbTop));
        setBackground(new Background(new BackgroundFill(Color.web("#EEEEEE"), null, null)));

    }

}
