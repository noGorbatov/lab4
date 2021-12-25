package ru.bmstu.akka.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestData {
    final static private String TEST_NAME = "testName";
    final static private String EXPECTED = "expectedResult";
    final static private String PARAMETERS = "params";

    final private String testName;
    final private String expected;
    final private List<Integer> parameters;

    @JsonCreator
    public TestData(@JsonProperty(TEST_NAME) String testName,
                    @JsonProperty(EXPECTED) String expected,
                    @JsonProperty(PARAMETERS) List<Integer> parameters) {
        this.testName = testName;
        this.expected = expected;
        this.parameters = parameters;
    }

    public List<Integer> getParameters() {
        return parameters;
    }

    public String getExpected() {
        return expected;
    }

    public String getTestName() {
        return testName;
    }
}
