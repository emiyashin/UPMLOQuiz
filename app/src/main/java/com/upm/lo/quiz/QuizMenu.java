package com.upm.lo.quiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.upm.lo.quiz.Model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizMenu extends AppCompatActivity {

    private Spinner startSpin;
    private TextView tvSelect;
    private Button startBtn;
    private String selText, q1, q2, q3;

    DatabaseReference cDatabase, quizRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);

        startSpin = (Spinner) findViewById(R.id.selectSpin);
        tvSelect = (TextView) findViewById(R.id.tvQuizSelect);
        startBtn = (Button) findViewById(R.id.startQuiz);

        selectQuiz();

    }

    public void selectQuiz() {

        cDatabase = FirebaseDatabase.getInstance().getReference("Quiz");

        cDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> selectList = new ArrayList<>();

                for (DataSnapshot quizSnapshot: dataSnapshot.getChildren()) {
                    String quizNo = quizSnapshot.child("questionList").child("quizCount").getValue(String.class);

                    if (quizNo !=null){
                        selectList.add(quizNo);
                    }
                }

                ArrayAdapter<String> selectAdapter = new ArrayAdapter<String>(QuizMenu.this, android.R.layout.simple_spinner_item, selectList);
                selectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                startSpin.setAdapter(selectAdapter);



                startBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(QuizMenu.this, QuizActivity.class);
                        myIntent.putExtra("quizCount", String.valueOf(selText));
                        myIntent.putExtra("q1", String.valueOf(q1));
                        myIntent.putExtra("q2", String.valueOf(q2));
                        myIntent.putExtra("q3", String.valueOf(q3));
                        startActivity(myIntent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        startSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selText = startSpin.getSelectedItem().toString();
                tvSelect.setText(q1);

                quizRef = FirebaseDatabase.getInstance().getReference().child("Quiz").child(selText);
                quizRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Quiz quiz = dataSnapshot.child("questionList").getValue(Quiz.class);
                        q1 = quiz.getQ1();
                        q2 = quiz.getQ2();
                        q3 = quiz.getQ3();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }



        });

    }
}
