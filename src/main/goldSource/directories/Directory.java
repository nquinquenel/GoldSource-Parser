package main.goldSource.directories;

import lombok.ToString;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

@ToString
public class Directory {

    protected int count;
    public List<DirectoryEntry> entries;

    public Directory(RandomAccessFile raf) throws IOException {
        count = Reader.readInteger(raf);
        entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println(i + "/" + count);
            entries.add(new DirectoryEntry(raf));
        }
    }

}
