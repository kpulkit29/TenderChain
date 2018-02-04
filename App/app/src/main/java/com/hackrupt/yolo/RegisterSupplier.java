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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterSupplier extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_supplier);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register Supplier");
        mAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        TextView next=(TextView)findViewById(R.id.supplier_register);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView password=(TextView)findViewById(R.id.supplier_pass);
                TextView email=(TextView)findViewById(R.id.supplier_email);
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(RegisterSupplier.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterSupplier.this,"Authentication Success.", Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent(getApplicationCont,MainActivity.class);
//                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(RegisterSupplier.this,"Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                TextView name=(TextView)findViewById(R.id.supplier_name);
                TextView company=(TextView)findViewById(R.id.supplier_company);
                TextView prod=(TextView)findViewById(R.id.supplier_product);
                TextView price=(TextView)findViewById(R.id.supplier_price);
                TextView add=(TextView)findViewById(R.id.supplier_address);
                SupplierReg supplierReg= new SupplierReg(name.getText().toString(),email.getText().toString(),company.getText().toString(),prod.getText().toString(),price.getText().toString(),add.getText().toString());
                mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).setValue(supplierReg);
                Intent i=new Intent(getApplicationContext(),FirstPage.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
