package main.goldSource;

import lombok.ToString;
import lombok.Value;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;

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

    public DemoHeader(RandomAccessFile raf) throws IOException {
        type = Reader.readString(raf, 8);
        protocol = Reader.readInteger(raf);
        network = Reader.readInteger(raf);
        map = Reader.readString(raf, 260);
        directory = Reader.readString(raf, 260);
        mapCrc = Reader.readInteger(raf);
        offset = Reader.readInteger(raf);
    }

}
