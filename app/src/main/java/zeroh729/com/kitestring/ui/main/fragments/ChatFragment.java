package zeroh729.com.kitestring.ui.main.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;

@EFragment(R.layout.fragment_chatlist)
public class ChatFragment extends Fragment{
    @ViewById(R.id.rv_chats)
    RecyclerView rv_chats;

    @ViewById(R.id.iv_instruction)
    ImageView iv_instruction;

    @ViewById(R.id.btn_add)
    Button btn_add;

    @AfterViews
    public void afterviews(){

    }
}
