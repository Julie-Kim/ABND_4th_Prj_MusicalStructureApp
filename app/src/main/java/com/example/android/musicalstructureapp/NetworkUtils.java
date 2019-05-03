package com.example.android.musicalstructureapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

class NetworkUtils {

    static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    static void showNetworkErrorToast(Context context) {
        Toast.makeText(context, R.string.network_error, Toast.LENGTH_SHORT).show();
    }
}