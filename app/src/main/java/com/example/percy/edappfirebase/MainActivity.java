package com.example.percy.edappfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login,register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instance de firebase
        auth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = email.getText().toString();
                String passwordE = password.getText().toString();
                if(TextUtils.isEmpty(userE)) {
                    Toast.makeText(getApplicationContext(),"coloca un correo",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(passwordE)) {
                    Toast.makeText(getApplicationContext(),"coloca una contraseña",Toast.LENGTH_SHORT).show();
                }
                auth.signInWithEmailAndPassword(userE,passwordE)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"error en el login",
                                            Toast.LENGTH_SHORT).show();
                                }else {
                                    Intent intent = new Intent(MainActivity.this,PerfilActivity.class);
                                    startActivity(intent);
                                    //close to activity
                                    finish();
                                }
                            }
                        });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
