package zeroh729.com.kitestring.ui.main.views.viewholders;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Chatroom;
import zeroh729.com.kitestring.data.model.Message;

@EViewGroup(R.layout.row_chat_item)
public class ChatItemRow extends RelativeLayout{

    @ViewById(R.id.iv_profilepic)
    CircleImageView iv_profilepic;

    @ViewById(R.id.tv_profilepic)
    TextView tv_profilepic;

    @ViewById(R.id.tv_username)
    TextView tv_username;

    @ViewById(R.id.tv_message)
    TextView tv_message;

    public ChatItemRow(Context context) {
        super(context);
    }

    public ChatItemRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatItemRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(Message message){
        iv_profilepic.setColorFilter(Color.parseColor(message.getHex()));
        tv_profilepic.setText(message.getScreenName().substring(0,1));
        tv_username.setText(message.getScreenName());
        tv_message.setText(message.getMessage());
    }

    public CircleImageView getIv_profilepic() {
        return iv_profilepic;
    }

    public void setIv_profilepic(CircleImageView iv_profilepic) {
        this.iv_profilepic = iv_profilepic;
    }

    public TextView getTv_profilepic() {
        return tv_profilepic;
    }

    public void setTv_profilepic(TextView tv_profilepic) {
        this.tv_profilepic = tv_profilepic;
    }

    public TextView getTv_username() {
        return tv_username;
    }

    public void setTv_username(TextView tv_username) {
        this.tv_username = tv_username;
    }

    public TextView getTv_message() {
        return tv_message;
    }

    public void setTv_message(TextView tv_message) {
        this.tv_message = tv_message;
    }
}
