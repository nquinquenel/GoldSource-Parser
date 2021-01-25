package main.goldSource.frames.networkMessage;

import lombok.ToString;
import main.goldSource.io.Reader;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
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

    public UserCmd(RandomAccessFile raf) throws IOException {
        lerpMsec = Reader.readShort(raf);
        msec = Reader.readByte(raf);
        align1 = Reader.readByte(raf);
        viewangles = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
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
        impactPosition = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
    }

}
