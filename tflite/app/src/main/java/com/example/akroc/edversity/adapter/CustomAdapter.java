package com.example.akroc.edversity.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.akroc.edversity.LearnRhymes;
import com.example.akroc.edversity.R;
import com.example.akroc.edversity.models.VideoDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class CustomAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<VideoDetails> videoDetailsArrayList;
    LayoutInflater layoutInflater;

    public CustomAdapter(Activity activity, ArrayList<VideoDetails> videoDetailsArrayList)
    {
        this.activity=activity;
        this.videoDetailsArrayList=videoDetailsArrayList;

    }

    @Override
    public int getCount() {
        return this.videoDetailsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.videoDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater==null)
        {
            layoutInflater= this.activity.getLayoutInflater();
        }
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.custom_item,null);
        }
        ImageView img= (ImageView) convertView.findViewById(R.id.Thumbnail);
        TextView text= (TextView) convertView.findViewById(R.id.vidtitle);
        final VideoDetails videoDetails= (VideoDetails) this.videoDetailsArrayList.get(position);
        LinearLayout linearLayout= (LinearLayout) convertView.findViewById(R.id.rootele);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, LearnRhymes.class);
                String xo=videoDetails.getVideoId();
                intent.putExtra("VideoId",xo);
                activity.startActivity(intent);

            }
        });
        Picasso.get().load(videoDetails.getUrl()).into(img);
        text.setText(videoDetails.getTitle());
        return convertView;
    }

    public void update(ArrayList<VideoDetails> result) {
        videoDetailsArrayList=new ArrayList<VideoDetails>();
        videoDetailsArrayList.addAll(result);
        int x=0;
        for(VideoDetails res: videoDetailsArrayList){
            x++;
        }

        notifyDataSetChanged();
    }
}
