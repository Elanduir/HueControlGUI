import io.github.zeroone3010.yahueapi.Room;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class CenterHBox extends HBox {

    ColorPicker colPick;

    public CenterHBox(HueViewLogic viewLogic, HueControlLogic controlLogic, RadioButtonHBox rad, HueGuiBtnHBoxTop hbTop){
        setPadding(new Insets(15,15,15, 15));
        setSpacing(15);
        colPick = viewLogic.createColPick(controlLogic, rad, viewLogic, hbTop);
        BrightnessSliderVbox brightnessSliderVbox = new BrightnessSliderVbox(viewLogic, controlLogic, rad);
        rad.initRadioButtons(viewLogic, controlLogic);
        getChildren().addAll(colPick, brightnessSliderVbox, rad);
    }

}
