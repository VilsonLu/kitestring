package zeroh729.com.kitestring.ui.main.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.ui.base.BaseActivity;
import zeroh729.com.kitestring.utils._;

@EActivity(R.layout.activity_login_registration)
public class LoginActivity extends BaseActivity {
    @ViewById(R.id.iv_logo)
    ImageView iv_logo;

    @ViewById(R.id.et_email)
    EditText et_email;

    @ViewById(R.id.et_password)
    EditText et_password;

    @ViewById(R.id.btn_login_register)
    ImageButton btn_login_register;

    @ViewById(R.id.tv_switch)
    TextView tv_switch;

    Boolean isLogin = true;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    if(isLogin){
                        MainActivity_.intent(LoginActivity.this).start();
                    } else {
                        RegistrationFormActivity_.intent(LoginActivity.this).start();
                    }

                } else {

                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

    @AfterViews
    public void afterviews(){

        tv_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin){
                    isLogin = false;
                    btn_login_register.setImageResource(R.drawable.btn_signin);
                    tv_switch.setText("Already have an account?");
                } else {
                    isLogin = true;
                    btn_login_register.setImageResource(R.drawable.register);
                    tv_switch.setText("Don't have an account yet?");
                }

            }
        });

        btn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin){
                    loginUser();
                } else {
                    registerUser();
                }
            }
        });


    }


    private Boolean isFieldEmpty(String username, String password){
        if(username.isEmpty() || password.isEmpty()){
            return true;
        }

        return false;
    }

    public void loginUser(){

        String username = et_email.getText().toString();
        String password = et_password.getText().toString();


        if(isFieldEmpty(username, password)){
            _.showToast("Username/Password cannot be empty");
            return;
        }

        mAuth.signInWithEmailAndPassword(et_email.getText().toString(), et_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                _.showToast(task.isSuccessful() + "");
            }
        });
    }

    public void registerUser(){

        String username = et_email.getText().toString();
        String password = et_password.getText().toString();

        if(isFieldEmpty(username, password)){
            _.showToast("Username/Password cannot be empty");
            return;
        }

        mAuth.createUserWithEmailAndPassword(et_email.getText().toString(), et_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                _.showToast(task.isSuccessful() + "");
            }

        });
    }
}
