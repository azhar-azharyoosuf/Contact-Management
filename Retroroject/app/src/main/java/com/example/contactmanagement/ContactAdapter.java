package com.example.contactmanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.contact_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.txtId.setText("User Id: "+contact.getContactId());
        holder.txtFName.setText(contact.getFirstName());
        holder.txtLName.setText(contact.getLastName());
        holder.txtPhoneNo.setText(contact.getPhoneNo());

        Glide.with(holder.itemView.getContext())
                .load(contact.getImageUrl())
                .centerCrop()
                .override(100,100)
                .into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtId,txtFName,txtLName,txtPhoneNo;
        ImageView userImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId=itemView.findViewById(R.id.txt_id);
            txtFName=itemView.findViewById(R.id.txt_fn);
            txtLName=itemView.findViewById(R.id.txt_ln);
            txtPhoneNo=itemView.findViewById(R.id.txt_pn);
            userImage=itemView.findViewById(R.id.image_user);

        }
    }
}

