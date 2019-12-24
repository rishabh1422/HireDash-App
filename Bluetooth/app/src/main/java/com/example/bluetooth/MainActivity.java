package com.example.bluetooth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.usage.NetworkStats;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button status, bto, btof, bt1, bt2, bt3;
    BluetoothAdapter bluetoothAdapter;
    ConnectivityManager conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = findViewById(R.id.stutus);
        bto = findViewById(R.id.bto);
        btof = findViewById(R.id.btfo);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        bluetoothAdapter = bluetoothAdapter.getDefaultAdapter();
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 3);
        //Toast.makeText(this, "Bluetooth"+bluetoothAdapter, Toast.LENGTH_SHORT).show();
        // Log.d( "onCreate: ",String.valueOf(bluetoothAdapter));
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver1, filter1);
        //IntentFilter filter2=new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        // registerReceiver(mReceiver2,filter2);

    }

    private final BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Toast.makeText(context, "Inside Receive of Receiver1 ", Toast.LENGTH_SHORT).show();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                int state = intent.getCharExtra(BluetoothAdapter.EXTRA_STATE, Character.highSurrogate(BluetoothAdapter.ERROR));
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(context, "BlueTooth Off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(context, " Turning BlueTooth Off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(context, " BlueTooth On", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(context, " Turning BlueTooth On", Toast.LENGTH_SHORT).show();
                        break;
                }

            }


        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver1);
    }

    public void NetworkStats(View view) {
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "WI FI", Toast.LENGTH_SHORT).show();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "Mobile", Toast.LENGTH_SHORT).show();
            }


        }


    }

    public void turnOnBlueTooth(View view) {
        if (!bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(i, 1);
            }
            if (bluetoothAdapter.isEnabled()) {
                Toast.makeText(this, "BlueTooth Enabled", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (requestCode == RESULT_OK) {
                Toast.makeText(this, "BlueTooth Tern On", Toast.LENGTH_SHORT).show();
            }
            if (requestCode == RESULT_CANCELED) {
                Toast.makeText(this, "Bluetooth turned on Failed", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 2) {
            if (requestCode != RESULT_CANCELED) {
                Toast.makeText(this, "Device Discoverbility start", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Divice Discoverbility Cancelled", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void turnofbluetooth(View view) {
        if (bluetoothAdapter != null) {
            bluetoothAdapter.disable();
            Toast.makeText(this, "BlueTooth Turned Off", Toast.LENGTH_SHORT).show();
        }
    }

    public void DisCoverBlueToothDivce(View view) {
        if (bluetoothAdapter == null) {
            bluetoothAdapter.startDiscovery();
            Toast.makeText(this, "StartActivity" + bluetoothAdapter, Toast.LENGTH_SHORT).show();
        }

    }
    public void Makeservice(View view)
    {

    }
    public void  getpresed(View view)
    {

    }


}
