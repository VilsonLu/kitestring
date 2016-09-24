package zeroh729.com.kitestring.ui.main.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;

import zeroh729.com.kitestring.Constants;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.ui.base.BaseActivity;
import zeroh729.com.kitestring.utils._;

@EActivity(R.layout.activity_add_feed)
public class AddFeedActivity extends BaseActivity {
    @ViewById(R.id.et_title)
    EditText et_content;

    @ViewById(R.id.et_description)
    EditText et_description;

    @ViewById(R.id.btn_send)
    FloatingActionButton btn_send;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;

    @AfterViews
    public void afterviews(){
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser = mAuth.getCurrentUser();
                String userId = mUser.getUid();

                mDatabase.child("feeds").push().setValue(retrieveFields()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            _.showToast("Your question has been posted");
                            finish();
                        } else {
                            _.showToast("Please do not ask stupid ask question");
                        }
                    }
                });

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private HashMap<String, String> retrieveFields(){
        HashMap<String,String> fields = new HashMap<>();
        fields.put(Constants.KEY_TITLE, et_content.toString());
        fields.put(Constants.KEY_DESCRIPTION, et_description.toString());
        return fields;
    }

}
