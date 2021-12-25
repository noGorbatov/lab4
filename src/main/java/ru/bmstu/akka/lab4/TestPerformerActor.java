package ru.bmstu.akka.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class TestPerformerActor extends AbstractActor {

    public static class RunTestsMsg {
        final private TestPackage testPackage;
        final private ActorRef storageActor;
        public RunTestsMsg(TestPackage tests, ActorRef storageActor) {
            this.testPackage = tests;
            this.storageActor = storageActor;
        }
        public ActorRef getStorageActor() {
            return storageActor;
        }
        public TestPackage getTestPackage() {
            return testPackage;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(RunTestsMsg.class, );
    }

    private void runTests(RunTestsMsg tests) {
        
    }
}
