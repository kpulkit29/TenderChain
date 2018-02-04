package com.hackrupt.yolo;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

public class RegisterTender extends AppCompatActivity {
    TextView textFile;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tender);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register Tender");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Button browse=(Button)findViewById(R.id.tender_proposal_select);
        textFile=(TextView)findViewById(R.id.tender_proposal_path);
        mAuth = FirebaseAuth.getInstance();
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent,"ChooseFile"), 0);
            }
        });
        TextView next=(TextView)findViewById(R.id.tender_register);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView password=(TextView)findViewById(R.id.tender_password);
                TextView email=(TextView)findViewById(R.id.tender_email);
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(RegisterTender.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterTender.this,"Authentication Success.", Toast.LENGTH_SHORT).show();
                                    mAuth.getInstance();
                                }
                                else {
                                    Toast.makeText(RegisterTender.this,"Authentication failed.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });

                Uri file = Uri.fromFile(new File(textFile.getText().toString()));
                StorageReference riversRef = mStorageRef.child(email.getText().toString());

                riversRef.putFile(file)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccess
                            }
                        });
                TextView name=(TextView)findViewById(R.id.tender_first_name);
                TextView company=(TextView)findViewById(R.id.tender_company);
                TextView exp=(TextView)findViewById(R.id.tender_experience);
                TextView proj=(TextView)findViewById(R.id.tender_projects);
                TextView price=(TextView)findViewById(R.id.tender_price);
                TenderReg tenderReg= new TenderReg(name.getText().toString(),email.getText().toString(),company.getText().toString(),exp.getText().toString(),proj.getText().toString(),price.getText().toString());
                mStorageRef = FirebaseStorage.getInstance().getReference();
                mDatabase.child("users").push().setValue(tenderReg);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet("http://12a8e183.ngrok.io/company?name="+"ksfjk"/+"&ar=6266"+"&dur=4&gr=3236");
                try {
                    HttpResponse response = client.execute(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i=new Intent(getApplicationContext(),FirstPage.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 0:
                if(resultCode==RESULT_OK){
                    String FilePath = data.getData().getPath();
                    textFile.setText(FilePath);
                }
                break;

        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
