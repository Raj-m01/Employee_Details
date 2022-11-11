package com.example.employeedetails.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetails.R;
import com.example.employeedetails.models.EmployeeModel;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private final ArrayList<EmployeeModel> list;

    private static MyOnClickListener onClickListener;

    public EmployeeAdapter(ArrayList<EmployeeModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        EmployeeModel empData = list.get(position);

        holder.name.setText(empData.getName());
        holder.age.setText(String.valueOf(empData.getAge()));
        holder.salary.setText(String.valueOf(empData.getSalary()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView age;
        public TextView salary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.age = itemView.findViewById(R.id.age);
            this.salary = itemView.findViewById(R.id.salary);


            itemView.setOnClickListener(view -> {

                int pos = getAdapterPosition();

                if (onClickListener != null && pos != RecyclerView.NO_POSITION) {
                    onClickListener.onItemClick(pos);
                }

            });

        }

    }

    public interface MyOnClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(MyOnClickListener listener) {
        this.onClickListener = listener;
    }

}
