package ru.bmstu.akka.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestPerformerActor extends AbstractActor {
    final private static String ENGINE_NAME = "nashorn";

    public static class RunTestMsg {
        final private TestData testPackage;
        final private ActorRef storageActor;
        public RunTestMsg(TestData tests, ActorRef storageActor) {
            this.testPackage = tests;
            this.storageActor = storageActor;
        }
        public ActorRef getStorageActor() {
            return storageActor;
        }
        public TestData getTestPackage() {
            return testPackage;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(RunTestMsg.class, this::runTest).
                build();
    }

    private void runTest(RunTestMsg testMsg) {
        ScriptEngine engine = new ScriptEngineManager().
                                    getEngineByName(ENGINE_NAME);
        engine.eval(testMsg.getTestPackage().)
    }
}
