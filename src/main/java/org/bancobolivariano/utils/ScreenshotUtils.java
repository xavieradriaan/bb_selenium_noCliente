package org.bancobolivariano.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.bancobolivariano.listeners.ExtentReportListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScreenshotUtils {

    public static void addScreenshotToReport(WebDriver driver, String screenshotName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "target/screenshots/" + screenshotName + ".png";
        String reportPath = "screenshots/" + screenshotName + ".png";
        try {
            Files.createDirectories(Paths.get("target/screenshots/"));
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath), StandardCopyOption.REPLACE_EXISTING);
            ExtentReportListener.getTest().log(Status.INFO, "Screenshot: " + screenshotName,
                    MediaEntityBuilder.createScreenCaptureFromPath(reportPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void embedImagesInHtml(String htmlFilePath) throws IOException {
        String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));
        Pattern pattern = Pattern.compile("src=\"(.*?)\"");
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            String imagePath = matcher.group(1);
            if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
                continue;
            }
            imagePath = "target/" + imagePath;
            try {
                String base64Image = encodeImageToBase64(imagePath);
                String replacement = "src=\"data:image/png;base64," + base64Image + "\"";
                htmlContent = htmlContent.replace(matcher.group(0), replacement);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Files.write(Paths.get("target/report.html"), htmlContent.getBytes());
        Files.delete(Paths.get(htmlFilePath));
    }

    public static String encodeImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte[] imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);
            return Base64.getEncoder().encodeToString(imageData);
        }
    }

}
