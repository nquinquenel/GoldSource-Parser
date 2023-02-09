package goldsource;

import com.google.gson.Gson;
import goldsource.directories.Directory;
import goldsource.directories.DirectoryEntry;
import goldsource.directories.DirectoryEntryFrames;
import lombok.Data;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Data
public class Parser {

    protected DemoHeader demoHeader;
    protected Directory directory;
    protected RandomAccessFile raf;
    protected Gson gson;
    protected String fileName;

    public static void main(String[] args) throws IOException {
        new Parser();
    }

    public Parser() throws IOException {
        long startTime = System.nanoTime();
        System.out.println("Start reading file input: " + fileName + "...");

        String demo = findDemo();

        raf = new RandomAccessFile(demo, "r");
        FileChannel fileChannel = raf.getChannel();
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

        System.out.println("Input reading done");

        System.out.println("Start reading demo header...");
        demoHeader = new DemoHeader(buffer);
        System.out.println("Demo header reading done");

        buffer.position(demoHeader.offset);

        System.out.println("Start reading directories...");
        directory = new Directory(buffer);
        System.out.println("Directories reading done");

        System.out.println("Start reading frames...");
        for (int i = 0; i < directory.getEntries().size(); i++) {
            DirectoryEntry dir = directory.getEntries().get(i);

            buffer.position(dir.getOffset());

            dir.setDirectoryEntryFrames(new DirectoryEntryFrames(buffer));

            System.out.println("Directory " + (i + 1) + " read");
        }

        System.out.println("Frames reading done");
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Parsing done in " + totalTime/1000000 + " ms");

        raf.close();

        gson = new Gson().newBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        System.out.println("Start writing output file...");
        try (FileWriter file = new FileWriter("hldemo_output.json")) {
            file.write(gson.toJson(demoHeader) + gson.toJson(directory));
            System.out.println("Writing output file done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findDemo() throws FileNotFoundException {
        File dir = new File("demo/");
        if (dir.isDirectory()) {
            String[] list = dir.list((dir1, name) -> name.endsWith(".dem"));
            if (list != null && list.length > 0) {
                return "demo/" + list[0];
            }
        }
        throw new FileNotFoundException("Demo not found in directory demo/");
    }

}
