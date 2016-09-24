package zeroh729.com.kitestring.ui.main.adapters;

import android.view.ViewGroup;

import zeroh729.com.kitestring.data.model.Message;
import zeroh729.com.kitestring.ui.base.BaseAdapterRecyclerView;
import zeroh729.com.kitestring.ui.base.ViewWrapper;
import zeroh729.com.kitestring.ui.main.views.viewholders.ChatItemRow;

public class ChatItemAdapter extends BaseAdapterRecyclerView<Message, ChatItemRow>{


    @Override
    protected ChatItemRow onCreateItemView(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ChatItemRow> holder, int position) {

    }
}
