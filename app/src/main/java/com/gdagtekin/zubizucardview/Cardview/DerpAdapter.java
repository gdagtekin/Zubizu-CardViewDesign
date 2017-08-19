package com.gdagtekin.zubizucardview.Cardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.gdagtekin.zubizucardview.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;


public class DerpAdapter extends RecyclerView.Adapter<DerpAdapter.DerpHolder> {

    private List<ListItem> listData;
    private LayoutInflater inflater;
    private ItemClickCallback itemClickCallback;

    //Tıklanma için interface oluşturdum
    public interface ItemClickCallback {
        void onItemClick(int p);

        void onFollowIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public DerpAdapter(List<ListItem> listData, Context c) {
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public DerpAdapter.DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_item, parent, false);
        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {
        ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.text.setText(item.getShortText());
        if(item.isFollow()){
            holder.btTakip.setBackgroundResource(R.drawable.ic_star_black_24dp);
        }else{
            holder.btTakip.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
        }

        ImageLoader.getInstance().displayImage(item.getImage()
                ,holder.image);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setListData(ArrayList<ListItem> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }


    class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView text;
        private ImageView image;
        private Button btTakip;
        private View container;

        public DerpHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tvTitle);
            text = (TextView) itemView.findViewById(R.id.tvText);
            image = (ImageView) itemView.findViewById(R.id.ivImage);
            DisplayMetrics metrics = itemView.getResources().getDisplayMetrics();
            Log.i("INFO", String.valueOf(metrics));
            Log.i("INFO", String.valueOf(metrics.widthPixels * 0.5625));
            int height = (int) (metrics.widthPixels * 0.5625);
            image.getLayoutParams().height = height;


            DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
            ImageLoaderConfiguration imageLoaderConfiguration=new ImageLoaderConfiguration.Builder(itemView.getContext())
                    .defaultDisplayImageOptions(displayImageOptions)
                    .build();
            ImageLoader.getInstance().init(imageLoaderConfiguration);


            btTakip = (Button) itemView.findViewById(R.id.btTakipEt);
            btTakip.setOnClickListener(this);
            container = itemView.findViewById(R.id.cont_item);
            container.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cont_item) {
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.onFollowIconClick(getAdapterPosition());
            }
        }
    }


}

