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

import zeroh729.com.kitestring.Constants;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Chatroom;
import zeroh729.com.kitestring.data.model.Message;
import zeroh729.com.kitestring.ui.main.activities.ChatActivity;
import zeroh729.com.kitestring.ui.main.activities.ChatActivity_;
import zeroh729.com.kitestring.ui.main.activities.ChatGreetingActivity_;
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

    ArrayList<Message> messages = new ArrayList<>();

    @AfterViews
    public void afterviews(){
        adapter.setItems(messages);
        rv_chats.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatGreetingActivity_.intent(getContext()).start();
            }
        });


        FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_USER).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Constants.KEY_CHATROOMS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> chatroomIds = new ArrayList<String>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    chatroomIds.add(snapshot.getKey());
                }

                for(String chatId : chatroomIds) {
                    FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_CHATS).child(chatId).child(Constants.KEY_MESSAGE).limitToLast(1).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null) {
                                HashMap map = (HashMap) dataSnapshot.getValue();
                                Message message = new Message();
                                message.setId(dataSnapshot.getKey());
                                message.setScreenName((String) map.get(Constants.KEY_NAME));
                                message.setHex((String) map.get(Constants.KEY_HEX));
                                message.setMessage((String) map.get(Constants.KEY_MESSAGE));
                                messages.add(message);
                                adapter.notifyDataSetChanged();
                            }
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
}
