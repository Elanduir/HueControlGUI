import io.github.zeroone3010.yahueapi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import io.github.zeroone3010.yahueapi.Color;
import io.github.zeroone3010.yahueapi.discovery.HueBridgeDiscoveryService;
import javafx.scene.control.Alert;


public class HueControlLogic {
    private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());;
    private final String appName = "HueControl";
    private String bridgeIp;
    private String apiKey;
    public Hue hue;
    public Collection<Room> roomCollection;

//        Collection<Light> lights = fRoom.getLights();
    public void initHueConnection() throws ExecutionException, InterruptedException, BackingStoreException {
        prefs.clear();;
        bridgeIp = getIp(prefs);
        apiKey = getApiKey(prefs);
        if(bridgeIp.equals("")){
            bridgeIp = discoverBridge();
            prefs.put("ip", bridgeIp);
        }
        if(apiKey.equals("")){
            apiKey = initApiKey(bridgeIp, appName);
            prefs.put("apiKey", apiKey);
        }
        hue = new Hue(bridgeIp, apiKey);
        roomCollection = hue.getRooms();

    }


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
    public void setRoomColor(availableColors col, HueViewLogic viewLogic, Room room, HueGuiBtnHBoxTop hbTop){
        ButtonViewLogic btnViewLogic = new ButtonViewLogic();
        List<ColorChangeEvent> cCEvent = new ArrayList<>();
        cCEvent.add(btnViewLogic);

        if (col.containsColor()) {
            room.setState(State.builder().color(Color.of(col.color)).on());
        } else {
            switch (col.colorHex) {
                case "poweroff":
                    room.setState(State.builder().color(Color.of(javafx.scene.paint.Color.BLACK)).off());
                    break;
                case "rand":
                    javafx.scene.paint.Color rCol = randColor();
                    room.setState(State.builder().color(Color.of(rCol)).on());
                    btnViewLogic.colorChanged(hbTop, rCol);
                    break;
                default:
                    room.setState(State.builder().color(Color.of(javafx.scene.paint.Color.web(col.colorHex))).on());
                }
            }
            setRoomBrightness(room, 100);

    }
    //Sets room brightness
    public void setRoomBrightness(Room setRoom, double brightnessPercent){
        double max = 254;
        double p = 100;
        double  brightness = (max/p)*brightnessPercent;
        setRoom.setBrightness((int)brightness);
    }

    //Returns random color
    private javafx.scene.paint.Color randColor(){
        int r = (int)((Math.random()*175)+80);
        int g = (int)((Math.random()*175)+80);
        int b = (int)((Math.random()*175)+80);
        String hexCol = String.format("#%02x%02x%02x", r, g, b);
        return javafx.scene.paint.Color.web(hexCol);
    }

    private String getIp(Preferences prefs){
        return prefs.get("ip", "");
    }

    private String getApiKey(Preferences prefs){
        return prefs.get("apiKey", "");
    }

    private String discoverBridge() throws ExecutionException, InterruptedException {
        Future<List<HueBridge>> bridgesFuture = new HueBridgeDiscoveryService()
                .discoverBridges(bridge -> System.out.println("Bridge found: " + bridge));
        final List<HueBridge> bridges = bridgesFuture.get();
        if( !bridges.isEmpty() ) {
            final String bridgeIp = bridges.get(0).getIp();
            System.out.println("Bridge found at " + bridgeIp);

            return bridgeIp;
        }
        return null;
    }

    private String initApiKey(String bridgeIp, String appName) throws ExecutionException, InterruptedException {
        final CompletableFuture<String> keyFuture = Hue.hueBridgeConnectionBuilder(bridgeIp).initializeApiConnection(appName);
        final String apiKey = keyFuture.get();
        System.out.println("Store this API key for future use: " + apiKey);
        return apiKey;
    }

}
