package com.techwithadi.gpscbudy.Adepters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techwithadi.gpscbudy.ComplateListners;
import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.Models.TestModel;
import com.techwithadi.gpscbudy.Quiz.QuestionsActivity;
import com.techwithadi.gpscbudy.Quiz.StartQuizActivity;
import com.techwithadi.gpscbudy.Quiz.TestActivity;
import com.techwithadi.gpscbudy.R;

import java.util.List;

public class TestAdepter extends RecyclerView.Adapter<TestAdepter.Holder> {
    public List<TestModel> Test_list;
    public  String selected_test_id;
    public  String selected_cat_id;

    public TestAdepter(List<TestModel> test_list ) {
        Test_list = test_list;
    }

    @NonNull
    @Override
    public TestAdepter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.test_sample,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdepter.Holder holder, int position) {
        int prog = Test_list.get(position).getTopscore();
        holder.setdata(position,prog);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_cat_id=TestActivity.selected_cat;
                selected_test_id=Test_list.get(holder.getAdapterPosition()).getTestId();
                Intent intent= new Intent(holder.itemView.getContext(), StartQuizActivity.class);
                intent.putExtra("Selected_cat",TestActivity.selected_cat_id);
                intent.putExtra("Selected_cat_name",TestActivity.selected_cat);
                intent.putExtra("top score",prog+"");
                intent.putExtra("test id",Database.test_list.get(holder.getAdapterPosition()).getTestId()+"");
                intent.putExtra("Test_Time",Database.test_list.get(holder.getAdapterPosition()).getTime()+" min");
                intent.putExtra("Selected_test","Test No: "+(holder.getAdapterPosition()+1));
                view.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return Test_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView Test_No;
        private TextView Test_score;
        private ProgressBar progressBar;
        public Holder(@NonNull View itemView) {
            super(itemView);
            Test_No = itemView.findViewById(R.id.test_name);
            Test_score = itemView.findViewById(R.id.test_score);
            progressBar = itemView.findViewById(R.id.test_progress);
        }
        private void setdata(int position,int progress){

            Test_No.setText(Database.test_list.get(position).getTestId());
            Test_score.setText(progress+"% ");
            progressBar.setProgress(progress);
        }
    }
}
