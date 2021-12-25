package ru.bmstu.akka.lab4;

import akka.actor.AbstractActor;

public class StorageActor extends AbstractActor {

    public static class StoreMsg {
        private int packageId;
        private String results;
        public StoreMsg(int id, String results) {
            this.packageId = id;
            this.results = results;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match()
    }
}
