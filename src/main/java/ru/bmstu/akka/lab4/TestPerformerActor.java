package ru.bmstu.akka.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestPerformerActor extends AbstractActor {
    final private static String ENGINE_NAME = "nashorn";

    public static class RunTestMsg {
        final private String script;
        final private String funcName;
        final private TestData testData;
        final private ActorRef storageActor;
        public RunTestMsg(TestData test, String script, String funcName, ActorRef storageActor) {
            this.testData = test;
            this.script = script;
            this.funcName = funcName;
            this.storageActor = storageActor;
        }
        public ActorRef getStorageActor() {
            return storageActor;
        }
        public TestData getTestData() {
            return testData;
        }
        public String getScript() {
            return script;
        }
        public String getFuncName() {
            return funcName;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(RunTestMsg.class, this::runTest).
                build();
    }

    private void runTest(RunTestMsg testMsg) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().
                                    getEngineByName(ENGINE_NAME);
        engine.eval(testMsg.getScript());
        Invocable invocable = (Invocable) engine;
        String result = invocable.invokeFunction(testMsg.getFuncName(), testMsg.getTest)
    }
}
