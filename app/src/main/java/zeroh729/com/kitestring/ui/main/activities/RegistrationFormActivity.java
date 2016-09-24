package zeroh729.com.kitestring.ui.main.activities;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.ui.base.BaseActivity;

@EActivity(R.layout.activity_registrationform)
public class RegistrationFormActivity extends BaseActivity{

    @ViewById(R.id.et_name)
    EditText et_name;

    @ViewById(R.id.et_age)
    EditText et_age;

    @ViewById(R.id.sp_race)
    Spinner sp_race;

    @ViewById(R.id.sp_sex)
    Spinner sp_sex;

    @ViewById(R.id.sp_sexuality)
    Spinner sp_sexuality;

    @ViewById(R.id.sp_nationality)
    Spinner sp_nationality;

    @ViewById(R.id.sp_religion)
    Spinner sp_religion;

    @ViewById(R.id.btn_done)
    Button btn_done;

    @AfterViews
    public void afterviews(){

    }

}
