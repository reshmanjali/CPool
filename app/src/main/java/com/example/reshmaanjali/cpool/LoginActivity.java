package com.example.reshmaanjali.cpool;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

       private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView =  findViewById(R.id.email);
        mPasswordView =findViewById(R.id.password);
       myFirebaseAuth=FirebaseAuth.getInstance();
        /*if (myFirebaseAuth.getCurrentUser()!=null){

            startActivity(new Intent(LoginActivity.this,CreationActivity.class));
        }
        else{
            Toast.makeText(LoginActivity.this, "Plz register first", Toast.LENGTH_SHORT).show();
        }*/
        final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
              String mail=mEmailView.getText().toString();
              String pass=mPasswordView.getText().toString();
                myFirebaseAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(LoginActivity.this,CreationActivity.class));
                           finish();
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Login details wrong!!! registr first", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    public void directToReg(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}

