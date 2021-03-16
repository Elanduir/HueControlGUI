import io.github.zeroone3010.yahueapi.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HueControlLogic {
    private String bridgeIp;
    private String apiKey;
    private Hue hue;
    private Room fRoom;


    //Initialize connection with HueHub
    public void initHueControl() {
        bridgeIp = "192.168.10.104";
        apiKey = "BNJ2ndL9yZQy3Sl98AKBQPRgCX4dmJDN420OdNNR";
        hue = new Hue(bridgeIp, apiKey);
        fRoom = hue.getRoomByName("Fabian Bedroom").get();
    }

    public void rest(Hue hue, Room fRoom) throws InterruptedException {

        Collection<Light> lights = fRoom.getLights();
        for(int i = 0; i < 10; i++){
            for(Light light : lights){
                light.setState(State.builder().color(Color.of(randColor())).on());
            }
            TimeUnit.SECONDS.sleep(1);
        }
        fRoom.setState(State.builder().color(Color.of(java.awt.Color.BLACK)).off());
    }

    //Specifies all available Colors.
    public List<String> availableColors = List.of("White", "Green", "Pink", "Cyan", "Random");

    //Sets room color.
    public void setRoomColor(String switchString){
        initHueControl();
        fRoom.setState(State.builder().color(Color.of(switchCol(switchString))).on());
    }

    public void setRoomColorHash(javafx.scene.paint.Color hashCol){
        initHueControl();
        fRoom.setState(State.builder().color(Color.of(hashCol)).on());
    }

    //returns java.awt.Color
    private java.awt.Color switchCol(String switchString){
        switch(switchString){
            case "White":
                return java.awt.Color.WHITE;
            case "Green":
                return java.awt.Color.GREEN;
            case "Pink":
                return java.awt.Color.PINK;
            case "Random":
                return randColor();
            default:
                return java.awt.Color.CYAN;
        }
    }
    //Returns random color.
    private java.awt.Color randColor(){
        return new java.awt.Color(((int)(Math.random()*175)+80), ((int)(Math.random()*175)+80), ((int)(Math.random()*175)+80));
    }
}
