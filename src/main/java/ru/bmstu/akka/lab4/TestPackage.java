package ru.bmstu.akka.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestPackage {
    final static private String PACKAGE_ID = "packageId";
    final static private String JS_SCRIPT = "jsScript";
    final static private String TESTS = "tests";

    private int id;
    private String script;
    private String functionName;
    private List<TestData> tests;

    @JsonCreator
    public TestPackage(@JsonProperty(PACKAGE_ID) int id,
                       @JsonProperty(JS_SCRIPT) String script,
                       @JsonProperty(TESTS) List<TestData> tests) {
        this.id = id;
        this.script = script;
        this.tests = tests;
    }
}
