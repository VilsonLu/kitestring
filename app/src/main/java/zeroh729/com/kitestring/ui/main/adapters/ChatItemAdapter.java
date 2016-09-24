package zeroh729.com.kitestring.ui.main.adapters;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import zeroh729.com.kitestring.data.model.Message;
import zeroh729.com.kitestring.ui.base.BaseAdapterRecyclerView;
import zeroh729.com.kitestring.ui.base.ViewWrapper;
import zeroh729.com.kitestring.ui.main.views.viewholders.ChatItemRow;
import zeroh729.com.kitestring.ui.main.views.viewholders.ChatItemRow_;

@EBean
public class ChatItemAdapter extends BaseAdapterRecyclerView<Message, ChatItemRow>{
    @RootContext
    Context context;

    @Override
    protected ChatItemRow onCreateItemView(ViewGroup parent, int viewType) {
        return ChatItemRow_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ChatItemRow> holder, int position) {
        ChatItemRow view = holder.getView();
        Message message = items.get(position);

        view.bind(message);
    }
}