package zeroh729.com.kitestring.ui.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zeroh729.com.kitestring.ui.main.fragments.ChatFragment;
import zeroh729.com.kitestring.ui.main.fragments.FeedDetailsFragment;

public class ViewpagerAdapter extends FragmentPagerAdapter{
    ChatFragment chatFragment;
    FeedDetailsFragment feedDetailsFragment;

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
