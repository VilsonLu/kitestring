package zeroh729.com.kitestring.ui.main.adapters;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import zeroh729.com.kitestring.data.model.Comment;
import zeroh729.com.kitestring.ui.base.BaseAdapterRecyclerView;
import zeroh729.com.kitestring.ui.base.ViewWrapper;
import zeroh729.com.kitestring.ui.main.views.viewholders.ChatMessageRow_;
import zeroh729.com.kitestring.ui.main.views.viewholders.CommentRow;
import zeroh729.com.kitestring.ui.main.views.viewholders.CommentRow_;

@EBean
public class CommentAdapter extends BaseAdapterRecyclerView<Comment, CommentRow> {
    @RootContext
    Context context;

    @Override
    protected CommentRow onCreateItemView(ViewGroup parent, int viewType) {
        return CommentRow_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<CommentRow> holder, int position) {
        CommentRow view = holder.getView();
        Comment comment = items.get(position);
        view.bind(comment);
    }
}
