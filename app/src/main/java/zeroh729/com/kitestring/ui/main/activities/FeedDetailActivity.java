package zeroh729.com.kitestring.ui.main.activities;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Random;

import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Comment;
import zeroh729.com.kitestring.data.model.Feed;
import zeroh729.com.kitestring.ui.base.BaseActivity;
import zeroh729.com.kitestring.ui.main.views.viewholders.CommentRow;
import zeroh729.com.kitestring.ui.main.views.viewholders.CommentRow_;

@EActivity(R.layout.activity_newsdetails)
public class FeedDetailActivity extends BaseActivity {
    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.iv_image)
    ImageView iv_image;

    @ViewById(R.id.tv_title)
    TextView tv_title;

    @ViewById(R.id.tv_article)
    TextView tv_article;

    @ViewById(R.id.rv_feeds)
    LinearLayout rv_feeds;

    @ViewById(R.id.et_message)
    EditText et_message;

    @ViewById(R.id.btn_send)
    ImageButton btn_send;

    @Extra("feed")
    Feed feed;

    ArrayList<Comment> commentArrayList;

    @AfterViews
    public void afterviews(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(feed.getTitle());
        }
        if(feed.getImageUrl().isEmpty()){
            iv_image.setVisibility(View.GONE);
        }else{
            Glide.with(this).load(feed.getImageUrl()).into(iv_image);
        }
        tv_title.setText(feed.getTitle());
        tv_article.setText(feed.getDescription());


        PopulateCommentList();
        for(int i=0; i<commentArrayList.size(); i++){
            CommentRow row = CommentRow_.build(this);
            row.bind(commentArrayList.get(i));
            rv_feeds.addView(row);
        }

    }

    private void PopulateCommentList(){
        commentArrayList = new ArrayList<>();

        Comment comment = new Comment();
        comment.setComment("I can't believe that there are still people living in the middle-ages!");
        comment.setScreenName("Tasha Grey");
        comment.setHex(getRandomColors());
        commentArrayList.add(comment);

        comment = new Comment();
        comment.setComment("God created Adam and Eve, not Adam and Steve");
        comment.setScreenName("Mary Schuyler");
        comment.setHex(getRandomColors());
        commentArrayList.add(comment);

        comment = new Comment();
        comment.setComment("Not all people believe in the same God you know.");
        comment.setScreenName("Victor Hamilton");
        comment.setHex(getRandomColors());
        commentArrayList.add(comment);

        comment = new Comment();
        comment.setComment("We hold these truth to be self-evident, that all men are created equal.");
        comment.setScreenName("Angelica Church");
        comment.setHex(getRandomColors());
        commentArrayList.add(comment);
    }

    private String getRandomColors(){
        Random random = new Random();
        int number = random.nextInt(5) + 1;
        switch(number){
            case 1: return "#ef476f";
            case 2: return "#ffd166";
            case 3: return "#06D6A0";
            case 4: return "#118AB2";
            case 5: return "#073b4c";
            default: return "#ef476f";
        }
    }
}
