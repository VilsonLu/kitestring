package zeroh729.com.kitestring.ui.main.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.ui.main.activities.AddFeedActivity_;
import zeroh729.com.kitestring.ui.main.activities.FeedDetailActivity;
import zeroh729.com.kitestring.ui.main.activities.FeedDetailActivity_;

@EFragment(R.layout.fragment_feeds)
public class FeedDetailsFragment extends Fragment {
    @ViewById(R.id.rv_feeds)
    RecyclerView rv_feeds;

    @ViewById(R.id.btn_add)
    FloatingActionButton btn_add;

    @AfterViews
    public void afterviews(){
        rv_feeds.setLayoutManager(new LinearLayoutManager(getContext()));

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFeedActivity_.intent(getContext()).start();
            }
        });

    }
}
