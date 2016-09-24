package zeroh729.com.kitestring.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Feed implements Parcelable{
    private String title;
    private String blurb;
    private String description;
    private String imageUrl;
    private long timestamp;

    protected Feed(Parcel in) {
        title = in.readString();
        blurb = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        timestamp = in.readLong();
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(blurb);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeLong(timestamp);
    }
}
