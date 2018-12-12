package com.upm.lo.quiz;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView test;
    private String spinText;

    DatabaseReference cDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        final Spinner quizSpin = (Spinner) findViewById(R.id.questionSpinner);
        final Button removeBtn = (Button) findViewById(R.id.removeQuiz);
        final TextView test = (TextView) findViewById(R.id.qTest);

        cDatabase = FirebaseDatabase.getInstance().getReference("Questions");

        cDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> questionList = new ArrayList<String>();

                for (DataSnapshot questionSnapshot: dataSnapshot.getChildren()) {
                    String questions = questionSnapshot.child("questionCount").getValue(String.class);
                    if (questions!=null){
                        questionList.add(questions);
                    }
                }

                ArrayAdapter<String> questionAdapter = new ArrayAdapter<String>(RemoveActivity.this, android.R.layout.simple_spinner_item, questionList);
                questionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                quizSpin.setAdapter(questionAdapter);

                removeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cDatabase.child(spinText).removeValue();
                        Toast.makeText(getApplicationContext(), "Question "+spinText+" deleted.", Toast.LENGTH_SHORT).show();
                    }
                });

                //quizSpin.setOnItemClickListener(onItemSelectedListener1);

                //String spinText = quizSpin.getSelectedItem().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        quizSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinText = quizSpin.getSelectedItem().toString();
                test.setText(spinText);
            }
                @Override
                public void onNothingSelected(AdapterView<?> parent){

        }



    });


    }


}
