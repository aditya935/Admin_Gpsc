package com.techwithadi.gpscbudy.Quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.techwithadi.gpscbudy.Adepters.QuestionAdepter;
import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.R;

import java.util.concurrent.TimeUnit;

public class QuestionsActivity extends AppCompatActivity {

 TextView question_no,timer,quize_title;
 AppCompatButton submit,clearbtn,markbtn;
 ImageButton bookmark,question_grid,btnback,btnnext,closebtn;
 RecyclerView question_view;
 private int queno=0;
 private int time;
 QuestionAdepter questionAdepter;
 DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_drawer_layout);
        init();
        time= Integer.parseInt(getIntent().getStringExtra("quize_time"));
         questionAdepter=new QuestionAdepter(Database.Question_list);
        question_view.setAdapter(questionAdepter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        question_view.setLayoutManager(linearLayoutManager);
        starttimer();
        setsnaphelper();
        setclicklistner();
    }

    private void starttimer() {
        int myresttime = time*60*1000;

        CountDownTimer countDownTimer=new CountDownTimer(myresttime+1000,1000) {
            @Override
            public void onTick(long remainingtime) {
                     int min= (int) TimeUnit.MILLISECONDS.toMinutes(remainingtime);
                     int sec= (int) (TimeUnit.MILLISECONDS.toSeconds(remainingtime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainingtime)));
               timer.setText(String.format("%02d:%02d min",min, sec));

            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }

    private void setclicklistner() {

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queno > 0)
                {
                    question_view.smoothScrollToPosition(queno-1);
                }
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queno < Database.Question_list.size()-1)
                {
                    question_view.smoothScrollToPosition(queno+1);
                }
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.Question_list.get(QuestionAdepter.cposition).setSelectedAns(-1);
                questionAdepter.notifyDataSetChanged();
            }
        });

       question_grid.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (!drawer.isDrawerOpen(GravityCompat.END))
               {
                  drawer.openDrawer(GravityCompat.END);
               }
               }
       });

       closebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (drawer.isDrawerOpen(GravityCompat.END))
               {
                   drawer.closeDrawer(GravityCompat.END);
               }
           }
       });
    }

    private void setsnaphelper() {
        SnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(question_view);

        question_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view=snapHelper.findSnapView(recyclerView.getLayoutManager());
                queno=recyclerView.getLayoutManager().getPosition(view);

                question_no.setText((queno+1)+"/"+Database.Question_list.size());
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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
        drawer=findViewById(R.id.drawer);
        closebtn=findViewById(R.id.closebtn);

        question_no.setText((queno+1)+"/"+Database.Question_list.size());
        quize_title.setText(getIntent().getStringExtra("quize_title"));

    }
}