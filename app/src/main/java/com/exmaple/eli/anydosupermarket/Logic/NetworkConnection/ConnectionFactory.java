package com.exmaple.eli.anydosupermarket.Logic.NetworkConnection;

import java.net.URI;

/**
 * Created by eliyashar on 18/10/16.
 */
public class ConnectionFactory {
    public enum eConnectionsTypes{
        WebSocket
    }

    public static IConnection createConnection(eConnectionsTypes type, String url, IOnNewData dataListener){
        switch (type) {
            case WebSocket:
                return new WebSocketConnection(url, dataListener);
        }
        return null;
    }
}
