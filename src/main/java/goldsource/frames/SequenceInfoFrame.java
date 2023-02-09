package goldsource.frames;

import goldsource.io.Reader;
import lombok.ToString;
import lombok.Value;

import java.nio.MappedByteBuffer;

@ToString
@Value
public class SequenceInfoFrame implements Frame {

    protected String cmd;
    protected byte[] bxtData;

    public SequenceInfoFrame(MappedByteBuffer raf) {
        cmd = Reader.readString(raf, 64);
        bxtData = null;
    }

}
