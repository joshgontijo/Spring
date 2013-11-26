/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.josue.devtech.mavenproject1;

/**
 *
 * @author Josue
 */
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws")
public class MyEndpoint {

    @OnMessage
    public String send(String message) {
        return "Server return: "+message;
    }

    @OnOpen
    public void openConnection(Session session) {
        System.out.println("ON-OPEN");
    }

    @OnClose
    public void closedConnection(Session session) {
        System.out.println("ON-CLOSE");
    }

    @OnError
    public void error(Session session, Throwable t) {
        System.out.println("ON-ERROR");
    }

}
