package goldsource.directories;

import goldsource.io.Reader;
import lombok.ToString;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;

@ToString
public class Directory {

    protected int count;
    private final List<DirectoryEntry> entries;

    public Directory(MappedByteBuffer raf) {
        count = Reader.readInteger(raf);
        entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            entries.add(new DirectoryEntry(raf));
        }
    }

    public List<DirectoryEntry> getEntries() {
        return this.entries;
    }

}
