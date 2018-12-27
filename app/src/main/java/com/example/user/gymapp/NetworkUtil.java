package com.example.user.gymapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static boolean isNetWorkAvailable(Context context){
        ConnectivityManager manager=(ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if(manager==null){
            return false;
        }

        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        if(networkInfo==null||!networkInfo.isAvailable()){
            return false;
        }

        return true;
    }
}
