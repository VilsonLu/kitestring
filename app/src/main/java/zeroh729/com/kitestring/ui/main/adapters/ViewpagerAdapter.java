package zeroh729.com.kitestring.ui.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zeroh729.com.kitestring.ui.main.fragments.ChatFragment;
import zeroh729.com.kitestring.ui.main.fragments.ChatFragment_;
import zeroh729.com.kitestring.ui.main.fragments.FeedDetailsFragment;
import zeroh729.com.kitestring.ui.main.fragments.FeedDetailsFragment_;

public class ViewpagerAdapter extends FragmentPagerAdapter{
    ChatFragment chatFragment;
    FeedDetailsFragment feedDetailsFragment;

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(chatFragment == null)
            chatFragment = ChatFragment_.builder().build();
        if(feedDetailsFragment == null)
            feedDetailsFragment = FeedDetailsFragment_.builder().build();
        switch(position){
            case 0:
                return chatFragment;
            case 1:
            default:
                return feedDetailsFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
