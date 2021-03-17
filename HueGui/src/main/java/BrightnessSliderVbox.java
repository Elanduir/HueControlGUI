import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class BrightnessSliderVbox extends VBox {
    Slider sldBrightness;
    Label lbBrightness;
    public BrightnessSliderVbox(HueViewLogic viewLogic, HueControlLogic controlLogic, RadioButtonHBox rad){
        setAlignment(Pos.CENTER);
        lbBrightness = new Label("Brightness");
        sldBrightness = viewLogic.createBrightnessSlider(0, 100, 100, controlLogic, rad, viewLogic);
        getChildren().addAll(lbBrightness, sldBrightness);
    }
}
