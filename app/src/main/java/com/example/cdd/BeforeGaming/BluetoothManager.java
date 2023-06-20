package com.example.cdd.BeforeGaming;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BluetoothManager {
    private BluetoothAdapter bluetoothAdapter;//蓝牙适配器
    private Context context;//上下文对象
    private static final int REQUEST_ENABLE_BLUETOOTH=1;//请求启用蓝牙的请求码
    private static final int REQUEST_ACCESS_COARSE_LOCATION = 2;//请求启用位置的请求码

    public BluetoothManager(Context context){
        this.context=context;
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
    }

    //检查设备是否支持蓝牙
    public boolean isBluetoothSupported(){
        return bluetoothAdapter!=null;
    }

    //检查蓝牙是否已启用
    public boolean isBluetoothEnabled(){
        return bluetoothAdapter.isEnabled();
    }

    //请求用户启用蓝牙
    public void enableBluetooth(Activity activity){
        //如果用户没有启动蓝牙，则发起蓝牙启动请求
        if(!isBluetoothEnabled()){
            //检查是否具有蓝牙权限
            if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED){
                Intent enableBluetoothIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBluetoothIntent,REQUEST_ENABLE_BLUETOOTH);
            }else{
                //请求蓝牙权限
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_ENABLE_BLUETOOTH);
            }
        }
    }

    //搜索蓝牙设备
    @SuppressLint("MissingPermission")
    public void startDeviceDiscovery(Activity activity) {
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }
        bluetoothAdapter.startDiscovery();
    }


    //注册蓝牙设备搜索广播接收器
    public void registerDiscoveryReceiver(BroadcastReceiver receiver) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(receiver, filter);
    }

    //注销蓝牙设备搜索广播接收器
    public void unregisterDiscoveryReceiver(BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
    }
}
