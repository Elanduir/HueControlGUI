import io.github.zeroone3010.yahueapi.Hue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.paint.Color;

public class HueGui extends BorderPane {
    public HueGui(){
        HueViewLogic viewLogic = new HueViewLogic();
        HueControlLogic controlLogic = new HueControlLogic();
        RadioButtonHBox rad =  new RadioButtonHBox();
        setPadding(new Insets(30));
        setTop(new HueGuiBtnVBoxTop(viewLogic, controlLogic, rad));
        setCenter(new CenterHBox(viewLogic, controlLogic, rad));
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

    }

}
