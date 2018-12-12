package com.upm.lo.quiz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RemoveActivity extends AppCompatActivity {

    private Spinner quizSpin;
    private Button removeBtn;

    DatabaseReference cDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        final Spinner quizSpin = (Spinner) findViewById(R.id.questionSpinner);
        Button removeBtn = (Button) findViewById(R.id.removeQuiz);

        cDatabase = FirebaseDatabase.getInstance().getReference("Questions");

        cDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> questionsNo = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("questionCount").getValue(String.class);
                    questionsNo.add(areaName);
                }
                ArrayAdapter<String> questionAdapter = new ArrayAdapter<String>(RemoveActivity.this, android.R.layout.simple_spinner_item, questionsNo);
                questionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                quizSpin.setAdapter(questionAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
