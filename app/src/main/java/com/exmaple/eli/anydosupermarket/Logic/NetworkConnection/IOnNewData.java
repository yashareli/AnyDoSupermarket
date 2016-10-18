package com.exmaple.eli.anydosupermarket.Logic.NetworkConnection;

/**
 * Created by eliyashar on 18/10/16.
 */
public interface IOnNewData {
    void onData(String data);
    void onConnectionFailed();
}
