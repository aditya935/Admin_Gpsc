package com.techwithadi.gpscbudy.Adepters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.techwithadi.gpscbudy.Models.QuestionModel;
import com.techwithadi.gpscbudy.R;

import java.util.ArrayList;

public class QuestionAdepter extends RecyclerView.Adapter<QuestionAdepter.ViewHolder> {
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
        AppCompatButton op_a,op_b,op_c,op_d;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question);
            op_a=itemView.findViewById(R.id.op_a);
            op_b=itemView.findViewById(R.id.op_b);
            op_c=itemView.findViewById(R.id.op_c);
            op_d=itemView.findViewById(R.id.op_d);
        }

        public void setdata(final int pos){
            question.setText(Questionlist.get(pos).getQuestion());
            op_a.setText(Questionlist.get(pos).getOptionA());
            op_b.setText(Questionlist.get(pos).getOptionB());
            op_c.setText(Questionlist.get(pos).getOptionC());
            op_d.setText(Questionlist.get(pos).getOptionD());
        }

    }
}
