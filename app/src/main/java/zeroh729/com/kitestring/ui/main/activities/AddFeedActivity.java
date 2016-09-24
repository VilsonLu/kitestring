package zeroh729.com.kitestring.ui.main.activities;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;

@EActivity(R.layout.activity_add_feed)
public class AddFeedActivity extends Activity{
    @ViewById(R.id.et_content)
    EditText et_content;

    @ViewById(R.id.btn_send)
    FloatingActionButton btn_send;

    @AfterViews
    public void afterviews(){

    }

}
