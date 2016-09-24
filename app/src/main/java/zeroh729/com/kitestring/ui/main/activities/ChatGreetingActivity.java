package zeroh729.com.kitestring.ui.main.activities;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import zeroh729.com.kitestring.Constants;
import zeroh729.com.kitestring.R;
import zeroh729.com.kitestring.data.model.Chatroom;
import zeroh729.com.kitestring.data.model.User;
import zeroh729.com.kitestring.ui.base.BaseActivity;
import zeroh729.com.kitestring.utils._;

@EActivity(R.layout.activity_chat_greetings)
public class ChatGreetingActivity extends BaseActivity {
    @ViewById(R.id.tv_greeting)
    TextView tv_greeting;

    @ViewById(R.id.et_question)
    EditText et_question;

    @ViewById(R.id.btn_done)
    FloatingActionButton btn_done;

    User randomUser;
    int completeCount;
    int successCount;

    private Chatroom chatroom;

    ArrayList<String> userAttrbs = new ArrayList<>();
    String[] attributes = {Constants.KEY_AGE, Constants.KEY_RACE, Constants.KEY_SEX, Constants.KEY_SEXUALITY, Constants.KEY_NATIONALITY, Constants.KEY_RELIGION};

    @AfterViews
    public void afterviews(){
        FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_USER).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = (int)dataSnapshot.getChildrenCount();
                int random = new Random().nextInt(size);

                int i = 0;


                for(DataSnapshot value : dataSnapshot.getChildren()){
                    if(i == random){
                        HashMap map = (HashMap) value.getValue();
                        randomUser = new User();
                        randomUser.setId(value.getKey());
                        randomUser.setUsername((String)map.get(Constants.KEY_NAME));
                        randomUser.setHex((String)map.get(Constants.KEY_HEX));
                        randomUser.setAge((int)(long)map.get(Constants.KEY_AGE));
                        randomUser.setNationality((String)map.get(Constants.KEY_NATIONALITY));
                        randomUser.setRace((String)map.get(Constants.KEY_RACE));
                        randomUser.setReligion((String)map.get(Constants.KEY_RELIGION));
                        randomUser.setSex((String)map.get(Constants.KEY_SEX));
                        randomUser.setSexuality((String)map.get(Constants.KEY_SEXUALITY));
                        break;
                    }
                    i++;
                }

                for(int j = 0; j < 3; j++){
                    int random1 = new Random().nextInt(attributes.length);
                    if(!userAttrbs.contains(attributes[random1])){
                        userAttrbs.add(attributes[random1]);
                    }else{
                        j--;
                    }
                }
                tv_greeting.setText("What do you think of " + randomUser.getUsername() + " :" +userAttrbs.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeCount = 0;
                successCount = 0;

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_CHATS).push();

                FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_USER).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Constants.KEY_CHATROOMS).child(ref.getKey()).setValue(true);
                FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_USER).child(randomUser.getId()).child(Constants.KEY_CHATROOMS).child(ref.getKey()).setValue(false);


                chatroom = new Chatroom();
                chatroom.setId(ref.getKey());
                chatroom.setFriendId(randomUser.getId());
                chatroom.setCharacteristics(userAttrbs);
                chatroom.setTopic(et_question.getText().toString());

                HashMap memberMap = new HashMap();
                memberMap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), true);
                memberMap.put(randomUser.getId(), false);

                ref.child(Constants.CHILD_MEMBERS).setValue(memberMap).addOnCompleteListener(listener);

                ref.child(Constants.KEY_CHARACTERISTICS).setValue(userAttrbs).addOnCompleteListener(listener);
                ref.child(Constants.KEY_DESCRIPTION).setValue(et_question.getText().toString()).addOnCompleteListener(listener);
            }
        });
    }


    OnCompleteListener listener = new OnCompleteListener() {
        @Override
        public void onComplete(@NonNull Task task) {
            completeCount++;
            if(task.isSuccessful()){
                successCount++;
            }

            if(completeCount == 3){
                if(successCount == 3){
                    ChatActivity_.intent(ChatGreetingActivity.this).extra("chatroom",chatroom).start();
                }else{
                    _.showToast("Something went wrong, please try again.");
                }
            }
        }
    };
}
