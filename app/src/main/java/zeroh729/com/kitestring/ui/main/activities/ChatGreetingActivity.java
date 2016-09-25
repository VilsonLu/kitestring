package zeroh729.com.kitestring.ui.main.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
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
//                    if(i == random){
                    if(value.getKey().equals("VQ0VkM1icveXOtMt0K3NMhZBvEa2")){
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
                    String attr = "";
                    switch (random1){
                        case 0:
                            attr = randomUser.getAge() + " years old";
                            break;
                        case 1:
                            attr = randomUser.getNationality();
                            break;
                        case 2:
                            attr = randomUser.getRace();
                            break;
                        case 3:
                            attr = randomUser.getReligion();
                            break;
                        case 4:
                            attr = randomUser.getSex();
                            break;
                        default:
                            attr = randomUser.getSexuality();
                            break;
                    }
                    if(!userAttrbs.contains(attr)){
                        userAttrbs.add(attr);
                    }else{
                        j--;
                    }
                }
                tv_greeting.setText("Have you met a:" + userAttrbs.get(0).toString() + ",\n" + userAttrbs.get(1).toString() + ",\n" + userAttrbs.get(2).toString() + "?\n\n Don't worry, we'll be connecting you with " + randomUser.getUsername() + "!");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                completeCount = 0;
//                successCount = 0;
//

                ref = FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_CHATS).push();

                chatroom = new Chatroom();
                chatroom.setId(ref.getKey());
                chatroom.setFriendName(randomUser.getUsername());
                chatroom.setCharacteristics(userAttrbs);
                chatroom.setTopic(et_question.getText().toString());
                saveInFirebase();
                goToChatroom();


            }
        });

    }

    DatabaseReference ref;

    @Background
    public void saveInFirebase(){
//        DatabaseReference userref = FirebaseDatabase.getInstance().getReference().child(Constants.CHILD_USER);
//        userref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Constants.KEY_CHATROOMS).child(ref.getKey()).setValue(true);
//        userref.child(randomUser.getId()).child(Constants.KEY_CHATROOMS).child(ref.getKey()).setValue(false);

        DatabaseReference ref0 = ref.child(Constants.CHILD_MEMBERS);
        DatabaseReference ref1 = ref.child(Constants.KEY_CHARACTERISTICS);
        DatabaseReference ref2 = ref.child(Constants.KEY_DESCRIPTION);

        HashMap memberMap = new HashMap();
        memberMap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), true);
        memberMap.put(randomUser.getId(), false);

        ref2.setValue(et_question.getText().toString());
        ref1.setValue(userAttrbs);//.addOnCompleteListener(listener);
        ref0.setValue(memberMap);
    }

    private void goToChatroom(){
        ChatActivity_.intent(this).extra("chatroom", chatroom).start();
    }

//
//    OnCompleteListener listener = new OnCompleteListener() {
//        @Override
//        public void onComplete(@NonNull Task task) {
//            completeCount++;
//            if(task.isSuccessful()){
//                successCount++;
//            }
//
//            if(completeCount == 3){
//                if(successCount == 3){
//                }else{
//                    _.showToast("Something went wrong, please try again.");
//                }
//            }
//        }
//    };
}
