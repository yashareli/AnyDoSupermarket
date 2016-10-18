package com.exmaple.eli.anydosupermarket.Logic;

import android.content.Context;

import com.exmaple.eli.anydosupermarket.Logic.NetworkConnection.ConnectionFactory;
import com.exmaple.eli.anydosupermarket.Logic.NetworkConnection.ConnectionFactory.*;
import com.exmaple.eli.anydosupermarket.Logic.NetworkConnection.IConnection;
import com.exmaple.eli.anydosupermarket.Logic.NetworkConnection.IOnNewData;
import com.exmaple.eli.anydosupermarket.R;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by eliyashar on 18/10/16.
 */
public class ModelManager  implements IOnNewData{

    private static ModelManager mInstance = null;
    private IConnection mConnection;
    private ArrayList<IOnNewProductListener> mListeners;
    private boolean mIsConnected;

    private ModelManager(Context context){
        mListeners = new ArrayList<>();
        mIsConnected = false;
        mConnection = ConnectionFactory.createConnection(eConnectionsTypes.WebSocket, context.getString(R.string.connection_url),this);
    }

    public static ModelManager getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new ModelManager(context);
        }
        return mInstance;
    }

    public void observe(IOnNewProductListener listener) {
        synchronized (this){
            mListeners.add(listener);
            if(mIsConnected == false) {
                mIsConnected =true;
                mConnection.openConnection();
            }
        }
    }
    public void unObserve(IOnNewProductListener listener) {
        synchronized (this){
            mListeners.remove(listener);
        }
    }
    private void notifyObservers(ProductData product) {
        synchronized (this) {
            for(IOnNewProductListener listener : mListeners) {
                listener.onNewProduct(product);
            }
        }
    }
    @Override
    public void onData(String data) {
        try {
            ProductData product = new Gson().fromJson(data, ProductData.class);
            notifyObservers(product);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onConnectionFailed() {

    }
}
