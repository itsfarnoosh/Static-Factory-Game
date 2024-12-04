package game.system.teleportLocation;

public class InternTeleportLocation implements TeleportLocation{

    private String moonName;
    private int xCoordinate;
    private int yCoordinate;

    public InternTeleportLocation(String moonName, int x, int y) {
        this.moonName = moonName;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public String getMoonName(){
        return moonName;
    }
}
