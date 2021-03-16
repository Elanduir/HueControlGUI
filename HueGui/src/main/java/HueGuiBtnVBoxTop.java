import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.List;

public class HueGuiBtnVBoxTop extends HBox {
    //Specifies layout of VBox Top
    public HueGuiBtnVBoxTop(){
        setPadding(new Insets(15));
        setSpacing(15);
        setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, null, null)));
        HueViewLogic viewLogic = new HueViewLogic();
        List<Button> buttonList = viewLogic.createButtons();
        getChildren().addAll(buttonList);


    }
}
