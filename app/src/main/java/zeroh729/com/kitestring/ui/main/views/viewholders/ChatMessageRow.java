package zeroh729.com.kitestring.ui.main.views.viewholders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Message;

@EViewGroup(R.layout.row_chat_message)
public class ChatMessageRow extends LinearLayout{
    @ViewById(R.id.parent_view)
    LinearLayout parent_view;

    @ViewById(R.id.tv_message)
    TextView tv_message;

    public ChatMessageRow(Context context) {
        super(context);
    }

    public ChatMessageRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatMessageRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(Message message){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);

        if(message.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
            params.gravity = Gravity.RIGHT;
            parent_view.setBackgroundResource(R.drawable.bg_chat_user);
        }else{
            params.gravity = Gravity.RIGHT;
            parent_view.setBackgroundResource(R.drawable.bg_chat_other);
        }
        tv_message.setText(message.getMessage());
        parent_view.setLayoutParams(params);
    }
}
