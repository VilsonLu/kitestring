package zeroh729.com.kitestring.ui.main.activities;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;

@EActivity(R.layout.activity_login_registration)
public class LoginActivity extends Activity {
    @ViewById(R.id.iv_logo)
    ImageView iv_logo;

    @ViewById(R.id.et_email)
    EditText et_email;

    @ViewById(R.id.et_password)
    EditText et_password;

    @ViewById(R.id.btn_login_register)
    Button btn_login_register;

    @ViewById(R.id.tv_switch)
    TextView tv_switch;

    @AfterViews
    public void afterviews(){

    }
}
