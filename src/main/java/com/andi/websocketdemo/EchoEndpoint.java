package com.andi.websocketdemo;

import jakarta.websocket.OnOpen;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnError;
import jakarta.websocket.Session;
import jakarta.websocket.CloseReason;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/echo")
public class EchoEndpoint
{
    private static final Set<Session> openSessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        // TODO
        openSessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            session.getBasicRemote().sendText("Echo: " + message);
        } catch (IOException e) {
            System.err.println("Error while sending Echo message: session id =>" + session.getId() + e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        openSessions.remove(session);
        System.out.println("Echo closed: " + session.getId() + " " + "reason code:" + reason.getCloseCode() + reason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("Error occurred with session id: " + session.getId() + ":" + error.getMessage());
        if (session != null && !session.isOpen()) {
            openSessions.remove(session);
        }
    }
}
