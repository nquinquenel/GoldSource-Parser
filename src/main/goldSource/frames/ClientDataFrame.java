package main.goldSource.frames;

import lombok.ToString;
import main.goldSource.io.Reader;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class ClientDataFrame implements Frame {

    protected Point3D origin;
    protected Point3D viewAngles;
    protected int weaponBits;
    protected float fov;

    public ClientDataFrame(RandomAccessFile raf) throws IOException {
        origin = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        viewAngles = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        weaponBits = Reader.readInteger(raf);
        fov = Reader.readFloat(raf);
    }

}
