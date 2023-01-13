package com.techwithadi.gpscbudy.Adepters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.Models.QuestionModel;
import com.techwithadi.gpscbudy.R;

import java.util.ArrayList;

public class QuestionAdepter extends RecyclerView.Adapter<QuestionAdepter.ViewHolder> {
    public static int cposition;
    public ArrayList<QuestionModel> Questionlist;

    public QuestionAdepter(ArrayList<QuestionModel> questionlist) {
        Questionlist = questionlist;
    }

    @NonNull
    @Override
    public QuestionAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdepter.ViewHolder holder, int position) {
        holder.setdata(position);
    }

    @Override
    public int getItemCount() {
        return Questionlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        AppCompatButton op_a,op_b,op_c,op_d, previous_selected_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question);
            op_a=itemView.findViewById(R.id.op_a);
            op_b=itemView.findViewById(R.id.op_b);
            op_c=itemView.findViewById(R.id.op_c);
            op_d=itemView.findViewById(R.id.op_d);
            previous_selected_btn=null;
        }

        public void setdata(final int pos){
            cposition=pos;
            question.setText(Questionlist.get(pos).getQuestion());
            op_a.setText(Questionlist.get(pos).getOptionA());
            op_b.setText(Questionlist.get(pos).getOptionB());
            op_c.setText(Questionlist.get(pos).getOptionC());
            op_d.setText(Questionlist.get(pos).getOptionD());

            setoption(op_a,1,pos);
            setoption(op_b,2,pos);
            setoption(op_c,3,pos);
            setoption(op_d,4,pos);

            op_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOption(op_a,1,pos);
                }
            });

            op_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOption(op_b,2,pos);
                }
            });

            op_c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOption(op_c,3,pos);
                }
            });

            op_d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOption(op_d,4,pos);
                }


            });


        }

        private void setoption(AppCompatButton btn, int i, int pos) {

            if (Database.Question_list.get(pos).getSelectedAns() == i){
                btn.setBackgroundResource(R.drawable.selcted_option);
            }
            else {
                btn.setBackgroundResource(R.drawable.unselected_option);
            }
        }

        private void selectOption(AppCompatButton btn, int i, int pos) {
            if (previous_selected_btn == null)
            {
                btn.setBackgroundResource(R.drawable.selcted_option);
                Database.Question_list.get(pos).setSelectedAns(i);
                previous_selected_btn=btn;
            }
            else if(previous_selected_btn.getId()==R.id.clearbtn){

            }
            else {
                if (previous_selected_btn.getId() == btn.getId())
                {
                    previous_selected_btn.setBackgroundResource(R.drawable.unselected_option);
                    Database.Question_list.get(pos).setSelectedAns(-1);
                    previous_selected_btn=null;
                }
                else {
                    previous_selected_btn.setBackgroundResource(R.drawable.unselected_option);
                    btn.setBackgroundResource(R.drawable.selcted_option);
                    Database.Question_list.get(pos).setSelectedAns(i);
                    previous_selected_btn=btn;
                }

            }
        }


    }
    public static void clearsel(){

    }
}
