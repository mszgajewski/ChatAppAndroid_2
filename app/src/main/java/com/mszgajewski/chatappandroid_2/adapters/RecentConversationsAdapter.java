package com.mszgajewski.chatappandroid_2.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mszgajewski.chatappandroid_2.databinding.ItemContainerRecentConversionBinding;
import com.mszgajewski.chatappandroid_2.listeners.ConversionListener;
import com.mszgajewski.chatappandroid_2.models.ChatMessage;
import com.mszgajewski.chatappandroid_2.models.User;

import java.util.List;

public class RecentConversationsAdapter extends RecyclerView.Adapter<RecentConversationsAdapter.ConversionViewHolder> {

    private final List<ChatMessage> chatMessages;
    private final ConversionListener conversionListener;

    public RecentConversationsAdapter(List<ChatMessage> chatMessages, ConversionListener conversionListener) {
        this.chatMessages = chatMessages;
        this.conversionListener = conversionListener;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(ItemContainerRecentConversionBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        holder.setData(chatMessages.get(position));
    }

    @Override
    public int getItemCount() { return chatMessages.size(); }

    class ConversionViewHolder extends RecyclerView.ViewHolder {
        ItemContainerRecentConversionBinding binding;

         ConversionViewHolder(ItemContainerRecentConversionBinding itemContainerRecentConversionBinding) {
            super(itemContainerRecentConversionBinding.getRoot());
            binding = itemContainerRecentConversionBinding;
        }

        void setData(ChatMessage chatMessage) {
             binding.textName.setText(chatMessage.conversationName);
             binding.imageProfile.setImageBitmap(getConversionImage(chatMessage.conversationImage));
             binding.textRecentMessage.setText(chatMessage.message);
             binding.getRoot().setOnClickListener(v -> {
                 User user = new User();
                 user.id = chatMessage.conversationId;
                 user.name = chatMessage.conversationName;
                 user.image = chatMessage.conversationImage;
                 conversionListener.onConversionClicked(user);
             });
        }
    }

    private Bitmap getConversionImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0, bytes.length);
    }
}
