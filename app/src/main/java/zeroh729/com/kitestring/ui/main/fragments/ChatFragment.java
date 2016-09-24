package zeroh729.com.kitestring.ui.main.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Chatroom;
import zeroh729.com.kitestring.ui.main.activities.ChatActivity;
import zeroh729.com.kitestring.ui.main.activities.ChatActivity_;

@EFragment(R.layout.fragment_chatlist)
public class ChatFragment extends Fragment{
    ArrayList<Chatroom> chatrooms = new ArrayList<>();

    @ViewById(R.id.rv_chats)
    RecyclerView rv_chats;

    @ViewById(R.id.iv_instruction)
    ImageView iv_instruction;

    @ViewById(R.id.btn_add)
    FloatingActionButton btn_add;

    @AfterViews
    public void afterviews(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatActivity_.intent(getContext()).extra("chatroom", chatrooms.get(0)).start();
            }
        });
    }
}
