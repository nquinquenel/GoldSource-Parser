package goldsource.frames;

import goldsource.io.Reader;
import lombok.ToString;
import lombok.Value;

import java.nio.MappedByteBuffer;

@ToString
@Value
public class FrameHeader {

    protected FrameType type;
    protected float time;
    protected int frame;

    public FrameHeader(MappedByteBuffer raf) {
        type = FrameType.getTypeFromByte(Reader.readByte(raf));
        time = Reader.readFloat(raf);
        frame = Reader.readInteger(raf);
    }

}
