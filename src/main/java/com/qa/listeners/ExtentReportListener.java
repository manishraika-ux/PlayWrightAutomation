package com.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static com.qa.demoqa.PlayWrightFactory.takeScreenshot;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent = new ExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("build/ExecutionReport.html");
        spark.config().setReportName("Automation Stack Trace");
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // 1. Log a simple pass message
        test.get().pass("Test passed successfully.");

        // 2. Capture and attach the "Success" screenshot
        String base64Code = takeScreenshot();
        try {
            test.get().pass("Success Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Capture screenshot and attach to the failure log
        String base64Code = takeScreenshot();
        try {
            test.get().fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite execution finished. Generating Extent Report...");
        if (extent != null) {
            extent.flush(); // This is the command that creates the .html file
        }
    }
}