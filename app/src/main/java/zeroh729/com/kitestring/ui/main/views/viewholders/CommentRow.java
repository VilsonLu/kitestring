package zeroh729.com.kitestring.ui.main.views.viewholders;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Comment;

@EViewGroup(R.layout.row_comment)
public class CommentRow extends RelativeLayout{
    @ViewById(R.id.iv_profilepic)
    CircleImageView iv_profilepic;

    @ViewById(R.id.tv_profilepic)
    TextView tv_profilepic;

    @ViewById(R.id.tv_username)
    TextView tv_username;

    @ViewById(R.id.tv_comment)
    TextView tv_comment;

    public CommentRow(Context context) {
        super(context);
    }

    public CommentRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(Comment comment){
        iv_profilepic.setColorFilter(Color.parseColor(comment.getHex()));
        tv_profilepic.setText(comment.getScreenName().substring(0,1));
        tv_username.setText(comment.getScreenName());
        tv_comment.setText(comment.getComment());
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

    public TextView getTv_comment() {
        return tv_comment;
    }

    public void setTv_comment(TextView tv_comment) {
        this.tv_comment = tv_comment;
    }
}
