import io.github.zeroone3010.yahueapi.*;
import java.util.Collection;
import java.util.List;
import java.util.prefs.Preferences;
import io.github.zeroone3010.yahueapi.Color;



public class HueControlLogic {
    private Preferences prefs;
    private String bridgeIp = "192.168.10.104";
    private String apiKey = "BNJ2ndL9yZQy3Sl98AKBQPRgCX4dmJDN420OdNNR";
    public Hue hue = new Hue(bridgeIp, apiKey);
    public Room fRoom = hue.getRoomByName("Fabian Bedroom").get();
    public Collection<Room> roomCollection = hue.getRooms();

//        Collection<Light> lights = fRoom.getLights();


    //Specifies all available Colors.
    public List<availableColors> avColors = List.of(
            new availableColors("White", javafx.scene.paint.Color.WHITE),
            new availableColors("Red","#ff0000"),
            new availableColors("Green", "#00ff00"),
            new availableColors("Blue","#0000ff"),
            new availableColors("Random", "rand"),
            new availableColors("Power Off", "poweroff")
    );

    //Sets room color.
    public void setRoomColor(availableColors col, HueViewLogic viewLogic, Room room){
        if (col.containsColor()) {
            room.setState(State.builder().color(Color.of(col.color)).on());
        } else {
            switch (col.colorHex) {
                case "poweroff":
                    room.setState(State.builder().color(Color.of(javafx.scene.paint.Color.BLACK)).off());
                    break;
                case "rand":
                    room.setState(State.builder().color(Color.of(randColor())).on());
                    break;
                default:
                    room.setState(State.builder().color(Color.of(javafx.scene.paint.Color.web(col.colorHex))).on());
                }
            }
            setRoomBrightness(room, 100);

    }
    //Sets room brightness
    public void setRoomBrightness(Room setRoom, int brightnessPercent){
        int brightness = 255/100*brightnessPercent;
        setRoom.setBrightness(brightness);
    }

    //Returns random color.
    private javafx.scene.paint.Color randColor(){
        int r = (int)((Math.random()*175)+80);
        int g = (int)((Math.random()*175)+80);
        int b = (int)((Math.random()*175)+80);
        String hexCol = String.format("#%02x%02x%02x", r, g, b);
        return javafx.scene.paint.Color.web(hexCol);
    }

    public void setPreference(){
        prefs = Preferences.userRoot().node(this.getClass().getName());
        if(prefs.get("ip", "").equals("")){
            prefs.put("ip", "192.168.10.104");
            System.out.println("Set Ip");
        }
        if(prefs.get("apiKey", "").equals("")){
            prefs.put("apiKey", "BNJ2ndL9yZQy3Sl98AKBQPRgCX4dmJDN420OdNNR");
            System.out.println("Set apiKey");
        }


    }
}
