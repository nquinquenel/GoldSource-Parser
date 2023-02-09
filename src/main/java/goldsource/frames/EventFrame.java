package goldsource.frames;

import goldsource.frames.event.EventArguments;
import goldsource.io.Reader;
import lombok.ToString;

import java.nio.MappedByteBuffer;

@ToString
public class EventFrame implements Frame {

    protected int flags;
    protected int index;
    protected float delay;
    protected EventArguments eventArguments;

    public EventFrame(MappedByteBuffer raf) {
        flags = Reader.readInteger(raf);
        index = Reader.readInteger(raf);
        delay = Reader.readFloat(raf);
        eventArguments = new EventArguments(raf);
    }

}
