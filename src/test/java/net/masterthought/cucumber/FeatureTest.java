package net.masterthought.cucumber;

import static net.masterthought.cucumber.FileReaderUtil.getAbsolutePathFromResource;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;

import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.support.Status;

public class FeatureTest {

    private final Configuration configuration = new Configuration(new File(""), "testProject");

    private Feature passingFeature;
    private Feature failingFeature;

    @Before
    public void setUpJsonReports() throws IOException {
        List<String> jsonReports = new ArrayList<>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/project1.json"));
        List<Feature> features = new ReportParser(configuration).parseJsonResults(jsonReports);
        passingFeature = features.get(0);
        failingFeature = features.get(1);
    }

    @Test
    public void shouldReturnManagedFileName() {
        assertThat(passingFeature.getReportFileName(), is("net-masterthought-example-s--ATM-local-feature.html"));
    }

    @Test
    public void shouldGetDescription() {
        assertThat(passingFeature.getDescription(), is(
                "As a Account Holder\nI want to withdraw cash from an ATM\nSo that I can get money when the bank is closed"));
    }

    @Test
    public void shouldGetId() {
        assertThat(passingFeature.getId(), is("account-holder-withdraws-cash"));
    }

    @Test
    public void shouldKnowIfTagsExists() {
        assertThat(passingFeature.hasTags(), is(true));
    }

    @Test
    public void shouldListTheTags() {
        String name = "@featureTag";
        assertThat(passingFeature.getTags()[0].getName(), is(name));
    }

    @Test
    public void shouldGetStatus() {
        assertThat(passingFeature.getStatus(), is(Status.PASSED));
    }

    @Test
    public void shouldReturnName() {
        assertThat(passingFeature.getName(), is("Account Holder withdraws cash & <MyPlace>"));
    }

    @Test
    public void shouldReturnEmptyName() {
        assertThat(failingFeature.getName(), is(""));
    }

    @Test
    public void shouldReturnRawName() {
        assertThat(passingFeature.getRawName(), is("Account Holder withdraws cash &amp; &lt;MyPlace&gt;"));
    }

    @Test
    public void shouldReturnEmptyRawName() {
        assertThat(failingFeature.getRawName(), is(""));
    }

    @Test
    public void shouldReturnRawStatus() {
        assertThat(passingFeature.getRawStatus(), is("passed"));
    }

    @Test
    public void shouldGetNumberOfSteps() {
        assertThat(passingFeature.getNumberOfSteps(), is(40));
    }

    @Test
    public void shouldGetNumberOfPassingSteps() {
        assertThat(passingFeature.getNumberOfPasses(), is(40));
        assertThat(failingFeature.getNumberOfPasses(), is(5));

    }

    @Test
    public void shouldGetNumberOfFailingSteps() {
        assertThat(passingFeature.getNumberOfFailures(), is(0));
        assertThat(failingFeature.getNumberOfFailures(), is(1));
    }

    @Test
    public void shouldGetNumberOfSkippedSteps() {
        assertThat(passingFeature.getNumberOfSkipped(), is(0));
        assertThat(failingFeature.getNumberOfSkipped(), is(3));
    }

    @Test
    public void shouldGetNumberOfPendingSteps() {
        assertThat(passingFeature.getNumberOfPending(), is(0));
    }

    @Test
    public void shouldGetNumberOfMissingSteps() {
        assertThat(passingFeature.getNumberOfMissing(), is(0));
    }

    @Test
    public void shouldgetTotalDuration() {
        assertThat(passingFeature.getTotalDuration(), StringContains.containsString("ms"));
    }

    @Test
    public void shouldGetNumberOScenarios() {
        assertThat(passingFeature.getScenarios(), is(4));
    }

    @Test
    public void shouldProcessFeatureWhenNoScenarios() throws IOException {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/noscenario.json"));
        new ReportParser(configuration).parseJsonResults(jsonReports);
    }

    @Test
    public void shouldGetNumberOfPassingScenarios() {
        assertThat(passingFeature.getPassedScenarios(), is(4));
    }

    @Test
    public void shouldGetNumberOfFailingScenarios() {
        assertThat(failingFeature.getFailedScenarios(), is(1));
    }

}