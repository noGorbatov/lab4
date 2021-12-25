package ru.bmstu.akka.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.routing.RoundRobinPool;

public class HttpServer extends AllDirectives {
    ActorSystem system;
    ActorRef storageActor;
    ActorRef workerPool;
    final static private int WORKERS_NUM = 5;
    public HttpServer(ActorSystem system) {
        this.system = system;
        storageActor = system.actorOf(Props.create(StorageActor.class));
        workerPool = system.actorOf(new RoundRobinPool(WORKERS_NUM)
                .props(Props.create(TestPerformerActor.class)));
    }
    public Route getRoute() {
        return concat(
                post( () -> entity(Jackson.unmarshaller(TestPackage.class), testPackage ->  {
                    int id = testPackage.getId();
                    String script = testPackage.getJsScript();
                    for (TestData test: testPackage.getTests()) {
                        TestPerformerActor.RunTestMsg testMsg =
                                new TestPerformerActor.RunTestMsg(id, test, )
                        workerPool.tell();
                    }
                    return complete("ok");
                })),
                get( () -> {
                    return complete("get ok");
                })
        );
    }
}
