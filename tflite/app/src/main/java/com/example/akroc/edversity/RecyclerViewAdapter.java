package com.example.akroc.edversity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Feature> mData ;


    public RecyclerViewAdapter(Context mContext, List<Feature> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_feature,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.feature_title.setText(mData.get(position).getTitle());
        holder.feature_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mData.get(position).getTitle().equals("Learn Numbers and Alphabet"))
                {
                    mContext.startActivity(new Intent(mContext,LearnAlpha.class));
                }
                else if (mData.get(position).getTitle().equals("Learn Shapes"))
                {
                    mContext.startActivity(new Intent(mContext,LearnShapes.class));
                }
                else if (mData.get(position).getTitle().equals("Learn Pronunciation"))
                {
                    mContext.startActivity(new Intent(mContext,LearnPronunciation.class));
                }
                else if (mData.get(position).getTitle().equals("Learn Colours"))
                {
                    mContext.startActivity(new Intent(mContext,LearnColors.class));
                }
                else if (mData.get(position).getTitle().equals("Learn Fruits and Vegetables"))
                {
                    mContext.startActivity(new Intent(mContext,LearnFruandVeg.class));
                }
                else if (mData.get(position).getTitle().equals("Learn Rhymes"))
                {
                    mContext.startActivity(new Intent(mContext,RhymesList.class));
                }
                else if (mData.get(position).getTitle().equals("Learn Animal Sounds"))
                {
                    mContext.startActivity(new Intent(mContext,LearnAnisounds.class));
                }
                else if (mData.get(position).getTitle().equals("Recognizing Live Objects"))
                {
                    mContext.startActivity(new Intent(mContext,CameraActivity.class));
                }
                else if (mData.get(position).getTitle().equals("Matching Like Objects"))
                {
                    mContext.startActivity(new Intent(mContext,MatchingLikeObj.class));
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView feature_title;
        CircleImageView feature_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            feature_title = (TextView) itemView.findViewById(R.id.ftitle) ;
            feature_thumbnail = (CircleImageView) itemView.findViewById(R.id.fimage);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}