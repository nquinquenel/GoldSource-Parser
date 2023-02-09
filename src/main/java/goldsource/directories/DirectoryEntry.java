package goldsource.directories;

import goldsource.io.Reader;
import lombok.ToString;

import java.nio.MappedByteBuffer;

@ToString
public class DirectoryEntry {

    protected int index;
    protected String title;
    protected int flag;
    protected int cdTrack;
    protected float time;
    protected int frames;
    protected int length;

    private final int offset;
    private DirectoryEntryFrames directoryEntryFrames;

    public DirectoryEntry(MappedByteBuffer raf) {
        index = Reader.readInteger(raf);
        title = Reader.readString(raf, 64);
        flag = Reader.readInteger(raf);
        cdTrack = Reader.readInteger(raf);
        time = Reader.readFloat(raf);
        frames = Reader.readInteger(raf);
        offset = Reader.readInteger(raf);
        length = Reader.readInteger(raf);
    }

    public int getOffset() {
        return this.offset;
    }

    public void setDirectoryEntryFrames(DirectoryEntryFrames directoryEntryFrames) {
        this.directoryEntryFrames = directoryEntryFrames;
    }

}
