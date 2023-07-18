package com.example.lsnavigation;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import com.felhr.usbserial.UsbSerialDevice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SerialService extends Service {

    private final String TAG = "SerialService";
    public static final String ACTION = "SerialServiceAction";
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    public static UsbSerialDevice serial;
    public static Context context;

    // ---ActivityからServiceの受信部、音声認識の結果をserial.writeする
    Messenger messenger;
    static final int DISTANCE_0 = 0;    // 0.5km以下
    static final int DISTANCE_1 = 1;    // 0.5Km
    static final int DISTANCE_2 = 2;    // 1.0km
    static final int DISTANCE_3 = 3;    // 1.5km
    static final int DISTANCE_4 = 4;    // 2.0km
    static final int DISTANCE_5 = 5;    // 2.5km
    static final int DISTANCE_6 = 6;    // 3.0Km
    static final int DISTANCE_7 = 7;    // 3.5km
    static final int DISTANCE_8 = 8;    // 4.0km
    static final int DISTANCE_9 = 9;    // 4.5km
    static final int DISTANCE_10 = 10;  // 5.0km
    static final int DISTANCE_11 = 11;  // 5.5Km
    static final int DISTANCE_12 = 12;  // 6.0km
    static final int DISTANCE_13 = 13;  // 6.5km
    static final int DISTANCE_14 = 14;  // 7.0km
    static final int DISTANCE_15 = 15;  // 7.5km
    static final int DISTANCE_16 = 16;  // 8.0km
    static final int DISTANCE_17 = 17;  // 8.5Km
    static final int DISTANCE_18 = 18;  // 9.0km
    static final int DISTANCE_19 = 19;  // 9.5km
    static final int DISTANCE_20 = 20;  // 10.0km
    static final int DISTANCE_21 = 21;  // 10.5km
    static final int DISTANCE_22 = 22;  // 11.0km
    static final int DISTANCE_23 = 23;  // 11.5Km
    static final int DISTANCE_24 = 24;  // 12.0km
    static final int DISTANCE_25 = 25;  // 12.5km
    static final int DISTANCE_26 = 26;  // 13.0km
    static final int DISTANCE_27 = 27;  // 13.5km
    static final int DISTANCE_28 = 28;  // 14.0km
    static final int DISTANCE_29 = 29;  // 14.5Km
    static final int DISTANCE_30 = 30;  // 15.0km
    static final int DISTANCE_31 = 31;  // 15.5km
    static final int DISTANCE_32 = 32;  // 16.0km
    static final int DISTANCE_33 = 33;  // 16.5km
    static final int DISTANCE_34 = 34;  // 17.0km
    static final int DISTANCE_35 = 35;  // 17.5km
    static final int DISTANCE_36 = 36;  // 18.0km

    static final int HEIGHT_1 = 41;     // 2m<
    static final int HEIGHT_2 = 42;     // 2m<<3m
    static final int HEIGHT_3 = 43;     // 3m<<4m
    static final int HEIGHT_4 = 44;     // 4m<<5m
    static final int HEIGHT_5 = 45;     // 5m>>

    static final int GROUND_SPEED_1 = 51;
    static final int GROUND_SPEED_2 = 52;
    static final int GROUND_SPEED_3 = 53;
    static final int GROUND_SPEED_4 = 54;
    static final int GROUND_SPEED_5 = 55;
    static final int GROUND_SPEED_6 = 56;
    static final int OTHER_1 = 100;
    

    static class VoiceHandler extends Handler{

        public VoiceHandler(Context cont){
            context = cont.getApplicationContext();
        }

        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case DISTANCE_0:
                    serial.syncWrite("distance_0.wav".getBytes(), 1000);
                    break;
                case DISTANCE_1:
                    serial.syncWrite("distance_1.wav".getBytes(), 1000);
                    break;
                case DISTANCE_2:
                    serial.syncWrite("distance_2.wav".getBytes(), 1000);
                    break;
                case DISTANCE_3:
                    serial.syncWrite("distance_3.wav".getBytes(), 1000);
                    break;
                case DISTANCE_4:
                    serial.syncWrite("distance_4.wav".getBytes(), 1000);
                    break;
                case DISTANCE_5:
                    serial.syncWrite("distance_5.wav".getBytes(), 1000);
                    break;
                case DISTANCE_6:
                    serial.syncWrite("distance_6.wav".getBytes(), 1000);
                    break;
                case DISTANCE_7:
                    serial.syncWrite("distance_7.wav".getBytes(), 1000);
                    break;
                case DISTANCE_8:
                    serial.syncWrite("distance_8.wav".getBytes(), 1000);
                    break;
                case DISTANCE_9:
                    serial.syncWrite("distance_9.wav".getBytes(), 1000);
                    break;
                case DISTANCE_10:
                    serial.syncWrite("distance_10.wav".getBytes(), 1000);
                    break;
                case DISTANCE_11:
                    serial.syncWrite("distance_11.wav".getBytes(), 1000);
                    break;
                case DISTANCE_12:
                    serial.syncWrite("distance_12.wav".getBytes(), 1000);
                    break;
                case DISTANCE_13:
                    serial.syncWrite("distance_13.wav".getBytes(), 1000);
                    break;
                case DISTANCE_14:
                    serial.syncWrite("distance_14.wav".getBytes(), 1000);
                    break;
                case DISTANCE_15:
                    serial.syncWrite("distance_15.wav".getBytes(), 1000);
                    break;
                case DISTANCE_16:
                    serial.syncWrite("distance_16.wav".getBytes(), 1000);
                    break;
                case DISTANCE_17:
                    serial.syncWrite("distance_17.wav".getBytes(), 1000);
                    break;
                case DISTANCE_18:
                    serial.syncWrite("distance_18.wav".getBytes(), 1000);
                    break;
                case DISTANCE_19:
                    serial.syncWrite("distance_19.wav".getBytes(), 1000);
                    break;
                case DISTANCE_20:
                    serial.syncWrite("distance_20.wav".getBytes(), 1000);
                    break;
                case DISTANCE_21:
                    serial.syncWrite("distance_21.wav".getBytes(), 1000);
                    break;
                case DISTANCE_22:
                    serial.syncWrite("distance_22.wav".getBytes(), 1000);
                    break;
                case DISTANCE_23:
                    serial.syncWrite("distance_23.wav".getBytes(), 1000);
                    break;
                case DISTANCE_24:
                    serial.syncWrite("distance_24.wav".getBytes(), 1000);
                    break;
                case DISTANCE_25:
                    serial.syncWrite("distance_25.wav".getBytes(), 1000);
                    break;
                case DISTANCE_26:
                    serial.syncWrite("distance_26.wav".getBytes(), 1000);
                    break;
                case DISTANCE_27:
                    serial.syncWrite("distance_27.wav".getBytes(), 1000);
                    break;
                case DISTANCE_28:
                    serial.syncWrite("distance_28.wav".getBytes(), 1000);
                    break;
                case DISTANCE_29:
                    serial.syncWrite("distance_29.wav".getBytes(), 1000);
                    break;
                case DISTANCE_30:
                    serial.syncWrite("distance_30.wav".getBytes(), 1000);
                    break;
                case DISTANCE_31:
                    serial.syncWrite("distance_31.wav".getBytes(), 1000);
                    break;
                case DISTANCE_32:
                    serial.syncWrite("distance_32.wav".getBytes(), 1000);
                    break;
                case DISTANCE_33:
                    serial.syncWrite("distance_33.wav".getBytes(), 1000);
                    break;
                case DISTANCE_34:
                    serial.syncWrite("distance_34.wav".getBytes(), 1000);
                    break;
                case DISTANCE_35:
                    serial.syncWrite("distance_35.wav".getBytes(), 1000);
                    break;

                case HEIGHT_1:
                    serial.syncWrite("height_1.wav".getBytes(), 1000);
                    break;
                case HEIGHT_2:
                    serial.syncWrite("height_2.wav".getBytes(), 1000);
                    break;
                case HEIGHT_3:
                    serial.syncWrite("height_3.wav".getBytes(), 1000);
                    break;
                case HEIGHT_4:
                    serial.syncWrite("height_4.wav".getBytes(), 1000);
                    break;
                case HEIGHT_5:
                    serial.syncWrite("height_5.wav".getBytes(), 1000);
                    break;

                case GROUND_SPEED_1:
                    serial.syncWrite("groundSpeed_1.wav".getBytes(), 1000);
                    break;
                case GROUND_SPEED_2:
                    serial.syncWrite("groundSpeed_2.wav".getBytes(), 1000);
                    break;
                case GROUND_SPEED_3:
                    serial.syncWrite("groundSpeed_3.wav".getBytes(), 1000);
                    break;
                case GROUND_SPEED_4:
                    serial.syncWrite("groundSpeed_4.wav".getBytes(), 1000);
                    break;
                case GROUND_SPEED_5:
                    serial.syncWrite("groundSpeed_5.wav".getBytes(), 1000);
                    break;
                case GROUND_SPEED_6:
                    serial.syncWrite("groundSpeed_6.wav".getBytes(), 1000);
                    break;
                case OTHER_1:
                    serial.syncWrite("other_1.wav".getBytes(), 1000);
                    break;
            }
        }
    }

    /* サービスバインド時実行
     * サービスとアクティビティ間で通信するときに利用するメソッド
     */
    @Override
    public IBinder onBind(Intent intent) {
        messenger = new Messenger(new VoiceHandler(this));
        return messenger.getBinder();
    }


    /**
     * USBシリアル通信の初期化を行う
     * @return 成功した場合はUsbSerialDevice、失敗した場合はnull
     */
    public UsbSerialDevice initializeUsbSerial() {
        UsbManager usbManager = (UsbManager) getSystemService(context.USB_SERVICE);

        // USBデバイス一覧を取得して、一覧からUSBシリアルデバイスを探す
        UsbDevice usbDevice = null;
        for (UsbDevice d : usbManager.getDeviceList().values()) {
            // 関係ないデバイスに誤爆することがあるので、Raspberry Pi Picoが見つかるようにVendorIdもチェックする
            if (UsbSerialDevice.isSupported(d) && UsbSerialDevice.isCdcDevice(d) && d.getVendorId() == 0x2E8A) {
                usbDevice = d;
            }
        }
        if (usbDevice == null) {return null;}

        // 権限チェック
        if (!usbManager.hasPermission(usbDevice)) {
            // ユーザからアクセス許可をもらっていなかったら、許可を求めたうえで初期化は失敗扱いにしてnullを返す。
            // 一度ユーザが許可をすれば、次回以降のアプリ起動時に接続ができるようになる。
            // Flag->PendingIntent.FLAG_MUTABLE or FLAG.IMMUTABLE を設定しなければいけない
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_MUTABLE);
            usbManager.requestPermission(usbDevice, pendingIntent);
            return null;
        }

        // 権限があるのでデバイスに接続する
        UsbDeviceConnection usbDeviceConnection = usbManager.openDevice(usbDevice);
        return UsbSerialDevice.createUsbSerialDevice(usbDevice, usbDeviceConnection);
    }


    public SerialService() {
    }

    // サービス初回起動時に実行
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        serial = initializeUsbSerial();
        if (serial == null) {
            // USBシリアルの初期化に失敗
            Toast.makeText(this, "connection failed", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "connection failed");
        }else{
            Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
            // Picoとの接続を確認し、Picoにmsgを送る
            serial.syncOpen();
            serial.syncWrite("connect".getBytes(), 1000);
        }
    }


// サービスの起動都度に実行

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i(TAG, "onStartCommand");
        new ReadThread().start();
        /*
         * onStartCommandの戻り値は、サービスがシステムによって強制終了されたときにどのように振る舞うかを表す。
         * ------------------------
         * START_NOT_STICKY             サービスを起動しない
         * START_STICKY                 サービスを再起動
         * START_REDELIVER_INTENT       終了前と同じインテントを使って再起動する
         * START_STICKY_COMPATIBILITY   再起動は保証されない(START_STICKYとの互換)
         */
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        serial.close();
    }

    private class ReadThread extends Thread {
        @Override
        public void run() {
            while(true){
                byte[] buffer = new byte[100];
                int n = serial.syncRead(buffer, 0);
                if(n > 0) {
                    byte[] received = new byte[n];
                    System.arraycopy(buffer, 0, received, 0, n);
                    String receivedStr = new String(received);

                    Intent intent2 = new Intent(ACTION);
                    intent2.putExtra("message", receivedStr);
                    // ブロードキャストの送信、受信側はレシーバーconnectを作成しなければいけない-> BroadcastReceiver
                    sendBroadcast(intent2);
                }
            }
        }
    }
}