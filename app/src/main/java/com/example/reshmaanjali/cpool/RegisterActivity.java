package com.example.reshmaanjali.cpool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.id_name_reg)
    EditText nameReg;
    @BindView(R.id.id_profsn_reg) EditText profsnReg;
    @BindView(R.id.id_clg_id_reg) EditText clgIdReg;
    @BindView(R.id.id_phn_reg) EditText pnNoReg;
    @BindView(R.id.id_adhr_reg) EditText adhrReg;
    @BindView(R.id.id_mail_reg) EditText mailReg;
    @BindView(R.id.id_pwd_reg) EditText pwdReg;
    FirebaseAuth firebaseAuth;
    String customTokenStr;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        firebaseAuth= FirebaseAuth.getInstance();
        register = (Button) findViewById(R.id.id_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=nameReg.getText().toString();
                final String profsn=profsnReg.getText().toString();
                final String clgid=clgIdReg.getText().toString();
                final String phnNo=pnNoReg.getText().toString();
                final String adhr=adhrReg.getText().toString();
                final String mail=mailReg.getText().toString();
                final String pwd=pwdReg.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            RegistrationPOJO myNewUser=new RegistrationPOJO(name,profsn,clgid,phnNo,adhr,mail,pwd);
                            //FirebaseDatabase database =
                            FirebaseDatabase.getInstance().getReference("myUsers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(myNewUser)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Registration Successfull....", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    }
                                }
                            });

                            Toast.makeText(RegisterActivity.this, "Registration success", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(RegisterActivity.this, "Sorry!! registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

   /* public void toRegister(View view) {
        final String name=nameReg.getText().toString();
        final String profsn=profsnReg.getText().toString();
        final String clgid=clgIdReg.getText().toString();
        final String phnNo=pnNoReg.getText().toString();
        final String adhr=adhrReg.getText().toString();
        final String mail=mailReg.getText().toString();
        final String pwd=pwdReg.getText().toString();
       *//* firebaseAuth.signInWithCustomToken(customTokenStr).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=firebaseAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "Registration Successfull....", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
                else
                    Toast.makeText(RegisterActivity.this, "Sorry!! registration failed", Toast.LENGTH_SHORT).show();
            }
        });*//*
        firebaseAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    RegistrationPOJO myNewUser=new RegistrationPOJO(name,profsn,clgid,phnNo,adhr,mail,pwd);
                    //FirebaseDatabase database =
                            *//*FirebaseDatabase.getInstance().getReference("myUsers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(myNewUser)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Registration Successfull....", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    }
                                }
                            });*//*
                    Toast.makeText(RegisterActivity.this, "Registration success", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(RegisterActivity.this, "Sorry!! registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
    /*private void setCustomTokenStr(String tokenStr){
        customTokenStr=tokenStr;
        String status;
        if(tokenStr!=null){
            status="token = "+customTokenStr;
        }
        else{
            status="token = null";
        }
    }*/
}
