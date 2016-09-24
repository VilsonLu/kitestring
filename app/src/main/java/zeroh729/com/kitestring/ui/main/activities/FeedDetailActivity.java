package zeroh729.com.kitestring.ui.main.activities;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;

@EActivity(R.layout.activity_newsdetails)
public class FeedDetailActivity extends Activity{
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
    Button btn_send;

    @AfterViews
    public void afterviews(){

    }
}
