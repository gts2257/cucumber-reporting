package net.masterthought.cucumber.generators;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.ReportResult;

public class FeaturesOverviewPage extends AbstractPage {

    public FeaturesOverviewPage(ReportResult reportResult, Configuration configuration) {
        super(reportResult, "featuresOverview.vm", configuration);
    }

    @Override
    public String getWebPage() {
        return ReportBuilder.HOME_PAGE;
    }

    @Override
    public void prepareReport() {
        velocityContext.put("all_features", report.getAllFeatures());
        velocityContext.put("all_steps", report.getStepsCounter());
        velocityContext.put("all_steps_passes", report.getAllPassedSteps());
        velocityContext.put("all_steps_failed", report.getAllFailedSteps());
        velocityContext.put("all_steps_skipped", report.getAllSkippedSteps());
        velocityContext.put("all_steps_pending", report.getPendingStepsl());
        velocityContext.put("all_steps_undefined", report.getUndefinedSteps());
        velocityContext.put("all_steps_missing", report.getTotalStepsMissing());
        velocityContext.put("all_scenarios", report.getAllScenarios());
        velocityContext.put("all_scenarios_passed", report.getAllPassedScenarios());
        velocityContext.put("all_scenarios_failed", report.getAllFailedScenarios());
        velocityContext.put("all_features_passed", report.getAllPassedFeatures());
        velocityContext.put("all_features_failed", report.getAllFailedFeatures());

        velocityContext.put("all_durations", report.getAllDurationsAsString());
        velocityContext.put("parallel", configuration.isParallelTesting());
    }
}
