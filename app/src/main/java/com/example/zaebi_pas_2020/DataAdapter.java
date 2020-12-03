package com.example.zaebi_pas_2020;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.UserViewHolder> {

    public List<ItemModel> dataList;
    public List<ItemModel> dataListFull;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DataAdapter(List<ItemModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_data_adapter, parent, false);
        return new UserViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        holder.tv_listTitle.setText(dataList.get(position).getTeam_name());
        holder.tv_listDescription.setText("Formed At "+dataList.get(position).getFormed_year() + ", From " + dataList.get(position).getTeam_country());
        holder.img_listImage.setImageResource(R.drawable.ic_launcher_foreground);
        Picasso.get().load(dataList.get(position).getTeam_badge_URL())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img_listImage);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_listTitle;
        private TextView tv_listDescription;
        private ImageView img_listImage;

        public UserViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            tv_listTitle = itemView.findViewById(R.id.tv_listTitle);
            tv_listDescription = itemView.findViewById(R.id.tv_listDescriptionAdapter);
            img_listImage = itemView.findViewById(R.id.img_ImageIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}