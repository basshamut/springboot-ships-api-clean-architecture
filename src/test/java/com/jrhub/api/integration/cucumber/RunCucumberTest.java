package com.jrhub.api.integration.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:/integration/cucumber", glue = "com.jrhub.api.integration.cucumber")
public class RunCucumberTest {
}