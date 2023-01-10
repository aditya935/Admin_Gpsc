package com.techwithadi.gpscbudy.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.techwithadi.gpscbudy.R;

public class QuestionsActivity extends AppCompatActivity {

 TextView question_no,timer,quize_title;
 AppCompatButton submit,clearbtn,markbtn;
 ImageView bookmark,question_grid,btnback,btnnext;
 RecyclerView question_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
    }
}