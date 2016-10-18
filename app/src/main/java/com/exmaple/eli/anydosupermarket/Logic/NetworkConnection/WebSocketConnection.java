package com.exmaple.eli.anydosupermarket.Logic.NetworkConnection;

import android.os.Build;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by eliyashar on 18/10/16.
 */
public class WebSocketConnection implements IConnection {

    private IOnNewData mDataListener;
    private URI mUri;
    private WebSocketClient mWebSocketClient;
    public WebSocketConnection(String serverURL, IOnNewData listener) {
        mDataListener = listener;
        try {
            mUri = new URI("ws://"+serverURL);
             mWebSocketClient = new WebSocketClient(mUri) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
                }

                @Override
                public void onMessage(String message) {
                    Log.i("Websocket", "message:" + message);
                    if(mDataListener != null) {
                        mDataListener.onData(message);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.i("Websocket", "Closed " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    Log.i("Websocket", "Error " + ex.getMessage());
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void openConnection() {
        if(mUri !=null) {
            mWebSocketClient.connect();
        }else{
            mDataListener.onConnectionFailed();
        }
    }
}
