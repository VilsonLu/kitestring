package zeroh729.com.kitestring.ui.main.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import zeroh729.com.kitestring.Constants;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Chatroom;
import zeroh729.com.kitestring.data.model.Message;
import zeroh729.com.kitestring.ui.main.activities.ChatActivity;
import zeroh729.com.kitestring.ui.main.activities.ChatActivity_;
import zeroh729.com.kitestring.ui.main.activities.ChatGreetingActivity_;
import zeroh729.com.kitestring.ui.main.adapters.ChatClickListener;
import zeroh729.com.kitestring.ui.main.adapters.ChatItemAdapter;

@EFragment(R.layout.fragment_chatlist)
public class ChatFragment extends Fragment{
    ArrayList<Chatroom> chatrooms = new ArrayList<>();

    @ViewById(R.id.rv_chats)
    RecyclerView rv_chats;

    @Bean
    ChatItemAdapter adapter;

    @ViewById(R.id.iv_instruction)
    ImageView iv_instruction;

    @ViewById(R.id.btn_add)
    FloatingActionButton btn_add;

    ArrayList<Chatroom> chatroomIds;

    ArrayList<Message> messages = new ArrayList<>();

    @AfterViews
    public void afterviews(){
        adapter.setItems(messages);
        adapter.clickListener = new ChatClickListener() {
            @Override
            public void onClick(Message message) {
                for(Chatroom room : chatroomIds){
                    if(room.getId().equals(message.getId())){
                        ChatActivity_.intent(getContext()).extra("chatroom", room).start();
                    }
                }
            }
        };

        rv_chats.setAdapter(adapter);
        rv_chats.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatGreetingActivity_.intent(getContext()).start();
            }
        });

        FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_CHATS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatroomIds = new ArrayList<Chatroom>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    HashMap map = (HashMap)snapshot.getValue();
                    HashMap userMap = (HashMap) map.get(Constants.KEY_MEMBERS);
                    if(userMap.containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        Chatroom chatroom = new Chatroom();
                        chatroom.setId(snapshot.getKey());
                        chatroom.setCharacteristics((ArrayList<String>)map.get(Constants.KEY_CHARACTERISTICS));
                        chatroom.setTopic((String)map.get(Constants.KEY_DESCRIPTION));
                        chatroom.setFriendName("");
                        chatroomIds.add(chatroom);
                    }
                }

                if(chatroomIds.size() > 0){
                    iv_instruction.setVisibility(View.GONE);
                }

                for(final Chatroom chatId : chatroomIds) {
                    FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_CHATS).child(chatId.getId()).child(Constants.CHILD_MESSAGES).limitToLast(1).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null) {
                                HashMap map = (HashMap) dataSnapshot.getValue();
                                Message message = new Message();
                                message.setId(chatId.getId());
                                message.setScreenName("Matthew");
                                message.setHex(getRandomColors());
                                message.setMessage((String) map.get(Constants.KEY_MESSAGE));
                                messages.add(message);
                                adapter.notifyDataSetChanged();
                            }else{
                                Message message = new Message();
                                message.setId(chatId.getId());
                                message.setScreenName("New message!");
                                message.setHex(getRandomColors());
                                message.setMessage(chatId.getTopic());
                                messages.add(message);
                                adapter.notifyDataSetChanged();
                            }
                            chatId.messageId = dataSnapshot.getKey();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
