package goldsource;

import goldsource.io.Reader;
import lombok.ToString;
import lombok.Value;

import java.nio.MappedByteBuffer;

@ToString
@Value
public class DemoHeader {

    protected String type;
    protected int protocol;
    protected int network;
    protected String map;
    protected String directory;
    protected int mapCrc;
    protected int offset;

    public DemoHeader(MappedByteBuffer raf) {
        type = Reader.readString(raf, 8);
        protocol = Reader.readInteger(raf);
        network = Reader.readInteger(raf);
        map = Reader.readString(raf, 260);
        directory = Reader.readString(raf, 260);
        mapCrc = Reader.readInteger(raf);
        offset = Reader.readInteger(raf);
    }

}
