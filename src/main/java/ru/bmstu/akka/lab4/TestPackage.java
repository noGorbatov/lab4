package ru.bmstu.akka.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestPackage {
    final static private String PACKAGE_ID = "packageId";
    final static private String JS_SCRIPT = "jsScript";
    final static private String TESTS = "tests";
    final static private String FUNC_NAME = "functionName";

    final private int id;
    final private String script;
    final private String functionName;
    final private List<TestData> tests;

    @JsonCreator
    public TestPackage(@JsonProperty(PACKAGE_ID) int id,
                       @JsonProperty(JS_SCRIPT) String script,
                       @JsonProperty(FUNC_NAME) String functionName,
                       @JsonProperty(TESTS) List<TestData> tests) {
        this.id = id;
        this.script = script;
        this.functionName = functionName;
        this.tests = tests;
    }

    public int getId() {
        return id;
    }

    public String getJsScript() {
        return script;
    }

    public List<TestData> getTests() {
        return tests;
    }

    public String getFuncName() {
        return functionName;
    }
}
