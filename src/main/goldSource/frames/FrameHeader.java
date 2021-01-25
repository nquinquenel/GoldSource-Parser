package main.goldSource.frames;

import lombok.ToString;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class FrameHeader {

    public FrameType type;
    protected float time;
    protected int frame;

    public FrameHeader(RandomAccessFile raf) throws IOException {
        type = FrameType.getTypeFromByte(Reader.readByte(raf));
        time = Reader.readFloat(raf);
        frame = Reader.readInteger(raf);
    }

}
