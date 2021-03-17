import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.List;

public class HueGuiBtnVBoxTop extends HBox {
    //Specifies layout of VBox Top
    public HueGuiBtnVBoxTop(HueViewLogic viewLogic, HueControlLogic controlLogic, RadioButtonHBox rad){
        setPadding(new Insets(15));
        setSpacing(15);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        List<Button> buttonList = viewLogic.createButtons(controlLogic, rad, viewLogic);
        getChildren().addAll(buttonList);

    }
}
