package com.upm.lo.quiz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.upm.lo.quiz.Model.Question;

public class RemoveActivity extends AppCompatActivity {

    private Spinner quizSpin;
    private Button removeBtn;
    private TextView tQuestion, tOption1, tOption2, tOption3, tOption4;

    DatabaseReference cDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        final Spinner quizSpin = (Spinner) findViewById(R.id.questionSpinner);
        Button removeBtn = (Button) findViewById(R.id.removeQuiz);

       final TextView tQuestion = (TextView) findViewById(R.id.questionRemoveTxt);
        final TextView tOption1 = (TextView) findViewById(R.id.qOption1);
        final TextView tOption2 = (TextView) findViewById(R.id.qOption2);
        final TextView tOption3 = (TextView) findViewById(R.id.qOption3);
        final TextView tOption4 = (TextView) findViewById(R.id.qOption4);

        cDatabase = FirebaseDatabase.getInstance().getReference("Questions");

        cDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Question question = dataSnapshot.getValue(Question.class);

                tQuestion.setText(question.getQuestion());
                tOption1.setText(question.getOption1());
                tOption2.setText(question.getOption2());
                tOption3.setText(question.getOption3());
                tOption4.setText(question.getOption4());

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
