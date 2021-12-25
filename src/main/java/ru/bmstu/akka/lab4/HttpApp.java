package ru.bmstu.akka.lab4;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.concurrent.CompletionStage;

public class HttpApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("HttpSystem");
        Http http = Http.get(system);
        ActorMaterializer materializer = ActorMaterializer.create(system);
        HttpServer server = new HttpServer(system);
        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                server.getRoute().flow(system, materializer);

        CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );

        System.in.read();
        binding.
                thenCompose(ServerBinding)

    }
}
