package com.example.myasisten;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class alumno extends AppCompatActivity {

    private TextView tvScanResult;

    private final androidx.activity.result.ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(
            new ScanContract(), result -> {
                if (result.getContents() != null) {
                    String scannedData = result.getContents();
                    Log.d("ScannedData", "Scanned data: " + scannedData);

                    String[] extractedData = extractNameAndRut(scannedData);
                    String name = extractedData[0];
                    String rut = extractedData[1];

                    Log.d("ExtractedData", "Name: " + name + ", RUT: " + rut);

                    tvScanResult.setText("Nombre: " + name + "\nRUT: " + (rut.isEmpty() ? "No válido" : rut));

                    Intent intent = new Intent(alumno.this, Asistencia.class);
                    intent.putExtra("name", name);
                    intent.putExtra("rut", rut);
                    startActivity(intent);
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alumno);

        tvScanResult = findViewById(R.id.tvScanResult);
        Button btnScan = findViewById(R.id.btnScan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRCodeScanner();
            }
        });
    }

    private void startQRCodeScanner() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Escanea un código");
        options.setBeepEnabled(true);
        options.setBarcodeImageEnabled(true);
        options.setOrientationLocked(false);
        barcodeLauncher.launch(options);
    }

    private String[] extractNameAndRut(String scannedData) {
        Pattern pattern = Pattern.compile("([a-zA-Z ]+)(\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9Kk])");
        Matcher matcher = pattern.matcher(scannedData);

        String name = "";
        String rut = "";

        if (matcher.find()) {
            name = matcher.group(1).trim();
            rut = matcher.group(2);
        }

        return new String[]{name, rut};
    }
}
