package zeroh729.com.kitestring.ui.main.views.viewholders;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;

@EViewGroup(R.layout.row_headline)
public class HeadlineRow extends LinearLayout {
    @ViewById(R.id.tv_headline)
    TextView tv_headline;

    public HeadlineRow(Context context) {
        super(context);
    }

    public HeadlineRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadlineRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(String title){
        tv_headline.setText(title);
    }
}
