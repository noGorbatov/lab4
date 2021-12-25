package ru.bmstu.akka.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;

import java.util.concurrent.CompletionStage;

public class HttpServer extends AllDirectives {
    ActorSystem system;
    ActorRef storageActor;
    ActorRef workerPool;
    final static private int WORKERS_NUM = 5;
    final static private String PACKAGE_ID_PARAM = "packageId";
    final static private int TIMEOUT_MS = 5000;

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
                    String funcName = testPackage.getFuncName();

                    for (TestData test: testPackage.getTests()) {
                        TestPerformerActor.RunTestMsg testMsg =
                                new TestPerformerActor.RunTestMsg(id, test, script, funcName, storageActor);
                        workerPool.tell(testMsg, ActorRef.noSender());
                    }
                    return complete("ok");
                })),
                get( () -> parameter( PACKAGE_ID_PARAM, packageId -> {
                    CompletionStage<Object> answer = PatternsCS.ask(storageActor, new StorageActor.GetMsg(Integer.parseInt(packageId)), TIMEOUT_MS);
                    
                    return completeOKWithFuture();
                }))
        );
    }
}
