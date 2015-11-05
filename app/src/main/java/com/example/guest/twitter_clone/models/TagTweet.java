package com.example.guest.twitter_clone.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Guest on 11/5/15.
 */
@Table(name = "tag_tweets", id="_id")
public class TagTweet extends Model {

    @Column(name = "Tweet")
    public Tweet mTweet;

    @Column(name = "Tag")
    public Tag mTag;

    public TagTweet(Tag tag, Tweet tweet) {
        super();
        mTweet = tweet;
        mTag = tag;
    }
}
