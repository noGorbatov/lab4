package ru.bmstu.akka.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestPackage {
    final static private String PACKAGE_ID = "packageId";
    final static private String JS_SCRIPT = "jsScript";
    final static private String TESTS = "tests";

    final private int id;
    final private String script;
    private String functionName;
    final private List<TestData> tests;

    @JsonCreator
    public TestPackage(@JsonProperty(PACKAGE_ID) int id,
                       @JsonProperty(JS_SCRIPT) String script,
                       @JsonProperty(TESTS) List<TestData> tests) {
        this.id = id;
        this.script = script;
        this.tests = tests;
    }

    public int getId() {
        return id;
    }

    public static String getJsScript() {
        return JS_SCRIPT;
    }

    public List<TestData> getTests() {
        return tests;
    }
}
