package com.erised.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.erised.R;

/**
 * Created by neosoft on 14/9/15.
 */

public class VerifyLocation {

    private Context context;

    public VerifyLocation() {

    }

    public VerifyLocation(Context context) {
        this.context = context;
    }

    public void checkLocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
                gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {

        }

        try {

            if(confirmAirplaneModeOff(context) && confirmWiFiAvailable(context)){
//                returns true even if wifi is on and airplane mode is off. So only if they are on , the
//                default code is run
                network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }

        } catch (Exception ex) {
        }

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle(context.getResources().getString(R.string.gps_network_not_enabled));
            dialog.setMessage(context.getResources().getString(R.string.open_location_settings));
            dialog.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                    //get gps
                }
            });
//            dialog.setNegativeButton(context.getString(R.string.Cancel), new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                    // TODO Auto-generated method stub
//
//                }
//            });
            dialog.show();
        }

    }

    // Check Wi-Fi is on
    boolean confirmWiFiAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo.isAvailable();
    }

    // Check Airplane Mode - we want airplane mode off
    boolean confirmAirplaneModeOff(Context context) {
        int airplaneSetting =
                Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) ;
        return airplaneSetting == 0;
    }
}
