package com.upm.lo.quiz;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.upm.lo.quiz.Model.Question;

public class ResultActivity extends AppCompatActivity {

    TextView t1,t2,t3;

    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        t1 = (TextView) findViewById(R.id.textView4);
        t2 = (TextView) findViewById(R.id.textView5);
        t3 = (TextView) findViewById(R.id.textView7);

        table = (TableLayout) findViewById(R.id.ResultLayout);

        Intent i=getIntent();

        String questions = i.getStringExtra("total");
        String correct = i.getStringExtra("correct");
        String wrong = i.getStringExtra("incorrect");
        int [] resultLO_total= i.getIntArrayExtra("total lo");
        int [] resultLO_correct = i.getIntArrayExtra("correct lo");

        for (int j=0; j<Question.LO.length; j++) {
            if (resultLO_total[j] > 0) {
                TableRow tr = new TableRow(this);
                TextView LO1 = new TextView(this);
                LO1.setText(Question.LO[j]);

                TextView LO_Correct = new TextView(this);
                LO_Correct.setText(resultLO_correct[j] + "/" + resultLO_total[j]);

                tr.addView(LO1);
                tr.addView(LO_Correct);

                table.addView(tr);
            }
        }

        t1.setText(questions);
        t2.setText(correct);
        t3.setText(wrong);




    }
}
