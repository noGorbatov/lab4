package ru.bmstu.akka.lab4;

import akka.actor.AbstractActor;

import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private final Map<Integer, String> storage = new HashMap<>();
    private final static String INIT_TEST_RES_MSG = "Tests for package ";
    private final static String TEST_SEP = "\n";

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
        int id = msg.getPackageId();
        if (storage.containsKey(id)) {
            storage.put(id, storage.get(id) + TEST_SEP + msg.getResults());
        } else {
            storage.put(id, INIT_TEST_RES_MSG + id + TEST_SEP + msg.getResults());
        }
    }

    private void getTest(GetMsg m) {
        int key = m.getPackageId();
        String results = storage.get(key);
        getSender().tell(new StoreMsg(key, results), getSelf());
    }
}
