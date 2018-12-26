package ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dialog.Dynamic;


public class ThreeFragment extends Fragment {
    private ImageButton btn;

    List<Dynamic> dynamics;
    private LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        LinearLayout.LayoutParams LL=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        LL.setMargins(2,12,2,2);
        LinearLayout.LayoutParams LW=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        LW.setMargins(2,2,2,2);
        LinearLayout.LayoutParams LP=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        LP.setMargins(18,0,0,0);
        layout=view.findViewById(R.id.msg);
        //获取传过来的参数
        dynamics=(List<Dynamic>) ((Activity1)getActivity()).getDynamic();
        for(int i=0;i<dynamics.size();i++){
            TextView author=new TextView(this.getContext());
            author.setText(dynamics.get(i).getAuthor());
            author.setTextSize(16);
            author.setLayoutParams(LL);
            author.setTextColor(Color.BLACK);
            author.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            TextView time=new TextView(this.getContext());
            time.setText(dynamics.get(i).getTime());
            time.setTextSize(12);
            time.setLayoutParams(LW);

            TextView content=new TextView(this.getContext());
            content.setText(dynamics.get(i).getContent());
            content.setTextSize(14);
            content.setTextColor(Color.BLACK);
            content.setLayoutParams(LP);

            layout.addView(author);
            layout.addView(time);
            layout.addView(content);
        }


        btn = (ImageButton) view.findViewById(R.id.imageButton2);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),DynamicActivity.class);
                startActivity(intent);
            }

        });

        return view;
    }


}
