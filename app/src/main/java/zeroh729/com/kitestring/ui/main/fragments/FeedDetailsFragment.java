package zeroh729.com.kitestring.ui.main.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;

import zeroh729.com.kitestring.Constants;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Feed;
import zeroh729.com.kitestring.ui.main.activities.AddFeedActivity_;
import zeroh729.com.kitestring.ui.main.adapters.FeedAdapter;

@EFragment(R.layout.fragment_feeds)
public class FeedDetailsFragment extends Fragment {
    @ViewById(R.id.rv_feeds)
    RecyclerView rv_feeds;

    @ViewById(R.id.btn_add)
    FloatingActionButton btn_add;

    ArrayList<Feed> feeds = new ArrayList<>();

    @Bean
    FeedAdapter adapter;

    @AfterViews
    public void afterviews(){
        adapter.setItems(feeds);

        rv_feeds.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_feeds.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFeedActivity_.intent(getContext()).start();
            }
        });

        FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_FEEDS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap map = (HashMap) dataSnapshot.getValue();
                Feed feed = new Feed();
                feed.setBlurb((String)map.get(Constants.KEY_BLURB));
                feed.setDescription((String)map.get(Constants.KEY_DESCRIPTION));
                feed.setTitle((String)map.get(Constants.KEY_TITLE));
                if(map.containsKey(Constants.KEY_IMAGE))
                    feed.setImageUrl((String)map.get(Constants.KEY_IMAGE));
                else
                    feed.setImageUrl("");
                if(dataSnapshot.getKey().contains("id")) {
                    feeds.add(0,feed);
                }else{
                    feeds.add(feeds.size(), feed);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
