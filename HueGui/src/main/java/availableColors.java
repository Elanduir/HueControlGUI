import javafx.scene.paint.Color;

public class availableColors {
    String colorName;
    Color color;
    String colorHex;

    public availableColors(){
        this.colorName = "";
        this.color = null;
        this.colorHex = "";

    }

    public availableColors(String colorName, Color color){
        this.colorName = colorName;
        this.color = color;
        this.colorHex = "";

    }
    public availableColors(String colorName, String colorHex){
        this.colorName = colorName;
        this.color = null;
        this.colorHex = colorHex;

    }

    public availableColors parseObject(Object obj){
        System.out.println(obj);
        return null;
    }

    public String toString(){
        return color.toString();
    }

    public boolean containsColor(){
        return color != null;
    }

    public boolean containsValidHex(){
        if(colorHex.length() == 7 && colorHex.matches("^[a-fA-F0-9#]+$")){
            return true;
        }else{
            return false;
        }
    }

}
