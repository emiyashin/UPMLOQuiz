package com.upm.lo.quiz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CreateQuiz extends AppCompatActivity {

    private Button cPush;
    private EditText cQuestion, cOption1, cOption2, cOption3, cOption4, cAnswer, typeLO;
    //private TextView testCounter;
    public long questionCount;

    DatabaseReference cDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        cQuestion = (EditText) findViewById(R.id.cvQuestion);
        cOption1 = (EditText) findViewById(R.id.cvOption1);
        cOption2 = (EditText) findViewById(R.id.cvOption2);
        cOption3 = (EditText) findViewById(R.id.cvOption3);
        cOption4 = (EditText) findViewById(R.id.cvOption4);
        cAnswer = (EditText) findViewById(R.id.cvAnswer);
        typeLO = (EditText) findViewById(R.id.cvTypeLO);

        cPush = (Button) findViewById(R.id.cButton);

        //testCounter = (TextView) findViewById(R.id.tQuestion);

        createQuiz();

    }

    private void createQuiz() {

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //final DatabaseReference cReference = database.getReference("Questions");

        //int qCount = (int)(questionCount)+1;
        //final String counter = Integer.toString(qCount);

         //String counter = Integer.toString((int)(questionCount)+1);



        cDatabase = FirebaseDatabase.getInstance().getReference("Questions");
        cDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionCount = dataSnapshot.getChildrenCount();

                int qCount = (int)(questionCount)+1;
                final String counter = Integer.toString(qCount);
                //testCounter.setText(counter);


                cPush.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //createQuiz.put(counter, new Question(question, option1, option2, option3, option4, answer,intTypeLO));

                        //createQuiz.put("Question number", counter)
                        //createQuiz.put(counter, new Question(question, option1, option2, option3, option4, answer,intTypeLO));

                        //DatabaseReference newCRef = cDatabase.child("Questions").push();
                        //newCRef.setValue(createQuiz);

                        String question = cQuestion.getText().toString();
                        String option1 = cOption1.getText().toString();
                        String option2 = cOption2.getText().toString();
                        String option3 = cOption3.getText().toString();
                        String option4 = cOption4.getText().toString();
                        String answer = cAnswer.getText().toString();
                        String LO = typeLO.getText().toString();

                        int intTypeLO = Integer.parseInt(LO);

                        Map<String, Object> createQuiz = new HashMap<>();

                        createQuiz.put("question", question);
                        createQuiz.put("option1", option1);
                        createQuiz.put("option2", option2);
                        createQuiz.put("option3", option3);
                        createQuiz.put("option4", option4);
                        createQuiz.put("answer", answer);
                        createQuiz.put("intLO", intTypeLO);
                        //createQuiz.put("questionCount", counter);

                        cDatabase.child(counter).setValue(createQuiz);
                        Toast.makeText(getApplicationContext(), "Question uploaded.", Toast.LENGTH_SHORT).show();

                    }

                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Question failed to upload.", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
