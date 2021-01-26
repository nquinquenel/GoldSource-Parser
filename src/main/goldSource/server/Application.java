package main.goldSource.server;

import main.goldSource.Parser;
import spark.ModelAndView;
import spark.Request;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Application {

    public static void main(String[] args) throws IOException {
        File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Thymeleaf!");
            return new ModelAndView(model, "demo"); // located in resources/templates
        }, new ThymeleafTemplateEngine());

        post("/", (request, response) -> {
            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
                Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
                logInfo(request, tempFile);
                new Parser(tempFile.getFileName().toString());

                byte[] data = Files.readAllBytes(Paths.get("hldemo_output.json"));

                HttpServletResponse raw = response.raw();
                response.header("Content-Disposition", "attachment; filename=hldemo_output.json");
                response.type("application/force-download");
                try {
                    raw.getOutputStream().write(data);
                    raw.getOutputStream().flush();
                    raw.getOutputStream().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                purgeDirectory(new File("upload"));

                return raw;
            }
        });
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
        for (File file: dir.listFiles()) {
            if (file.isDirectory())
                purgeDirectory(file);
            file.delete();
        }
    }

}