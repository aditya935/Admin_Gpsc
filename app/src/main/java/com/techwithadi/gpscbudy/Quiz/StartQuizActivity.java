package com.techwithadi.gpscbudy.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techwithadi.gpscbudy.ComplateListners;
import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.R;

public class StartQuizActivity extends AppCompatActivity {
TextView cat_name, test_no, total_question, top_score, total_time;
AppCompatButton start;
ImageView back_btn;
ProgressBar queloader;
String selected_cat_id;
String selected_cat_name;
String selected_test_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        init();
        selected_cat_id=getIntent().getStringExtra("Selected_cat");
        selected_cat_name=getIntent().getStringExtra("Selected_cat_name");
        selected_test_id=getIntent().getStringExtra("test id");
        Database.loadquestions(new ComplateListners() {
            @Override
            public void OnSuccess() {
                queloader.setVisibility(View.INVISIBLE);
                cat_name.setText(selected_cat_name);
                //test_no.setText(selected_test_id);
                test_no.setText(selected_test_id);
                total_time.setText(getIntent().getStringExtra("Test_Time")+" min");
                top_score.setText(getIntent().getStringExtra("top score"));
                total_question.setText(Database.Question_list.size()+"");
            }

            @Override
            public void OnFailure() {

            }
        },selected_cat_id,selected_test_id);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartQuizActivity.this.finish();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(StartQuizActivity.this,QuestionsActivity.class);
                intent.putExtra("total_que",Database.Question_list.size());
                intent.putExtra("quize_title",selected_cat_name);
                intent.putExtra("quize_time",getIntent().getStringExtra("Test_Time"));
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        cat_name=findViewById(R.id.st_cat_name);
        test_no=findViewById(R.id.st_test_no);
        total_question=findViewById(R.id.st_total_q);
        top_score=findViewById(R.id.st_best_score);
        total_time=findViewById(R.id.st_time);
        start=findViewById(R.id.st_btn_start);
        back_btn=findViewById(R.id.st_back);
        queloader=findViewById(R.id.loadque);

    }
}