package com.example.finallabassignmentc0773774;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecylAdapter extends RecyclerView.Adapter<RecylAdapter.listViewHolder> {

    private List<UserM> uList;

    public RecylAdapter(List<UserM> uList) {
        this.uList = uList;
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyl_cell, parent, false);
        return new listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final listViewHolder holder, int position) {
        final UserM user = uList.get(position);

        holder.idTV.setText(String.valueOf("id: "+user.getId()));
        holder.nameTV.setText(String.valueOf("name: "+user.getFname()+" "+user.getLname()));

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserServiceImpl userService = new UserServiceImpl(v.getContext());
                userService.delete(user);
                refreshList(userService);
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vAct = new Intent(v.getContext(), DetailActivity.class);

//                vAct.putExtra("fname", user.getFname());
//                vAct.putExtra("lname", user.getLname());
//                vAct.putExtra("phone", user.getPhone());
//                vAct.putExtra("address", user.getAddress());
                vAct.putExtra("userm", user);
                vAct.putExtra("editBool", true);

                v.getContext().startActivity(vAct);

            }
        });

    }

    @Override
    public int getItemCount() {
        return uList.size();
    }

    public static class listViewHolder extends RecyclerView.ViewHolder {

        public TextView idTV;
        public TextView nameTV;
        public ImageButton deleteBtn;
        public ImageButton editBtn;

        public listViewHolder(@NonNull View itemView) {
            super(itemView);

            idTV = itemView.findViewById(R.id.id);
            nameTV = itemView.findViewById(R.id.fname);

            deleteBtn = itemView.findViewById(R.id.delete);
            editBtn = itemView.findViewById(R.id.edit);
        }
    }

    private void refreshList(UserServiceImpl userService) {

        uList.removeAll(uList);
        uList.addAll(userService.getAll());
        notifyDataSetChanged();
    }



}
