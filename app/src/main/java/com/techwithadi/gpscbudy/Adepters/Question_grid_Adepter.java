package com.techwithadi.gpscbudy.Adepters;


import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.Quiz.QuestionsActivity;
import com.techwithadi.gpscbudy.R;

public class Question_grid_Adepter extends BaseAdapter {
    private int NoOfQuestions;

    public Question_grid_Adepter(int noOfQuestions) {
        NoOfQuestions = noOfQuestions;
    }

    @Override
    public int getCount() {
        return NoOfQuestions;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview;
        if (view == null){
            myview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.que_no_sample,viewGroup,false);
        }
        else {
            myview=view;
        }

        TextView que_no=myview.findViewById(R.id.que_no);
        que_no.setText((i+1)+"");

        switch (Database.Question_list.get(i).getStatus()){
            case 1:
                que_no.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(myview.getContext(),R.color.green)));
                break;
            case 2:
                que_no.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(myview.getContext(),R.color.red)));
                break;

                case 3:
                que_no.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(myview.getContext(),R.color.grey)));
                break;

                case 4:
                que_no.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(myview.getContext(),R.color.dark_blue)));
                break;
        }

        que_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsActivity.gotoque(i);
            }
        });

        return myview;
    }
}
