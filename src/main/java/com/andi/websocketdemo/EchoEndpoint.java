package com.andi.websocketdemo;

import jakarta.websocket.OnOpen;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnError;
import jakarta.websocket.Session;
import jakarta.websocket.CloseReason;
import jakarta.websocket.server.ServerEndpoint;

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
        // TODO
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        openSessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        // TODO
    }
}
