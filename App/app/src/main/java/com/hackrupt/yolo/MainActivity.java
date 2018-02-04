package com.hackrupt.yolo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView login;
    TextView visitor;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Tender Chain");
        login=(TextView)findViewById(R.id.login);
        visitor=(TextView)findViewById((R.id.register_supplier))
    }
    public void regTend(View v) {
        Intent i=new Intent(this,RegisterTender.class);
        startActivity(i);
    }
    public void regSup(View v) {
        Intent i=new Intent(this,RegisterSupplier.class);
        startActivity(i);
    }
    public void login(View v) {
        Intent i=new Intent(this,Login.class);
        startActivity(i);
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    public void visitor(View v) {
        Intent i=new Intent(this,FirstPage.class);
        startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            login.setVisibility(View.GONE);


        }
    }
}
