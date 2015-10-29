package com.example.guest.twitter_clone.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guest.twitter_clone.R;
import com.example.guest.twitter_clone.models.Tweet;

import java.util.ArrayList;

/**
 * Created by Guest on 10/29/15.
 */
public class TweetAdaptor extends BaseAdapter {
    private Context mContext;
    private ArrayList<Tweet> mTweets;

    public TweetAdaptor(Context context, ArrayList<Tweet> tweets) {
        mTweets = tweets;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mTweets.size();
    }

    @Override
    public Object getItem(int position) {
        return mTweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tweet_list_item, null);
            holder = new ViewHolder();
            holder.nameLabel = (TextView) convertView.findViewById(R.id.nameLabel);
            holder.dateLabel = (TextView) convertView.findViewById(R.id.dateLabel);
            holder.tweetLabel = (TextView) convertView.findViewById(R.id.tweetLabel);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Tweet tweet = mTweets.get(position);

        holder.dateLabel.setText(tweet.getFormattedTime());
        holder.nameLabel.setText("By: " + tweet.getUser().getName());
        holder.tweetLabel.setText(tweet.getContent());

        return convertView;
    }

    private static class ViewHolder {
        TextView nameLabel;
        TextView tweetLabel;
        TextView dateLabel;
    }
}
