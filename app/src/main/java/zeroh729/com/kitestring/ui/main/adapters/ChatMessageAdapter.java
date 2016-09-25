package zeroh729.com.kitestring.ui.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.WindowManager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import zeroh729.com.kitestring.data.model.Message;
import zeroh729.com.kitestring.ui.base.BaseAdapterRecyclerView;
import zeroh729.com.kitestring.ui.base.ViewWrapper;
import zeroh729.com.kitestring.ui.main.views.viewholders.ChatItemRow;
import zeroh729.com.kitestring.ui.main.views.viewholders.ChatMessageRow;
import zeroh729.com.kitestring.ui.main.views.viewholders.ChatMessageRow_;

@EBean
public class ChatMessageAdapter  extends BaseAdapterRecyclerView<Message, ChatMessageRow> {
    @RootContext
    Context context;

    @Override
    protected ChatMessageRow onCreateItemView(ViewGroup parent, int viewType) {
        return ChatMessageRow_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ChatMessageRow> holder, int position) {
        ChatMessageRow view = holder.getView();
        Message message = items.get(position);
        view.bind(message);
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.MATCH_PARENT));
    }
}
