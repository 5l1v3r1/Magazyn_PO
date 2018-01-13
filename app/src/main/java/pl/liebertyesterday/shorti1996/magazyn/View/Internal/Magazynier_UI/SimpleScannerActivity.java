package pl.liebertyesterday.shorti1996.magazyn.View.Internal.Magazynier_UI;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import com.tedpark.tedpermission.rx2.TedRx2Permission;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    public static final String TAG = SimpleScannerActivity.class.getSimpleName();

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        TedRx2Permission.with(this)
//                .setRationaleTitle("Title")
//                .setRationaleMessage("Message") // "we need permission for read contact and find your location"
                .setPermissions(Manifest.permission.CAMERA)
                .request()
                .subscribe(tedPermissionResult -> {
                    if (!tedPermissionResult.isGranted()) {
                        Toast.makeText(this,
                                "Camera permission denied. Can't use scanner.", Toast.LENGTH_SHORT)
                                .show();
                    }
                }, throwable -> {
                }, () -> {
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("A", "A");
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}