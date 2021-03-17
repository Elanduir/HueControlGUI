import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.List;

public class HueGuiBtnHBoxTop extends HBox {
    //Specifies layout of VBox Top
    public HueGuiBtnHBoxTop(HueViewLogic viewLogic, HueControlLogic controlLogic, RadioButtonHBox rad){
        setPadding(new Insets(15));
        setSpacing(15);
        List<Button> buttonList = viewLogic.createButtons(controlLogic, rad, viewLogic, this);
        getChildren().addAll(buttonList);

    }
}
