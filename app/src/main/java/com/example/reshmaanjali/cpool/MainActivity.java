package com.example.reshmaanjali.cpool;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void creation(View view) {
        if (isOnline()) {
            //do whatever you want to do
            startActivity(new Intent(this,LoginActivity.class));
        } else {
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.show();
            } catch (Exception e) {
                Log.d("ji", "Show Dialog: " + e.getMessage());
            }
        }
    }

    public void searching(View view) {

        if (isOnline()) {
            //do whatever you want to do
            startActivity(new Intent(this,Searching.class));
        } else {
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.show();
            } catch (Exception e) {
                Log.d("ji", "Show Dialog: " + e.getMessage());
            }
        }
    }


    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
