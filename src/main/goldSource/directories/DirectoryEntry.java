package main.goldSource.directories;

import lombok.ToString;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class DirectoryEntry {

    protected int index;
    protected String title;
    protected int flag;
    protected int cdTrack;
    protected float time;
    protected int frames;
    protected int length;

    public int offset;
    public DirectoryEntryFrames directoryEntryFrames;

    public DirectoryEntry(RandomAccessFile raf) throws IOException {
        index = Reader.readInteger(raf);
        title = Reader.readString(raf, 64);
        flag = Reader.readInteger(raf);
        cdTrack = Reader.readInteger(raf);
        time = Reader.readFloat(raf);
        frames = Reader.readInteger(raf);
        offset = Reader.readInteger(raf);
        length = Reader.readInteger(raf);
    }

}
