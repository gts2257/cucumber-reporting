package net.masterthought.cucumber.generators;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.ReportResult;

public class ErrorPage extends AbstractPage {

    private final Exception exception;
    private final List<String> jsonFiles;

    public ErrorPage(ReportResult reportResult, Configuration configuration, Exception exception,
            List<String> jsonFiles) {
        super(reportResult, "errorPage.vm", configuration);
        this.exception = exception;
        this.jsonFiles = jsonFiles;
    }

    @Override
    public String getWebPage() {
        return ReportBuilder.HOME_PAGE;
    }

    @Override
    public void prepareReport() {
        velocityContext.put("output_message", ExceptionUtils.getStackTrace(exception));
        velocityContext.put("json_files", jsonFiles);
    }
}
