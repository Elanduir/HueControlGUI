import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class HueGui extends BorderPane {
    Button btnSetGreen;
    Button btnSetWhite;
    Button btnSetPink;

    public HueGui(){
        setPadding(new Insets(30));
        setTop(new HueGuiBtnVBoxTop());
        setCenter(new HueGuiColPickCenter());

    }

}
