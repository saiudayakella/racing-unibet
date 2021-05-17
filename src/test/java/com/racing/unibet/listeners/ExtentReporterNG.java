package com.racing.unibet.listeners;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;    
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();
 
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }
        
        for (String s : Reporter.getOutput()) {
            extent.addTestRunnerOutput(s);
        }
 
        extent.flush();
    }
    
    private void init() {
    	ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/ExecutionReport.html");
    	extent = new ExtentReports();		
		extent.attachReporter(spark);
    }
 
    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;
 
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName()); 
                
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
 
                if (result.getThrowable() != null) {
                	test.log(status, result.getThrowable());                	
                }
                else {
                	test.log(status, "Test " + status.toString().toLowerCase() + "ed");               	
                }
                
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }
 
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }
}
