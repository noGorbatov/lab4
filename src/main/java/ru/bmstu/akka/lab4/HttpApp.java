package ru.bmstu.akka.lab4;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;

public class HttpApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("HttpSystem");
        Http http = Http.get(system);
    }
}
