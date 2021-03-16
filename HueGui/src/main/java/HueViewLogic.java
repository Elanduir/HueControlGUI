import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class HueViewLogic {
    //Creates a button for each color specified in HueControlLogic >> availableColors
    public List<Button> createButtons(){
        int count = 0;
        HueControlLogic hueControl = new HueControlLogic();
        List<Button> buttonList = new ArrayList<>();
        for(int i = 0; i < hueControl.availableColors.size(); i++){
            buttonList.add(new Button());
        }
        for(Button btn : buttonList){
            String color = hueControl.availableColors.get(count);
            btn.setText(color);
            btn.setUserData(color);
            btn.setPadding(new Insets(20));
            count++;
        }
        //Assign setOnMouse event
        assignColors(buttonList);

        return buttonList;
    }
    //Assigns every button the corresponding color event.
    public void assignColors(List<Button> buttonList){
        for(Button btn : buttonList){
            btn.setOnMouseClicked(event -> {
                HueControlLogic assignColor = new HueControlLogic();
                assignColor.setRoomColor(btn.getUserData().toString());
            });
        }
    }

    public ColorPicker createColPick(){
        HueControlLogic controlLogic = new HueControlLogic();
        ColorPicker cp = new ColorPicker();
        cp.setPadding(new Insets(5));
        cp.valueProperty().addListener((c) ->{
            controlLogic.setRoomColorHash(cp.getValue());
        });
        return cp;
    }
}
