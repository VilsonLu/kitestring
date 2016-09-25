package zeroh729.com.kitestring.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Chatroom implements Parcelable{
    private String id;
    private String friendName;
    private String topic;
    private ArrayList<String> characteristics;
    public String messageId;

    public Chatroom() {
    }

    protected Chatroom(Parcel in) {
        id = in.readString();
        friendName = in.readString();
        topic = in.readString();
        characteristics = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(friendName);
        dest.writeString(topic);
        dest.writeStringList(characteristics);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Chatroom> CREATOR = new Creator<Chatroom>() {
        @Override
        public Chatroom createFromParcel(Parcel in) {
            return new Chatroom(in);
        }

        @Override
        public Chatroom[] newArray(int size) {
            return new Chatroom[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(ArrayList<String> characteristics) {
        this.characteristics = characteristics;
    }
}
