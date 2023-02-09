package goldsource.frames.network;

import goldsource.io.Reader;
import javafx.geometry.Point3D;
import lombok.ToString;
import lombok.Value;

import java.nio.MappedByteBuffer;

@ToString
@Value
public class UserCmd {

    protected short lerpMsec;
    protected byte msec;
    protected byte align1;
    protected Point3D viewangles;
    protected float forwardmove;
    protected float sidemove;
    protected float upmove;
    protected byte lightlevel;
    protected byte align2;
    protected short buttons;
    protected byte impulse;
    protected byte weaponselect;
    protected byte align3;
    protected byte align4;
    protected int impactIndex;
    protected Point3D impactPosition;

    public UserCmd(MappedByteBuffer raf) {
        lerpMsec = Reader.readShort(raf);
        msec = Reader.readByte(raf);
        align1 = Reader.readByte(raf);
        viewangles = null;
        for (int i = 0; i < 12; i++) {
            raf.get();
        }
        forwardmove = Reader.readFloat(raf);
        sidemove = Reader.readFloat(raf);
        upmove = Reader.readFloat(raf);
        lightlevel = Reader.readByte(raf);
        align2 = Reader.readByte(raf);
        buttons = Reader.readShort(raf);
        impulse = Reader.readByte(raf);
        weaponselect = Reader.readByte(raf);
        align3 = Reader.readByte(raf);
        align4 = Reader.readByte(raf);
        impactIndex = Reader.readInteger(raf);
        for (int i = 0; i < 12; i++) {
            raf.get();
        }
        impactPosition = null;
    }

}
