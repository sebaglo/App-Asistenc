package com.example.myasisten;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class EscaneoPistola extends AppCompatActivity {
    private TextView txtEscaneo;

    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.acrivity_pistola);

        txtEscaneo = findViewById(R.id.txtEscaneo);
    }
}
