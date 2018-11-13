package com.example.user.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import cn.bmob.v3.BmobUser;

public class ThithFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view=inflater.inflate(R.layout.thith_fragment,container,false);
        BmobUser user=BmobUser.getCurrentUser();
        TextView name=(TextView) view.findViewById(R.id.my_name);
        name.setText(user.getUsername());
        TextView time=(TextView) view.findViewById(R.id.my_account);
        time.setText(user.getCreatedAt());
        Button course=(Button) view.findViewById(R.id.button7);
        course.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(),RecycleActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

