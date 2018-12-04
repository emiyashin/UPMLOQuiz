package com.upm.lo.quiz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upm.lo.quiz.Model.Question;

import java.util.HashMap;
import java.util.Map;

public class CreateQuiz extends AppCompatActivity {

    private Button cPush;
    private EditText cQuestion, cOption1, cOption2, cOption3, cOption4, cAnswer, typeLO;
    public long questionCount;

    DatabaseReference cReference;

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

        createQuiz();

    }

    private void createQuiz() {

        cReference = FirebaseDatabase.getInstance().getReference().child("Questions");

        cPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cQuestion = ((EditText) findViewById(R.id.cvQuestion)).getText().toString();
                String cOption1 = ((EditText) findViewById(R.id.cvOption1)).getText().toString();
                String cOption2 = ((EditText) findViewById(R.id.cvOption2)).getText().toString();
                String cOption3 = ((EditText) findViewById(R.id.cvOption3)).getText().toString();
                String cOption4 = ((EditText) findViewById(R.id.cvOption4)).getText().toString();
                String cAnswer = ((EditText) findViewById(R.id.cvAnswer)).getText().toString();
                String typeLO = ((EditText) findViewById(R.id.cvTypeLO)).getText().toString();

                int intTypeLO = Integer.parseInt(typeLO);

                Map<Integer, Question> createQuiz = new HashMap<>();
                createQuiz.put((int)questionCount+1, new Question(cQuestion, cOption1, cOption2, cOption3, cOption4, cAnswer,intTypeLO));
                DatabaseReference newCRef = cReference.child("Question").push();
                newCRef.setValue(createQuiz);
                Toast.makeText(getApplicationContext(), "Question uploaded.", Toast.LENGTH_SHORT).show();
            }

    });

        cReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionCount = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
