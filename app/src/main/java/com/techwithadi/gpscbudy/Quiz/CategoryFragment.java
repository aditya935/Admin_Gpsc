package com.techwithadi.gpscbudy.Quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.techwithadi.gpscbudy.Adepters.CatAdepter;
import com.techwithadi.gpscbudy.Adepters.CategoryAdepter;
import com.techwithadi.gpscbudy.Database;
import com.techwithadi.gpscbudy.Models.CategoryModel;
import com.techwithadi.gpscbudy.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private RecyclerView cat_grid;
    FirebaseFirestore db;


    public CategoryFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.category_main, container, false);
//        RecyclerView rv = container.findViewById(R.id.cat_recy);
//        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        String arr[] = {"java","c","php","python","java","c","php","python","java","c","php","python","java","c","php","python","java","c","php","python","java","c","php","python"};
//        rv.setAdapter(new CategoryAdepter(arr));
        cat_grid = view.findViewById(R.id.cat_recy);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        cat_grid.setLayoutManager(layoutManager);
        Database ob = new Database();



       // CategoryAdepter catAdepter = new CategoryAdepter(Database.Sub_list);
        CatAdepter catAdepter=new CatAdepter(Database.Sub_list,getContext());
        cat_grid.setAdapter(catAdepter);
       // cat_grid.setAdapter(catAdepter);


         return view;
    }


}