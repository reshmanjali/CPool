package com.example.reshmaanjali.cpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CreationActivity extends AppCompatActivity {
    @BindView(R.id.id_from_place) EditText frmPlace;
    @BindView(R.id.id_from_date) EditText frmDate;
    @BindView(R.id.id_from_time) EditText frmTime;
    @BindView(R.id.id_to_place) EditText toPlace;
    @BindView(R.id.id_to_date) EditText toDate;
    @BindView(R.id.id_to_time) EditText  toTime;
    @BindView(R.id.id_veh_type) EditText vehType;
    @BindView(R.id.id_veh_num) EditText vehNumber;
    @BindView(R.id.id_cnt_name) EditText cntName;
    @BindView(R.id.id_cnt_mobile) EditText cntMobile;
    @BindView(R.id.id_cnt_mail) EditText cntmail;
    @BindView(R.id.id_sml_note) EditText smallNote;
    @BindView(R.id.id_create_btn)
    Button create_btn;
  FirebaseDatabase database;
    DatabaseReference myRef;
    String fPlace;
    String fDate;
    String fTime;
    String tPlace;
    String tDate;
    String tTime;
    String vType;
    String vNumber;
    String cName;
    String cMobile;
    String cMail;
    String sNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        ButterKnife.bind(this);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 database = FirebaseDatabase.getInstance();
                 Date d= Calendar.getInstance().getTime();
                 myRef = database.getReference("pooldata").child(d.toString());
                //String myData=frmPlace.getText().toString()+frmDate.getText().toString()+frmTime.getText().toString()+toPlace.getText().toString()+toDate.getText().toString()+toTime.getText().toString()+vehType.getText().toString()+vehNumber.getText().toString()+cntName.getText().toString()+cntmail.getText().toString()+cntMobile.getText().toString();
                fPlace = frmPlace.getText().toString();
                fDate = frmDate.getText().toString();
                fTime = frmTime.getText().toString();
                tPlace= toPlace.getText().toString();
                tDate = toDate.getText().toString();
                tTime = toTime.getText().toString();
                vType = vehType.getText().toString();
                vNumber = vehNumber.getText().toString();
                cName = cntName.getText().toString();
                cMobile = cntMobile.getText().toString();
                cMail = cntmail.getText().toString();
                sNote = smallNote.getText().toString();
                myRef.child("fplace").setValue(fPlace);//same
                myRef.child("fDate").setValue(fDate);
                myRef.child("fTime").setValue(fTime);
                myRef.child("tPlace").setValue(tPlace);
                myRef.child("tDate").setValue(tDate);
                myRef.child("tTime").setValue(tTime);
                myRef.child("vType").setValue(vType);
                myRef.child("vNumber").setValue(vNumber);
                myRef.child("cName").setValue(cName);
                myRef.child("cMobile").setValue(cMobile);
                myRef.child("cMail").setValue(cMail);
                myRef.child("snotes").setValue(sNote);
            }
        });

    }
}
