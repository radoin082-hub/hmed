package com.example.psp2.Controller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psp2.Model.Faculty;
import com.example.psp2.R;
import com.example.psp2.UI.Nextdata;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private List<Faculty> FacultyList;

    public Adapter(Context context, List<Faculty> facultyList) {
        this.context = context;
        FacultyList = facultyList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_content,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        Faculty faculty=FacultyList.get(position);
        holder.name_fac1.setText(faculty.getName_fac());
        holder.next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Nextdata.class);
                intent.putExtra("id",faculty.getId());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return FacultyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       public TextView name_fac1;
       public ImageView next1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_fac1 = itemView.findViewById(R.id.name_fac);
            next1=itemView.findViewById(R.id.imageViewNext);
        }

    }

}
