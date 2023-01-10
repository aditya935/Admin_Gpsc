package com.techwithadi.gpscbudy.Adepters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techwithadi.gpscbudy.Models.CategoryModel;
import com.techwithadi.gpscbudy.Quiz.TestActivity;
import com.techwithadi.gpscbudy.R;

import java.util.List;

public class CategoryAdepter extends BaseAdapter {
  public static List<CategoryModel> cat_list;

    public CategoryAdepter(List<CategoryModel> cat_list) {
        this.cat_list = cat_list;
    }

    @Override
    public int getCount() {
        return cat_list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return cat_list.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
       View myview;
       if (view==null){
           myview=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_sample,viewGroup,false);
       }else {

           myview=view;
       }

       myview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(view.getContext(), TestActivity.class);
               intent.putExtra("cat_index",i);
               view.getContext().startActivity(intent);
           }
       });
       ImageView cat_img;
       TextView cat_name, count;

       cat_name=myview.findViewById(R.id.cat_name);
//       cat_img=myview.findViewById(R.id.cat_img);
       count=myview.findViewById(R.id.testcount);

       cat_name.setText(cat_list.get(i).getCat_name());
       count.setText(cat_list.get(i).getNo_of_test()+" Tests");
       return myview;
    }
}
