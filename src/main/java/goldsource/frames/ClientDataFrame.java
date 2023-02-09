package goldsource.frames;

import goldsource.io.Reader;
import javafx.geometry.Point3D;
import lombok.Data;
import lombok.ToString;

import java.nio.MappedByteBuffer;

@ToString
@Data
public class ClientDataFrame implements Frame {

    protected Point3D origin;
    protected Point3D viewAngles;
    protected int weaponBits;
    protected float fov;

    public ClientDataFrame(MappedByteBuffer raf) {
        origin = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        Reader.readFloat(raf);
        Reader.readFloat(raf);
        Reader.readFloat(raf);
        weaponBits = Reader.readInteger(raf);
        fov = Reader.readFloat(raf);
    }

}
