package com.example.githubusers.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.githubusers.R;
import com.example.githubusers.model.GithubUsers;
import com.example.githubusers.view.DetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.UserViewHolder> {
    private final List<GithubUsers> users;
    /* package */ final Context context;


    public GithubAdapter(List<GithubUsers> githubUsersList, Context mContext) {
        this.users = githubUsersList;
        this.context = mContext;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        // Get data model based on position
        GithubUsers user = users.get(position);


        // Set item views based on respective model
        final String actualUserName = user.getUserName();
        final String atSignUsername = context.getResources().getString(R.string.at_sign_username, actualUserName);

        holder.userName.setText(atSignUsername);

        // Prepare image
        Glide.with(context)
                .asBitmap()
                .load(users.get(position).getAvatarUrl())
                .into(holder.userAvatar);

        // Add click listener
        View.OnClickListener userCardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Viewing profile for "+atSignUsername, Toast.LENGTH_SHORT).show();
                Intent viewDetailsIntent = new Intent(context, DetailActivity.class);
                viewDetailsIntent.putExtra("EXTRA_USERNAME", actualUserName);
                context.startActivity(viewDetailsIntent);
            }
        };

        holder.userCardView.setOnClickListener(userCardClickListener);
    }

    @NonNull
    @Override
    public GithubAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom row item layout
        View usersView = inflater.inflate(R.layout.recyclerview_row, parent, false);

        // Return new holder instance
        return new UserViewHolder(usersView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        CircleImageView userAvatar;
        TextView userName;
        CardView userCardView;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userAvatar = itemView.findViewById(R.id.ivUserAvatar);
            userName = itemView.findViewById(R.id.tvUserName);
            userCardView = itemView.findViewById(R.id.cvUsers);
        }
    }
}
