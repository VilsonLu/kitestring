package zeroh729.com.kitestring.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class Chatroom implements Parcelable{
    private String id;
    private String friendId;
    private String topic;
    private String[] characteristics;

    protected Chatroom(Parcel in) {
        id = in.readString();
        friendId = in.readString();
        topic = in.readString();
        characteristics = in.createStringArray();
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

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String[] getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String[] characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(friendId);
        dest.writeString(topic);
        dest.writeStringArray(characteristics);
    }
}
