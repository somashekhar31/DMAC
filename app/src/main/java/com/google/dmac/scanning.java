package com.google.dmac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scanning extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerView;
private Firebase fref,childqr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView= new ZXingScannerView(this);
        setContentView(scannerView);
        Firebase.setAndroidContext(this);
        fref= new Firebase("https://dmac-b92af.firebaseio.com/QR");
    }

    @Override
    public void handleResult(Result result) {
        //Account.resultanswer.setText(result.getText());
        childqr = fref.child("SetQr");
        childqr.push().setValue(result.getText());
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
