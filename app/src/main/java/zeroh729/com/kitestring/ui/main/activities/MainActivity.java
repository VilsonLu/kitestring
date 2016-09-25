package zeroh729.com.kitestring.ui.main.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import zeroh729.com.kitestring.Constants;
import zeroh729.com.kitestring.KiteString;
import zeroh729.com.kitestring.KiteString_;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.presenters.DataListPresenter;
import zeroh729.com.kitestring.ui.base.BaseActivity;
import zeroh729.com.kitestring.ui.main.adapters.ViewpagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements DataListPresenter.Screen{
    @ViewById(R.id.tablayout)
    TabLayout tabLayout;

    @ViewById(R.id.view_pager)
    ViewPager viewpager;

    ViewpagerAdapter adapter;

    private int[] tabIcons;
//    = {
//            R.drawable.ic_tab_favourite,
//            R.drawable.ic_tab_call,
//            R.drawable.ic_tab_contacts
//    };

    @AfterViews
    public void afterViews(){
        adapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        setupTabIcons();

        FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_USER).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap map = (HashMap)dataSnapshot.getValue();
                KiteString_.getInstance().user.setUsername((String)map.get(Constants.KEY_NAME));
                KiteString_.getInstance().user.setHex((String)map.get(Constants.KEY_HEX));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

}
