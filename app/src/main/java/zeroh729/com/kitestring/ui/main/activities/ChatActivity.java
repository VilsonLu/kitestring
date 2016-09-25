package zeroh729.com.kitestring.ui.main.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;

import zeroh729.com.kitestring.Constants;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Chatroom;
import zeroh729.com.kitestring.data.model.Message;
import zeroh729.com.kitestring.ui.base.BaseActivity;
import zeroh729.com.kitestring.ui.main.adapters.ChatMessageAdapter;

@EActivity(R.layout.activity_chat)
public class ChatActivity extends BaseActivity{
    ArrayList<Message> messages = new ArrayList<>();

    @ViewById(R.id.tv_question)
    TextView tv_question;

    @ViewById(R.id.rv_feeds)
    RecyclerView rv_feeds;

    @ViewById(R.id.et_message)
    EditText et_message;

    @ViewById(R.id.btn_send)
    ImageButton btn_send;

    @Bean
    ChatMessageAdapter adapter;

    @Extra("chatroom")
    Chatroom chatroom;

    DatabaseReference ref;

    @AfterViews
    public void afterviews(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(chatroom.getFriendName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ref = FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_CHATS).child(chatroom.getId()).child(Constants.CHILD_MESSAGES);

        tv_question.setText(chatroom.getTopic());

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(Constants.KEY_ID, "0");
                map.put(Constants.KEY_NAME, "Joyce");
                map.put(Constants.KEY_HEX, "#f00");
                map.put(Constants.KEY_MESSAGE, et_message.getText().toString());
                ref.push().setValue(map);
                et_message.setText("");
            }
        });

        adapter.setItems(messages);
        rv_feeds.setAdapter(adapter);
        rv_feeds.setLayoutManager(new LinearLayoutManager(this));

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap map = (HashMap) dataSnapshot.getValue();
                Message message = new Message();
                message.setId(dataSnapshot.getKey());
                message.setScreenName((String)map.get(Constants.KEY_NAME));
                message.setHex((String)map.get(Constants.KEY_HEX));
                message.setMessage((String)map.get(Constants.KEY_MESSAGE));
                messages.add(message);
                adapter.notifyItemInserted(messages.size());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
