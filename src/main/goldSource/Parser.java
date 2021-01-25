package main.goldSource;

import com.google.gson.Gson;
import main.goldSource.directories.Directory;
import main.goldSource.directories.DirectoryEntry;
import main.goldSource.directories.DirectoryEntryFrames;

import java.io.*;

public class Parser {

    protected DemoHeader demoHeader;
    protected Directory directory;
    protected RandomAccessFile raf;

    public Parser(String fileInput) throws IOException {
        System.out.println("Start reading file input: " + fileInput + "...");
        raf = new RandomAccessFile("src/main/resources/demo/" + fileInput, "r");
        System.out.println("Input reading done");

        System.out.println("Start reading demo header...");
        demoHeader = new DemoHeader(raf);
        System.out.println("Demo header reading done");

        raf.seek(demoHeader.offset);

        System.out.println("Start reading directories...");
        directory = new Directory(raf);
        System.out.println("Directories reading done");

        System.out.println("Start reading frames...");
        for (int i = 0; i < directory.entries.size(); i++) {
            DirectoryEntry dir = directory.entries.get(i);
            raf.seek(dir.offset);
            dir.directoryEntryFrames = new DirectoryEntryFrames(raf);
        }
        System.out.println("Frames reading done");

        raf.close();

        Gson gson = new Gson().newBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        System.out.println("Start writing output file...");
        FileWriter file = new FileWriter("hldemo_output.json");
        try {
            file.write(gson.toJson(demoHeader) + gson.toJson(directory));
            System.out.println("Writing output file done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file.flush();
            file.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new Parser("ksz_brickjumps_g-Lp_0020.23.dem");
    }

}
