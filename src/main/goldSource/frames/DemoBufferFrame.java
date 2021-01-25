package main.goldSource.frames;

import lombok.ToString;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class DemoBufferFrame implements Frame {

    protected byte[] buffer;

    public DemoBufferFrame(RandomAccessFile raf, int length) throws IOException {
        buffer = Reader.readByteArray(raf, length);
    }

}
