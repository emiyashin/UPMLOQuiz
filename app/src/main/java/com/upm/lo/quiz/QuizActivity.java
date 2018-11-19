package com.upm.lo.quiz;

import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upm.lo.quiz.Model.Question;

public class QuizActivity extends AppCompatActivity {

    private Button b1, b2, b3, b4;
    private TextView t1_question, timerTxt;
    private int total = 1, correct = 0, wrong = 0;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);

        t1_question = (TextView) findViewById(R.id.questionTxt);
        timerTxt = (TextView) findViewById(R.id.timerTxt);

        updateQuestion();
    }

    private void updateQuestion() {
        if (total>4) {
            //open the result activity
        }

        else
        {
            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   final Question question = dataSnapshot.getValue(Question.class);

                    t1_question.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b1.getText().toString().equals(question.getAnswer())) {
                                Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                                b1.setBackgroundColor(Color.GREEN);
                                correct = correct+1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.GRAY);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else {
                                // answer is wrong, find the correct answer and make it green
                                Toast.makeText(getApplicationContext(),"Wrong Answer!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                b1.setBackgroundColor(Color.RED);

                                if(b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.GRAY);
                                        b2.setBackgroundColor(Color.GRAY);
                                        b3.setBackgroundColor(Color.GRAY);
                                        b4.setBackgroundColor(Color.GRAY);
                                    }
                                },1500);

                            }
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b2.getText().toString().equals(question.getAnswer())) {
                                Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                                b2.setBackgroundColor(Color.GREEN);
                                correct = correct+1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.GRAY);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else {
                                // answer is wrong, find the correct answer and make it green
                                Toast.makeText(getApplicationContext(),"Wrong Answer!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                b2.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.GRAY);
                                        b2.setBackgroundColor(Color.GRAY);
                                        b3.setBackgroundColor(Color.GRAY);
                                        b4.setBackgroundColor(Color.GRAY);
                                    }
                                },1500);

                            }
                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b3.getText().toString().equals(question.getAnswer())) {
                                Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                                b1.setBackgroundColor(Color.GREEN);
                                correct = correct+1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundColor(Color.GRAY);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else {
                                // answer is wrong, find the correct answer and make it green
                                Toast.makeText(getApplicationContext(),"Wrong Answer!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                b3.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.GRAY);
                                        b2.setBackgroundColor(Color.GRAY);
                                        b3.setBackgroundColor(Color.GRAY);
                                        b4.setBackgroundColor(Color.GRAY);
                                    }
                                },1500);

                            }
                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b4.getText().toString().equals(question.getAnswer())) {
                                Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                                b4.setBackgroundColor(Color.GREEN);
                                correct = correct+1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.GRAY);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else {
                                // answer is wrong, find the correct answer and make it green
                                Toast.makeText(getApplicationContext(),"Wrong Answer!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                b1.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.GRAY);
                                        b2.setBackgroundColor(Color.GRAY);
                                        b3.setBackgroundColor(Color.GRAY);
                                        b4.setBackgroundColor(Color.GRAY);
                                    }
                                },1500);

                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

}
