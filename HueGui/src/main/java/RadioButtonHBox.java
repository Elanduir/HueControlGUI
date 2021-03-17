import io.github.zeroone3010.yahueapi.Room;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class RadioButtonHBox extends VBox {
    public List<RadioButton> radioButtons;
    public RadioButtonHBox(){
        setSpacing(5);
    }

    public void initRadioButtons(HueViewLogic viewLogic, HueControlLogic controlLogic){
        radioButtons = viewLogic.createRadioButtons(controlLogic);
        radioButtons.get(0).setSelected(true);
        this.getChildren().addAll(radioButtons);
    }


}
