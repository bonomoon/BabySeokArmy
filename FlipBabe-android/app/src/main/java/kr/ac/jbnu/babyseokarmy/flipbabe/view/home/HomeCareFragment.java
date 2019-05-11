package kr.ac.jbnu.babyseokarmy.flipbabe.view.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import kr.ac.jbnu.babyseokarmy.flipbabe.R;
import kr.ac.jbnu.babyseokarmy.flipbabe.service.BluetoothChatService;
import kr.ac.jbnu.babyseokarmy.flipbabe.service.BluetoothService;
import kr.ac.jbnu.babyseokarmy.flipbabe.view.DeviceListActivity;
import kr.ac.jbnu.babyseokarmy.flipbabe.view.base.BaseFragment;

public class HomeCareFragment extends BaseFragment {
    // Debugging
    private static final String TAG = "BluetoothChat";
    private static final boolean D = true;

    // Message types sent from the BluetoothChatService Handler
    private static final int MESSAGE_STATE_CHANGE = 1;
    private static final int MESSAGE_READ = 2;
    private static final int MESSAGE_DEVICE_NAME = 3;
    private static final int MESSAGE_TOAST = 4;

    // Key names received from the BluetoothChatService Handler
    private static final String DEVICE_NAME = "device_name";
    private static final String TOAST = "toast";

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    private BluetoothService mBlueService = null;

    // Name of the connected device
    private String mConnectedDeviceName = null;

    public HomeCareFragment() {
        super(R.layout.fragment_home_care);
    }

    static HomeCareFragment newInstance() {
        HomeCareFragment fragment = new HomeCareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this.getContext(), "Bluetooth is not available", Toast.LENGTH_LONG).show();
            Objects.requireNonNull(this.getActivity()).finish();
        }
    }

    @Override
    public void onCreateView(LayoutInflater inflate, View v) {
        Button careConnBtn = v.findViewById(R.id.care_conn_btn);
        careConnBtn.setOnClickListener(view -> {
            // 블루투스 연결안됐을때 연결하게 한다.
            // setupChat() will then be called during onActivityResult
            if (!mBluetoothAdapter.isEnabled()) {
                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT);
                // Otherwise, setup the chat session
            } else {
                if (mBlueService == null) setup();
            }
        });
    }

    private void setup() {
        Log.d(TAG, "setup()");
        // Initialize the BluetoothChatService to perform bluetooth connections
        mBlueService = new BluetoothService(this.getContext(), mHandler);
    }

//           if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
//        Toast.makeText(this.getContext(), R.string.not_connected, Toast.LENGTH_SHORT).show();
//        return;
//    }

    // The Handler that gets information back from the BluetoothService
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);

                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            Toast.makeText(getContext(), R.string.title_connected_to, Toast.LENGTH_SHORT).show();
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            Toast.makeText(getContext(), R.string.title_connecting, Toast.LENGTH_SHORT).show();
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            Toast.makeText(getContext(), R.string.title_not_connected, Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    if(readMessage == "F") {

                    }
                    if(readMessage == "R") {

                    }
                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    Toast.makeText(getContext(), "Connected to "
                            + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(getContext(), msg.getData().getString(TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Get the device MAC address
                    String address = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    // Get the BluetoothDevice object
                    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                    // Attempt to connect to the device
                    mBlueService.connect(device);
                }
                break;
            case REQUEST_ENABLE_BT:                     //블루투스 온 상태 반환받음
                if (resultCode == Activity.RESULT_OK) { //블루투스 킴
                    setup();
                } else {
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this.getContext(), R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    Objects.requireNonNull(getActivity()).finish();
                }
        }
    }
}
