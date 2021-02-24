package main.goldSource.server;

import main.goldSource.Parser;
import spark.ModelAndView;
import spark.Request;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Application {

    private static Parser PARSER;
    private static long PARSING_STATUS = 0;
    private static long PARSING_TOTAL = 0;

    public static void main(String[] args) throws IOException {
        exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
        File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist

        staticFiles.location("/");

        ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "demo"); // located in resources/templates
        }, engine);

        get("/status", (request, response) -> {
            System.out.println("PARSING: " + PARSING_STATUS);
            return PARSING_TOTAL == 0 ? 0 : ((double) PARSING_STATUS / PARSING_TOTAL) * 100;
        });

        get("/information", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("type", PARSER.getHeaders().getType());
            model.put("protocol", PARSER.getHeaders().getProtocol());
            model.put("network", PARSER.getHeaders().getNetwork());
            model.put("map", PARSER.getHeaders().getMap());
            model.put("directory", PARSER.getHeaders().getDirectory());
            model.put("mapcrc", PARSER.getHeaders().getMapCrc());
            model.put("offset", PARSER.getHeaders().getOffset());

            return new ModelAndView(model, "information"); // located in resources/templates
        }, engine);

        post("/upload", (request, response) -> {
            PARSING_STATUS = 0;
            PARSING_TOTAL = 0;
            purgeDirectory(new File("upload"));
            purgeDirectory(new File("json"));
            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
                Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
                logInfo(request, tempFile);
                PARSER = new Parser(tempFile.getFileName().toString());
                PARSER.writeOutputFile();
            }
            return "File uploaded";
        });
    }

    public static void increaseStatus(long value) {
        PARSING_STATUS += value;
    }

    public static void setStatus(long value) {
        PARSING_STATUS = value;
    }

    public static void setParsingTotal(long value) {
        PARSING_TOTAL = value;
    }

    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("uploaded_file")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private static void purgeDirectory(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory())
                purgeDirectory(file);
            file.delete();
        }
    }

}