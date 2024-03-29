package goldsource.frames.network;

import goldsource.io.Reader;
import javafx.geometry.Point3D;
import lombok.Data;
import lombok.ToString;

import java.nio.MappedByteBuffer;

@ToString
@Data
public class NetworkRefParams {

    protected Point3D vieworg;
    protected Point3D viewangles;
    protected Point3D forward;
    protected Point3D right;
    protected Point3D up;
    protected float frametime;
    protected float time;
    protected int intermission;
    protected int paused;
    protected int spectator;
    protected int onground;
    protected int waterlevel;
    protected Point3D simvel;
    protected Point3D simorg;
    protected Point3D viewheight;
    protected float idealpitch;
    protected Point3D clViewangles;
    protected int health;
    protected Point3D crosshairangle;
    protected float viewsize;
    protected Point3D punchangle;
    protected int maxclients;
    protected int viewentity;
    protected int playernum;
    protected int maxEntities;
    protected int demoplayback;
    protected int hardware;
    protected int smoothing;
    protected int ptrCmd;
    protected int ptrMovevars;
    protected float x;
    protected float y;
    protected float z;
    protected float w;
    protected int nextView;
    protected int onlyClientDraw;

    public NetworkRefParams(MappedByteBuffer raf) {
        vieworg = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        viewangles = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        forward = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        right = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        up = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        frametime = Reader.readFloat(raf);
        time = Reader.readFloat(raf);
        intermission = Reader.readInteger(raf);
        paused = Reader.readInteger(raf);
        spectator = Reader.readInteger(raf);
        onground = Reader.readInteger(raf);
        waterlevel = Reader.readInteger(raf);
        for (int i = 0; i < 36; i++) {
            raf.get();
        }
        idealpitch = Reader.readFloat(raf);
        for (int i = 0; i < 12; i++) {
            raf.get();
        }
        health = Reader.readInteger(raf);
        for (int i = 0; i < 12; i++) {
            raf.get();
        }
        viewsize = Reader.readFloat(raf);
        for (int i = 0; i < 12; i++) {
            raf.get();
        }
        maxclients = Reader.readInteger(raf);
        viewentity = Reader.readInteger(raf);
        playernum = Reader.readInteger(raf);
        maxEntities = Reader.readInteger(raf);
        demoplayback = Reader.readInteger(raf);
        hardware = Reader.readInteger(raf);
        smoothing = Reader.readInteger(raf);
        ptrCmd = Reader.readInteger(raf);
        ptrMovevars = Reader.readInteger(raf);
        x = Reader.readInteger(raf);
        y = Reader.readInteger(raf);
        z = Reader.readInteger(raf);
        w = Reader.readInteger(raf);
        nextView = Reader.readInteger(raf);
        onlyClientDraw = Reader.readInteger(raf);
    }

}
