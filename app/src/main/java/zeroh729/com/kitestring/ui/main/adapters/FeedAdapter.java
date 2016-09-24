package zeroh729.com.kitestring.ui.main.adapters;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import zeroh729.com.kitestring.data.model.Feed;
import zeroh729.com.kitestring.ui.base.BaseAdapterRecyclerView;
import zeroh729.com.kitestring.ui.base.ViewWrapper;
import zeroh729.com.kitestring.ui.main.activities.FeedDetailActivity_;
import zeroh729.com.kitestring.ui.main.views.viewholders.ChatMessageRow_;
import zeroh729.com.kitestring.ui.main.views.viewholders.FeedRow;
import zeroh729.com.kitestring.ui.main.views.viewholders.FeedRow_;

@EBean
public class FeedAdapter extends BaseAdapterRecyclerView<Feed, FeedRow>{
    @RootContext
    Context context;

    @Override
    protected FeedRow onCreateItemView(ViewGroup parent, int viewType) {
        return FeedRow_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<FeedRow> holder, int position) {
        FeedRow view = holder.getView();
        Feed feed = items.get(position);
        view.bind(feed, new FeedClickListener() {
            @Override
            public void onClick(Feed feed) {
                FeedDetailActivity_.intent(context).extra("feed", feed).start();
            }
        });
    }
}
