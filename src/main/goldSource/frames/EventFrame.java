package main.goldSource.frames;

import lombok.ToString;
import main.goldSource.io.Reader;
import main.goldSource.frames.event.EventArguments;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class EventFrame implements Frame {

    protected int flags;
    protected int index;
    protected float delay;
    protected EventArguments eventArguments;

    public EventFrame(RandomAccessFile raf) throws IOException {
        flags = Reader.readInteger(raf);
        index = Reader.readInteger(raf);
        delay = Reader.readFloat(raf);
        eventArguments = new EventArguments(raf);
    }

}
