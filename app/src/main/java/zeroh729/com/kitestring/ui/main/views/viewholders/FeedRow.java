package zeroh729.com.kitestring.ui.main.views.viewholders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Feed;
import zeroh729.com.kitestring.ui.main.adapters.ClickListener;
import zeroh729.com.kitestring.ui.main.adapters.FeedClickListener;

@EViewGroup(R.layout.row_feeds)
public class FeedRow extends CardView {
    @ViewById(R.id.parent_view)
    CardView parent_view;

    @ViewById(R.id.iv_image)
    ImageView iv_image;

    @ViewById(R.id.tv_title)
    TextView tv_title;

    @ViewById(R.id.tv_blurb)
    TextView tv_blurb;

    @ViewById(R.id.btn_view_discusson)
    Button btn_view_discusson;

    public FeedRow(Context context) {
        super(context);
    }

    public FeedRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FeedRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(final Feed feed, final FeedClickListener listener){
        Glide.with(getContext()).load(feed.getImageUrl()).into(iv_image);
        tv_title.setText(feed.getTitle());
        tv_blurb.setText(feed.getDescription());
        btn_view_discusson.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(feed);
            }
        });
    }

    public CardView getParent_view() {
        return parent_view;
    }

    public ImageView getIv_image() {
        return iv_image;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public TextView getTv_blurb() {
        return tv_blurb;
    }

    public Button getBtn_view_discusson() {
        return btn_view_discusson;
    }
}
