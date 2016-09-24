package zeroh729.com.kitestring.ui.main.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Random;

import zeroh729.com.kitestring.Constants;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.ui.base.BaseActivity;

@EActivity(R.layout.activity_registrationform)
public class RegistrationFormActivity extends BaseActivity {

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
    FloatingActionButton btn_done;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @AfterViews
    public void afterviews() {

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser = mAuth.getCurrentUser();
                String userId = mUser.getUid();

                mDatabase.child("users").child(userId).setValue(retrieveFields());

                MainActivity_.intent(RegistrationFormActivity.this).start();
            }
        });
    }


    private HashMap<String, String> retrieveFields(){
        HashMap<String, String> fields = new HashMap<>();

        fields.put(Constants.KEY_NAME, et_name.getText().toString());
        fields.put(Constants.KEY_AGE, et_name.getText().toString());
        fields.put(Constants.KEY_NATIONALITY, sp_nationality.getSelectedItem().toString());
        fields.put(Constants.KEY_SEX, sp_sex.getSelectedItem().toString());
        fields.put(Constants.KEY_SEXUALITY, sp_sexuality.getSelectedItem().toString());
        fields.put(Constants.KEY_RACE, sp_sexuality.getSelectedItem().toString());
        fields.put(Constants.KEY_RELIGION, sp_religion.getSelectedItem().toString());
        fields.put(Constants.KEY_HEX, getRandomColors());

        return fields;
    }

    private String getRandomColors(){
        Random random = new Random();
        int number = random.nextInt(5) + 1;
        switch(number){
            case 1: return "#ef476f";
            case 2: return "#ffd166";
            case 3: return "#06D6A0";
            case 4: return "#118AB2";
            case 5: return "#073b4c";
            default: return "#ef476f";
        }
    }


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("RegistrationForm Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
