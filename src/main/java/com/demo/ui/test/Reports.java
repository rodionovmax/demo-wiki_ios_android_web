package com.demo.ui.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reports {

    private static final boolean jenkinsOption = true;

    // initialize the HtmlReporter
    public static ExtentHtmlReporter htmlReporter;

    // initialize com.benefithub.TestReports and attach the HtmlReporter
    public static ExtentReports extent;
    private static ExtentTest currentTest;
    private static String lastAction;
    private  static final String ROOT_PATH = "reports/";
    private  static  String currentTestSuiteResultsPath;

    static {
        LocalDateTime ldt =  LocalDateTime.now();
        String formattedDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
//        currentTestSuiteResultsPath = "Suite " + formattedDate+ "/";
        if(jenkinsOption){
            currentTestSuiteResultsPath = "Suite/";
        } else {
            currentTestSuiteResultsPath = "Suite " + formattedDate + "/";
        }
        new File(ROOT_PATH + currentTestSuiteResultsPath).mkdir();
        htmlReporter = new ExtentHtmlReporter(ROOT_PATH + currentTestSuiteResultsPath + "report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // load config for html report
        htmlReporter.loadXMLConfig("extent-config.xml");
    }

    public static void start(String testName) {
        currentTest = extent.createTest(testName);
    }

    public static void stop() {
        extent.flush();
    }

    public static void log(String message) {
        currentTest.log(Status.PASS, message);
        System.out.println(message);
        lastAction = message;
    }

    public static void fail(WebDriver driver, String methodName) {

        try {

            LocalDateTime ldt =  LocalDateTime.now();
            String formattedDate = ldt.format(DateTimeFormatter.ofPattern("HH-mm-ss"));

            currentTest.fail("Failed step: " + lastAction);
            File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destinationPath = Paths.get(ROOT_PATH + currentTestSuiteResultsPath +"fail_" + methodName + "_" + formattedDate + ".png");
            Files.copy(imageFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            currentTest.addScreenCaptureFromPath(destinationPath.toFile().getAbsolutePath());
//            currentTest.addScreenCaptureFromPath("./screenshot.jpg");
//            currentTest.fail("Screenshot for failed step: ", MediaEntityBuilder.createScreenCaptureFromPath(destinationPath.toFile().getAbsolutePath()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
