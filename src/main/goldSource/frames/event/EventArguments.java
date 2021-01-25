package main.goldSource.frames.event;

import lombok.ToString;
import main.goldSource.io.Reader;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class EventArguments {

    protected int flags;
    protected int entityIndex;
    protected Point3D origin;
    protected Point3D angles;
    protected Point3D velocity;
    protected int ducking;
    protected float fparam1;
    protected float fparam2;
    protected int iparam1;
    protected int iparam2;
    protected int bparam1;
    protected int bparam2;

    public EventArguments(RandomAccessFile raf) throws IOException {
        flags = Reader.readInteger(raf);
        entityIndex = Reader.readInteger(raf);
        origin = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        angles = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        velocity = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        ducking = Reader.readInteger(raf);
        fparam1 = Reader.readFloat(raf);
        fparam2 = Reader.readFloat(raf);
        iparam1 = Reader.readInteger(raf);
        iparam2 = Reader.readInteger(raf);
        bparam1 = Reader.readInteger(raf);
        bparam2 = Reader.readInteger(raf);
    }

}
