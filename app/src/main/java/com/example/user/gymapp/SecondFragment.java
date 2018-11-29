package com.example.user.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondFragment extends Fragment{
    private static final String TAG="ListActivity";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.second_fragment,container,false);
        Button course =(Button) view.findViewById(R.id.button_video);
        course.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),VideoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}