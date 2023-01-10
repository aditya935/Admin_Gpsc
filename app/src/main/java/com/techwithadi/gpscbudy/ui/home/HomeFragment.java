package com.techwithadi.gpscbudy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.techwithadi.gpscbudy.Quiz.AccountFragment;
import com.techwithadi.gpscbudy.Quiz.CategoryFragment;
import com.techwithadi.gpscbudy.Quiz.LeaderFragment;
import com.techwithadi.gpscbudy.R;
import com.techwithadi.gpscbudy.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Fragment fragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fragment = new CategoryFragment();
        loadfragment(fragment);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case  R.id.home: fragment= new CategoryFragment();
                    break;
                    case  R.id.leader: fragment= new LeaderFragment();
                        break;
                    case  R.id.account: fragment = new AccountFragment();
                        break;
                }

                loadfragment(fragment);
                return true;
            }
        });

       return root;

    }
    public void loadfragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

