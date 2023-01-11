package com.techwithadi.gpscbudy.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.techwithadi.gpscbudy.Adepters.QuestionAdepter;
import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.R;

public class QuestionsActivity extends AppCompatActivity {

 TextView question_no,timer,quize_title;
 AppCompatButton submit,clearbtn,markbtn;
 ImageButton bookmark,question_grid,btnback,btnnext;
 RecyclerView question_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        init();

        QuestionAdepter questionAdepter=new QuestionAdepter(Database.Question_list);
        question_view.setAdapter(questionAdepter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        question_view.setLayoutManager(linearLayoutManager);
    }
    public void init(){
        question_no=findViewById(R.id.question_no);
        timer=findViewById(R.id.timer);
        quize_title=findViewById(R.id.quize_title);
        submit=findViewById(R.id.submit);
        clearbtn=findViewById(R.id.clearbtn);
        markbtn=findViewById(R.id.markbtn);
        bookmark=findViewById(R.id.bookmark);
        question_view=findViewById(R.id.question_view);
        question_grid=findViewById(R.id.question_grid);
        btnback=findViewById(R.id.btnback);
        btnnext=findViewById(R.id.btnnext);


    }
}