package com.tdm1.esi.kachfilm.APIService

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by mac on 22/06/2018.
 */
class CheckConnection
{
    companion object {
        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            return  activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}