package com.example.reshmaanjali.cpool;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Searching extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView textView;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
      //  textView = (TextView)findViewById(R.id.tv);
        recyclerView=findViewById(R.id.id_rec_view_srch);
        final ArrayList<String> fPlaceArray=new ArrayList<>();
        final ArrayList<String> tPlaceArray=new ArrayList<String>();
        final ArrayList<String> fDateTimeArray=new ArrayList<>();
        final ArrayList<String> tDateTimeArray=new ArrayList<>();
        final ArrayList<String> snotesArray=new ArrayList<>();
        final ArrayList<String> vehDetailsArray=new ArrayList<>();
        final ArrayList<String> cntDetailsArray=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("pooldata");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = "";
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {

                    fPlaceArray.add((String) dataSnapshot1.child("fplace").getValue());
                    tPlaceArray.add((String) dataSnapshot1.child("tPlace").getValue());
                    fDateTimeArray.add((String) dataSnapshot1.child("fDate").getValue()+" "+(String)dataSnapshot1.child("fTime").getValue());
                    tDateTimeArray.add((String) dataSnapshot1.child("tDate").getValue()+" "+(String)dataSnapshot1.child("tTime").getValue());//firebase open cheyava
                    snotesArray.add((String)dataSnapshot1.child("snotes").getValue());
                    vehDetailsArray.add(" "+(String) dataSnapshot1.child("vType").getValue()+"\n Vehicle No : "+(String)dataSnapshot1.child("vNumber").getValue());
                    cntDetailsArray.add(" "+(String) dataSnapshot1.child("cName").getValue()+"\n Phone : "+(String)dataSnapshot1.child("cMobile").getValue()+"\n Mail : "+(String)dataSnapshot1.child("cMail").getValue());
                    //data = data +  (String) dataSnapshot1.getValue() + "\n";
                }

                recyclerView.setAdapter(new PoolDataAdapter(fPlaceArray,tPlaceArray,fDateTimeArray,tDateTimeArray,snotesArray,
                        vehDetailsArray,cntDetailsArray));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class PoolDataAdapter extends RecyclerView.Adapter<PoolDataAdapter.MyViewHolder>{

        ArrayList<String> fPlaceArray,tPlaceArray,fDateTimeArray,tDateTimeArray,snotesArray;
        ArrayList<String> vehDetailsArray,cntDetailsArray;

        PoolDataAdapter(ArrayList<String> fPlaceArray,ArrayList<String> tPlaceArray
        ,ArrayList<String> fDateTimeArray,ArrayList<String> tDateTimeArray,
                        ArrayList<String> snotesArray,ArrayList<String> vehDetailsArray,
                        ArrayList<String> cntDetailsArray){
            this.fPlaceArray = fPlaceArray;
            this.tPlaceArray = tPlaceArray;
            this.fDateTimeArray = fDateTimeArray;
            this.tDateTimeArray = tDateTimeArray;
            this.snotesArray = snotesArray;
            this.vehDetailsArray = vehDetailsArray;
            this.cntDetailsArray = cntDetailsArray;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_in_search,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.fromPlace.setText(fPlaceArray.get(position));
            holder.toPlace.setText(tPlaceArray.get(position));
            holder.fromDateTime.setText(fDateTimeArray.get(position));
            holder.toDateTime.setText(tDateTimeArray.get(position));
            holder.notes.setText(snotesArray.get(position));
            holder.vehDetails.setText(vehDetailsArray.get(position));
            holder.cntDetails.setText(cntDetailsArray.get(position));
        }

        @Override
        public int getItemCount() {
            return fPlaceArray.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView fromPlace;
            TextView toPlace;
            TextView fromDateTime;
            TextView toDateTime;
            TextView vehDetails;
            TextView cntDetails;
            TextView notes;
            public MyViewHolder(View itemView) {
                super(itemView);
                fromPlace=itemView.findViewById(R.id.id_fromPlaceData);
                toPlace=itemView.findViewById(R.id.id_toPlaceData);
                fromDateTime=itemView.findViewById(R.id.id_fromDateTimeData);
                toDateTime=itemView.findViewById(R.id.id_toDateTimeData);
                vehDetails=itemView.findViewById(R.id.id_veh_num_veh_type_data);
                cntDetails=itemView.findViewById(R.id.id_cnt_name_mobile_mail_data);
                notes=itemView.findViewById(R.id.id_smallNotesData);
            }
        }
    }
}
