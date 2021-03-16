import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;

public class HueGuiColPickCenter extends HBox {
    ColorPicker colPick;
    public HueGuiColPickCenter(){
        setPadding(new Insets(15,0,0, 0));
        HueViewLogic viewLogic = new HueViewLogic();
        colPick = viewLogic.createColPick();
        getChildren().add(colPick);
    }

}
