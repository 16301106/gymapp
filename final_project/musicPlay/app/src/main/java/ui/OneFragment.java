package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class OneFragment extends Fragment {

    private ImageButton btn;
    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    String titles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        btn = (ImageButton) view.findViewById(R.id.imageButton);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),mainActivity.class);
                startActivity(intent);

            }

        });

        btn1 = (ImageButton) view.findViewById(R.id.imageButton3);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                        "该功能尚未开发 ! ",
                        Toast.LENGTH_SHORT).show();
            }

        });

        btn2 = (ImageButton) view.findViewById(R.id.imageButton4);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                        "该功能尚未开发 ! ",
                        Toast.LENGTH_SHORT).show();
            }

        });

        btn3= (ImageButton) view.findViewById(R.id.imageButton5);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                        "该功能尚未开发 ! ",
                        Toast.LENGTH_SHORT).show();
            }

        });

        btn4 = (ImageButton) view.findViewById(R.id.imageButton6);
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                        "该功能尚未开发 ! ",
                        Toast.LENGTH_SHORT).show();
            }

        });



        return view;
    }



}