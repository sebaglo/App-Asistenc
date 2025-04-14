package com.example.myasisten;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EscaneoPistola extends AppCompatActivity {

    private EditText etEscaneo;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_pistola);

        etEscaneo = findViewById(R.id.etEscaneo);
        tvResultado = findViewById(R.id.tvResultado);

        etEscaneo.requestFocus();

        etEscaneo.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                String codigoEscaneado = etEscaneo.getText().toString().trim();
                if (!codigoEscaneado.isEmpty()) {
                    tvResultado.setText("Código escaneado:\n" + codigoEscaneado);
                    etEscaneo.setText("");
                }
                return true;
            }
            return false;
        });
    }
}
