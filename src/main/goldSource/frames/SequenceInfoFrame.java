package main.goldSource.frames;

import lombok.ToString;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class SequenceInfoFrame implements Frame {

    protected String cmd;
    protected byte[] bxtData;

    public SequenceInfoFrame(RandomAccessFile raf) throws IOException {
        cmd = Reader.readString(raf, 64);
    }

}
