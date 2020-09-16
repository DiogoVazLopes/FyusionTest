package com.fidelidade.fyusion360;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.app.fyusion.BuildConfig;
import com.fyusion.sdk.common.FyuseSDKException;
import com.fyusion.sdk.ext.carmodeflow.CarSession;
import com.fyusion.sdk.ext.carmodeflow.CarSessionNavActivity;
import com.fyusion.sdk.ext.carmodeviewer.widget.CarSessionFyuseView;
import com.fyusion.sdk.common.FyuseSDK;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import static com.fyusion.sdk.ext.carmodeflow.CarSessionNavActivity.KEY_MODE_CAR_FREESTYLE_CAPTURE;
import static com.fyusion.sdk.ext.carmodeflow.CarSessionNavActivity.KEY_MODE_CAR_MANUAL;

public class Fyusion360Plugin extends CordovaPlugin {

    private static final int REQUEST_CODE = 123;
    private CallbackContext callbackContext;
    private String TAG = Fyusion360Plugin.class.getSimpleName();

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;

        switch (Actions.getActionByName(action)) {

            case INIT_FYUSE:
                initFyuse();
                break;
            case START_CAPTURE_SESSION:
                startCaptureSession();
                break;
            case SHOW_FYUSE:
                showFyuse();
                break;
            case GET_FYUSE_THUMBNAIL:
                getFyuseThumbnail();
                break;
            case GET_DETAIL_PHOTOS:
                getDetailPhotos();
                break;
            case INVALID:
                callbackContext.error(Actions.INVALID.getDescription());
                break;
        }

        return true;
    }

    private void closeApplication() {
        this.cordova.getActivity().finish();
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Log.i("SHARE", "Result OK with:");
                    Log.i("SHARE", "---- " + data.getStringExtra(CarSessionNavActivity.RESULT_EXTRA_URL));
                    Log.i("SHARE", "---- " + data.getStringExtra(CarSessionNavActivity.RESULT_EXTRA_SESSION_PATH));
                    Log.i("SHARE", "---- " + data.getBooleanExtra(CarSessionNavActivity.RESULT_EXTRA_SESSION_UPDATED, false));
                    Log.i("SHARE", "---- " + data.getIntExtra(CarSessionNavActivity.RESULT_EXTRA_TAGS, 0));
                    Log.i("SHARE", "---- " + data.getIntExtra(CarSessionNavActivity.RESULT_EXTRA_STATIC_IMAGES, 0));
                    Log.i("SHARE", "---- " + data.getStringExtra(CarSessionNavActivity.RESULT_EXTRA_SESSION_UPLOAD_ID));
                } else {
                    Log.i("SHARE", "Result OK but no data");
                }
            } else if (resultCode == CarSessionNavActivity.RESULT_ERROR) {
                Toast.makeText(this.cordova.getContext(), "There was an error. Check LOG.", Toast.LENGTH_LONG).show();
            } else if (resultCode == CarSessionNavActivity.RESULT_CAMERA_UNSUPPORTED) {
                Toast.makeText(this.cordova.getContext(), "This phone does not have a supported camera.", Toast.LENGTH_LONG).show();
            } else if (resultCode == CarSessionNavActivity.RESULT_PHONE_UNSUPPORTED) {
                Toast.makeText(this.cordova.getContext(), "This phone is not supported.", Toast.LENGTH_LONG).show();
            } else {
                Log.e("TagSessionShare", "Result: " + resultCode);
            }
        }
    }
*/
    /**
     * Start capture session
     */
    private void startCaptureSession() {

        try {
            CarSession.init(this.cordova.getContext())
                    .withTaggingFlow(true)
                    .startForResult(REQUEST_CODE);
            Log.i(TAG, "StartCaptureSession success");
            callbackContext.success("OK");
        } catch (Exception e) {
            Log.e(TAG, "message: " + e.toString());
            callbackContext.error(e.toString());
        }
    }

    private void initFyuse() {
        try {
            FyuseSDK.init(this.cordova.getActivity().getApplicationContext(), "ooljHBOWhC_PrxjVXYvmZf", "PGBeHBfhCpMTjMQYZhEOHRQDlGyZysEY");
            Log.i(TAG, "initFyuse success");
            callbackContext.success("OK");

        } catch (Exception e) {
            Log.e(TAG, "message: " + e.toString());
            callbackContext.error("Error:" e.toString());
        }
    }


    /**
     * Show Fyuse
     */
    private void showFyuse() {
        //CarSession.showFyuse();
    }
    /**
     * Get Fyuse Thumbnail
     */
    private void getFyuseThumbnail() {
        //CarSession.getPreviewThumb();
    }
    /**
     * Get Detail photos
     */
    private void getDetailPhotos() {
        //
    }
    //  This class help you get the actions called in JS
    private enum Actions {

        INIT_FYUSE("InitFyuse","Initialize Fyuse SDK."),
        START_CAPTURE_SESSION("startSession", "Open the platform to record a 360 image of the vehicle."),
        SHOW_FYUSE("showFyuse", "Open the Fyuse platform."),
        GET_FYUSE_THUMBNAIL("getFyuseThumbnail", "Fetch the thumbnail of your recording."),
        GET_DETAIL_PHOTOS("getDetailPhotos", "Get the detail of the recorded photos."),
        INVALID("", "Invalid or not found action!");

        private String action;
        private String description;

        Actions(String action, String description) {
            this.action = action;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static Actions getActionByName(String action) {
            for (Actions a : Actions.values()) {
                if (a.action.equalsIgnoreCase(action)) {
                    return a;
                }
            }
            return INVALID;
        }
    }
}