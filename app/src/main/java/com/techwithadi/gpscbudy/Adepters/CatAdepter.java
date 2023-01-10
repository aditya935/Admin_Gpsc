package com.techwithadi.gpscbudy.Adepters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techwithadi.gpscbudy.ComplateListners;
import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.MainActivity;
import com.techwithadi.gpscbudy.Models.CategoryModel;
import com.techwithadi.gpscbudy.Quiz.TestActivity;
import com.techwithadi.gpscbudy.R;

import java.util.ArrayList;

public class CatAdepter extends RecyclerView.Adapter<CatAdepter.Viewholder> {
   public  ArrayList<CategoryModel> Sub_list;
   Context context;
    int position;

    public CatAdepter(ArrayList<CategoryModel> sub_list, Context context) {
        Sub_list = sub_list;
        this.context=context;
    }

    @NonNull
    @Override
    public CatAdepter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_sample,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatAdepter.Viewholder holder,int position) {
        holder.cat_name.setText(Sub_list.get(position).getCat_name());
        holder.count.setText(Sub_list.get(position).getNo_of_test()+" Tests");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, TestActivity.class);
                intent.putExtra("cat_name", Database.Sub_list.get(holder.getAdapterPosition()).getCat_name());
                intent.putExtra("cat_index", holder.getAdapterPosition());
                intent.putExtra("cat_id", Database.Sub_list.get(holder.getAdapterPosition()).getDocid());
                view.getContext().startActivity(intent);

                Database.loadtestdata(Database.Sub_list.get(holder.getAdapterPosition()).getDocid(), new ComplateListners() {
                    @Override
                    public void OnSuccess() {

                    }

                    @Override
                    public void OnFailure() {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return Sub_list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView cat_img;
        TextView cat_name, count;
        public Viewholder(@NonNull View itemView) {



            super(itemView);
            cat_name=itemView.findViewById(R.id.cat_name);
//       cat_img=myview.findViewById(R.id.cat_img);
            count=itemView.findViewById(R.id.testcount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context, TestActivity.class);
                }
            });
        }
    }
}
