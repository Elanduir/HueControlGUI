import io.github.zeroone3010.yahueapi.Room;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class CenterHBox extends HBox {

    ColorPicker colPick;
    Slider sldBrightness;
    public CenterHBox(HueViewLogic viewLogic, HueControlLogic controlLogic, RadioButtonHBox rad){
        setPadding(new Insets(15,0,0, 0));
        setSpacing(15);
        colPick = viewLogic.createColPick(controlLogic, rad, viewLogic);
        sldBrightness = viewLogic.createBrightnessSlider(0, 100, 100, controlLogic, rad, viewLogic);
        rad.initRadioButtons(viewLogic, controlLogic);
        getChildren().addAll(colPick, sldBrightness, rad);
    }

}
