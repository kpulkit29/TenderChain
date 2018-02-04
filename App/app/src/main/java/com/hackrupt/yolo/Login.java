package com.hackrupt.yolo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        TextView next=(TextView)findViewById(R.id.login);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView password=(TextView)findViewById(R.id.login_pass);
                TextView email=(TextView)findViewById(R.id.login_email);
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Authentication Success.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(getApplicationContext(),FirstPage.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
