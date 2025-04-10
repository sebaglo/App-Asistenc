package com.example.myasisten;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sesion extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnSesion, btnRegistro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etContraseña);
        btnSesion = findViewById(R.id.btnIniciosesion);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    etEmail.setError("Ingrese su correo electrónico");
                    return;
                }

                if (password.length() < 6) {
                    etPassword.setError("La contraseña debe tener al menos 6 caracteres");
                    return;
                }

                iniciarSesion(email, password);
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sesion.this, Registro.class));
            }
        });
    }

    private void iniciarSesion(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(sesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(sesion.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(sesion.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
