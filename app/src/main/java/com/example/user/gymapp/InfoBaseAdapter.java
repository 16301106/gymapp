package com.example.user.gymapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InfoBaseAdapter extends RecyclerView.Adapter<InfoBaseAdapter.MyViewHolder>{
    private List<Course> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public InfoBaseAdapter( Context context,List<Course> list){
        this.list=list;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView=layoutInflater.inflate(R.layout.recyclerview_cardview_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position){
        final Course user=list.get(position);
        holder.tvID.setText(user.getName());
        holder.tvName.setText(user.getCouch());
        holder.PhoneNum.setText(user.getPhone());

        if(onItemClickListener!=null){
            holder.PhoneNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,pos);
                }
            });
        }
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tvID,tvName,PhoneNum;
        View itemView;
        String number;

        public MyViewHolder(View view){
            super(view);
            itemView=view;
            image=(ImageView) itemView.findViewById(R.id.ivImage);
            tvID=(TextView) itemView.findViewById(R.id.tvId);
            tvName=(TextView) itemView.findViewById(R.id.tvName);
            PhoneNum=(TextView) itemView.findViewById(R.id.PhoneNum);
        }
    }
}
