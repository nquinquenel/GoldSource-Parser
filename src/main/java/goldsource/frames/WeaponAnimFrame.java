package goldsource.frames;

import goldsource.io.Reader;
import lombok.ToString;

import java.nio.MappedByteBuffer;

@ToString
public class WeaponAnimFrame implements Frame {

    protected int anim;
    protected int body;

    public WeaponAnimFrame(MappedByteBuffer raf) {
        anim = Reader.readInteger(raf);
        body = Reader.readInteger(raf);
    }

}
