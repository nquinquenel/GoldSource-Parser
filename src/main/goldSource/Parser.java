package main.goldSource;

import com.google.gson.Gson;
import lombok.Data;
import main.goldSource.directories.Directory;
import main.goldSource.directories.DirectoryEntry;
import main.goldSource.directories.DirectoryEntryFrames;
import main.goldSource.server.Application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

@Data
public class Parser {

    protected DemoHeader demoHeader;
    protected Directory directory;
    protected RandomAccessFile raf;
    protected Gson gson;

    public Parser(String fileInput) throws IOException {
        System.out.println("Start reading file input: " + fileInput + "...");
        raf = new RandomAccessFile("upload/" + fileInput, "r");
        Application.setParsingTotal(raf.length());
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

            System.out.println("Directory " + (i + 1) + " read");
        }
        Application.setStatus(raf.length());
        System.out.println("Frames reading done");

        gson = new Gson().newBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        raf.close();
    }

    public void writeOutputFile() throws IOException {
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

    public DemoHeader getHeaders() {
        return demoHeader;
    }

    public String getJson() {
        return gson.toJson(demoHeader) + gson.toJson(directory);
    }

}
