package ru.bmstu.akka.lab4;

import akka.actor.AbstractActor;

public class StorageActor extends AbstractActor {
    Map<Integer, String> storage =
    public static class StoreMsg {
        private final int packageId;
        private final String results;
        public StoreMsg(int id, String results) {
            this.packageId = id;
            this.results = results;
        }
        public int getPackageId() {
            return packageId;
        }
        public String getResults() {
            return results;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(StoreMsg.class, )
    }

    private void store(StoreMsg msg) {

    }
}
