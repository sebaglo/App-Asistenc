package com.example.myasisten;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnregistro;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etEmail = findViewById(R.id.etNuevoUsuario);
        etPassword = findViewById(R.id.etNuevaContraseña);
        btnregistro = findViewById(R.id.btnRegistrarUsuario);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Registro.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener(authResult -> {
                            String uid = authResult.getUser().getUid();

                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("email", email);

                            userMap = new HashMap<>();
                            userMap.put("password", password);

                            firestore.collection("usuarios").document(uid).set(userMap)
                                    .addOnSuccessListener(unused ->
                                            Toast.makeText(Registro.this, "Usuario registrado", Toast.LENGTH_SHORT).show())
                                    .addOnFailureListener(e ->
                                            Toast.makeText(Registro.this, "Error guardando usuario", Toast.LENGTH_SHORT).show());
                        })
                        .addOnFailureListener(e ->
                                Toast.makeText(Registro.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }
}
