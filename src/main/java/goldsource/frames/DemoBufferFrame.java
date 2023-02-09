package goldsource.frames;

import goldsource.io.Reader;
import lombok.ToString;

import java.nio.MappedByteBuffer;

@ToString
public class DemoBufferFrame implements Frame {

    protected byte[] buffer;

    public DemoBufferFrame(MappedByteBuffer raf, int length) {
        buffer = Reader.readByteArray(raf, length);
    }

}
