package ru.bmstu.akka.lab4;

import akka.actor.ActorSystem;
import akka.actor.Props;

public class HttpServer {
    ActorSystem system;

    public HttpServer(ActorSystem system) {
        this.system = system;
//        system.actorOf(Props.create())
    }
}
