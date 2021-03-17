
import io.github.zeroone3010.yahueapi.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class HueViewLogic {
    //Creates a button for each color specified in HueControlLogic >> availableColors
    public List<Button> createButtons(HueControlLogic hueControl, RadioButtonHBox rad, HueViewLogic viewLogic, HueGuiBtnHBoxTop hbTop){
        int count = 0;
        List<Button> buttonList = new ArrayList<>();
        for(int i = 0; i < hueControl.avColors.size(); i++){
            buttonList.add(new Button());
        }
        for(Button btn : buttonList){
            String btnName = hueControl.avColors.get(count).colorName;
            btn.setText(btnName);
            btn.getProperties().put("color", hueControl.avColors.get(count));
            btn.setPadding(new Insets(5, 10, 5, 10));
            btn.setPrefWidth(90);
            btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            customizeButton(btn);
            count++;
        }
        //Assign setOnMouse event
        assignColors(buttonList, hueControl, rad, viewLogic, hbTop);

        return buttonList;
    }
    //Switches Hex or Color
    public void customizeButton(Button btn){
        availableColors btnCol = (availableColors)(btn.getProperties().get("color"));
        if(btnCol.containsColor()){
            btnBackground(btnCol.color, btn);
        }else if(btnCol.containsValidHex()){
            btnBackground(Color.web(btnCol.colorHex), btn);
        }else{
            btn.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
    }

    //Set background color and luminance -> Text color
    private void btnBackground(Color col, Button btn){
        btn.setBackground(new Background(new BackgroundFill(col,null, null)));
        double r = col.getRed();
        double g = col.getGreen();
        double b = col.getBlue();
        double luma =  sqrt( 0.299*(r*r) + 0.587*(g*g) + 0.114*(b*b) ) * 100;
        if(luma < 55){
            btn.setTextFill(Color.WHITE);
        }
    }

    //Assigns every button the corresponding color event.
    public void assignColors(List<Button> buttonList, HueControlLogic assignColor, RadioButtonHBox rad, HueViewLogic viewLogic, HueGuiBtnHBoxTop hbTop){
        for(Button btn : buttonList){
            btn.setOnMouseClicked(event -> {
                assignColor.setRoomColor((availableColors)btn.getProperties().get("color"), viewLogic, viewLogic.selectedRoom(rad), hbTop);
            });
        }
    }

    public ColorPicker createColPick(HueControlLogic controlLogic, RadioButtonHBox rad, HueViewLogic viewLogic, HueGuiBtnHBoxTop hbTop){
        ColorPicker cp = new ColorPicker();
        cp.setPadding(new Insets(5));
        cp.valueProperty().addListener((c) ->{
            controlLogic.setRoomColor(new availableColors("ColorPicker", cp.getValue()), viewLogic, viewLogic.selectedRoom(rad), hbTop);
        });
        return cp;
    }

    public Slider createBrightnessSlider(double min, double max, double value, HueControlLogic controlLogic, RadioButtonHBox rad, HueViewLogic viewLogic){
        Slider sld = new Slider(min, max, value);
        sld.setShowTickMarks(true);
        sld.setShowTickLabels(true);
        sld.valueProperty().addListener((c) ->{
            controlLogic.setRoomBrightness(viewLogic.selectedRoom(rad), (int)sld.getValue());
        });
        return sld;
    }


    public ListView<Room> createRoomList(HueControlLogic controlLogic){
        ListView<Room> roomListView = new ListView<>();
        ObservableList<Room> observableListRoom = FXCollections.observableArrayList();
        observableListRoom.addAll(controlLogic.roomCollection);
        roomListView.setItems(observableListRoom);
        roomListView.getSelectionModel().select(1);
        return roomListView;
    }

    public List<RadioButton> createRadioButtons(HueControlLogic controlLogic){
        final ToggleGroup roomGroup = new ToggleGroup();
        List<RadioButton> radList = new ArrayList<>();
        RadioButton cache;
        for(Room room : controlLogic.roomCollection){
            cache = new RadioButton(room.getName());
            cache.getProperties().put("room", room);
            cache.setToggleGroup(roomGroup);
            radList.add(cache);
        }

        RadioButton individual = new RadioButton();
        return radList;
    }

    public Room selectedRoom(RadioButtonHBox rad){
        for (int i = 0; i < rad.getChildren().size(); i++) {
            var cache = rad.getChildren().get(i);
            if(cache instanceof RadioButton && ((RadioButton) cache).isSelected()){
                return (Room)cache.getProperties().get("room");
            }
        }
        return null;
    }
}
