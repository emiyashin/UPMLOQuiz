package com.upm.lo.quiz;

import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upm.lo.quiz.Model.Question;

import java.util.HashMap;
import java.util.Map;

public class CreateQuiz extends AppCompatActivity {

    private EditText cQuestion, cOption1, cOption2, cOption3, cOption4, typeLO;



    DatabaseReference cReference;
    public long questionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        cReference = FirebaseDatabase.getInstance().getReference().child("Questions");

        cQuestion = (EditText) findViewById(R.id.cvQuestion);
        cOption1 = (EditText) findViewById(R.id.cvOption1);
        cOption2 = (EditText) findViewById(R.id.cvOption2);
        cOption3 = (EditText) findViewById(R.id.cvOption3);
        cOption4 = (EditText) findViewById(R.id.cvOption4);
        typeLO = (EditText) findViewById(R.id.cvTypeLO);

        DatabaseReference newPostRef = cReference.push();
        newPostRef.setValue();

        cReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionCount = dataSnapshot.getChildrenCount();

                 }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

                Map<Integer, Question> createQuiz = new HashMap<>();
                createQuiz.put((int)questionCount+1, new Question(cQuestion, "option1", "option2", "option3", "option4", "answer", 0));



        cReference.updateChildrenAsync(userUpdates);



    }
}
