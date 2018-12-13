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

public class CreateSet extends AppCompatActivity {

    private Button submitSet;
    private TextView tvCreate, tvQuestion;
    private EditText csQuestion1, csQuestion2, csQuestion3;

    private long questionCount;

    DatabaseReference cDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_set);

        submitSet = (Button) findViewById(R.id.eButton);

        tvCreate = (TextView) findViewById(R.id.tQuiz);
        tvQuestion = (TextView) findViewById(R.id.tQuizNo);

        csQuestion1 = (EditText) findViewById(R.id.eQuestion1);
        csQuestion2 = (EditText) findViewById(R.id.eQuestion2);
        csQuestion3 = (EditText) findViewById(R.id.eQuestion3);

        createSet();
    }

    private void createSet() {

        cDatabase = FirebaseDatabase.getInstance().getReference("Quiz");
        cDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionCount = dataSnapshot.getChildrenCount();

                int qCount = (int)(questionCount)+1;
                final String counter = Integer.toString(qCount);

                submitSet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String questionSel1 = csQuestion1.getText().toString();
                        String questionSel2 = csQuestion2.getText().toString();
                        String questionSel3 = csQuestion3.getText().toString();

                        Map<String, Object> createSet = new HashMap<>();

                        createSet.put("0", questionSel1);
                        createSet.put("1", questionSel2);
                        createSet.put("2", questionSel3);

                        cDatabase.child(counter).child("questionList").setValue(createSet);
                        Toast.makeText(getApplicationContext(), "Quiz created.", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Quiz failed to create.", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
