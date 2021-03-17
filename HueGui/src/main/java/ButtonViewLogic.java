import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class ButtonViewLogic implements ColorChangeEvent{
    public ButtonViewLogic(){
    }

    @Override
    public void colorChanged(HueGuiBtnHBoxTop hbTop, Color col) {
        for (int i = 0; i < hbTop.getChildren().size(); i++) {
            if(hbTop.getChildren().get(i)instanceof Button){
                Button cache = (Button) hbTop.getChildren().get(i);
                if(cache.getText().equals("Random")){
                    cache.setBackground(new Background(new BackgroundFill(col, null, null)));
                }
            }
        }
    }
}
