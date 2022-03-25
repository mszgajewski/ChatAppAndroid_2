package com.mszgajewski.chatappandroid_2.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mszgajewski.chatappandroid_2.databinding.ItemContainerRecentConversionBinding;
import com.mszgajewski.chatappandroid_2.databinding.ItemContainerUserBinding;
import com.mszgajewski.chatappandroid_2.listeners.UserListener;
import com.mszgajewski.chatappandroid_2.models.User;

import java.util.List;

public class RecentConversationsAdapter extends RecyclerView.Adapter<RecentConversationsAdapter.UserViewHolder> {

    private final List<User> users;
    private final UserListener userListener;

    public RecentConversationsAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerRecentConversionBinding itemContainerRecentConversionBinding = ItemContainerRecentConversionBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent, false);

        return new ConversationViewHolder(itemContainerRecentConversionBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        holder.setConversationData(users.get(position  ));
    }

    @Override
    public int getItemCount() { return users.size(); }

    class ConversationViewHolder extends RecyclerView.ViewHolder {
        ItemContainerRecentConversionBinding binding;

         ConversationViewHolder(ItemContainerRecentConversionBinding itemContainerRecentConversionBinding) {
            super(itemContainerRecentConversionBinding.getRoot());
            binding = itemContainerRecentConversionBinding;
        }

        void setConversationData(User user) {
             binding.textName.setText(user.name);
             binding.textEmail.setText(user.email);
             binding.imageProfile.setImageBitmap(getConversationImage(user.image));
             binding.getRoot().setOnClickListener(v -> userListener.onUserClicked(user));
        }
    }

    private Bitmap getConversationImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0, bytes.length);
    }
}
