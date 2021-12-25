package ru.bmstu.akka.lab4;

import akka.actor.AbstractActor;

import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private final Map<Integer, String> storage = new HashMap<>();

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

    public static class GetMsg {
        private final int packageId;
        public GetMsg(int id) {
            packageId = id;
        }
        public int getPackageId() {
            return packageId;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(StoreMsg.class, this::store).
                match(GetMsg.class, this::getTest).
                build();
    }

    private void store(StoreMsg msg) {
        storage.put(msg.getPackageId(), msg.getResults());
    }

    private void getTest(GetMsg m) {
        getSender().tell(new StoreMsg);
    }
}
