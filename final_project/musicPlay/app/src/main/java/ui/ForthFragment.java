package ui;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ForthFragment extends Fragment {
    private Button btn;
    private EditText author;
    private ImageButton btn1,btn2;
    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forth, container, false);

        btn = (Button) view.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }

        });

        btn1 = (ImageButton) view.findViewById(R.id.imageButton9);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),mainActivity.class);
                startActivity(intent);
            }

        });

        btn2 = (ImageButton) view.findViewById(R.id.imageButton11);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),mainActivity.class);
                startActivity(intent);
            }

        });

        return view;
    }
}