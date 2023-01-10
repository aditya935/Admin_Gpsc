package com.techwithadi.gpscbudy.Quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.techwithadi.gpscbudy.Adepters.CategoryAdepter;
import com.techwithadi.gpscbudy.Adepters.TestAdepter;
import com.techwithadi.gpscbudy.ComplateListners;
import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.Models.TestModel;
import com.techwithadi.gpscbudy.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
  private   RecyclerView test_rec;
  private Toolbar toolbar;
    String cat_id;
  public static String selected_cat;
  public static String selected_cat_id;
    String selected_test_id;
    ProgressBar prg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        test_rec=findViewById(R.id.test_rec);
        toolbar=findViewById(R.id.toolbar);
        prg=findViewById(R.id.prg);

        setSupportActionBar(toolbar); //set Actionbar To Display
        getSupportActionBar().setDisplayShowHomeEnabled(true); //Set Title on Actionbar
        int cat_index =getIntent().getIntExtra("cat_index",0);//Get Title from Category Activity
        cat_id=getIntent().getStringExtra("cat_id");
        selected_cat_id=cat_id;
        selected_cat=getIntent().getStringExtra("cat_name");
        selected_test_id=getIntent().getStringExtra("test_id");
        getSupportActionBar().setTitle(Database.Sub_list.get(cat_index).getCat_name()); // SEt Title Text
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // SEt Back Button On Actionbar



        Database.loadtestdata(cat_id, new ComplateListners() {
            @Override
            public void OnSuccess() {
                prg.setVisibility(View.INVISIBLE);
                //Set LayoutManager to RecyclerView
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                test_rec.setLayoutManager(layoutManager);
                TestAdepter adepter= new TestAdepter(Database.test_list);
                test_rec.setAdapter(adepter);
            }

            @Override
            public void OnFailure() {

            }
        });
//        Database.loadquestions(new ComplateListners() {
//            @Override
//            public void OnSuccess() {
//
//            }
//
//            @Override
//            public void OnFailure() {
//
//            }
//        },selected_cat,selected_test_id);





    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            TestActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

