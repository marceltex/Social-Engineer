package za.co.social_engineer.www.socialengineer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * State class used to create state objects of each record in the State database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class State implements Parcelable {

    private String id;
    private String name;

    public State(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    protected State(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() {
        public State createFromParcel(Parcel source) {
            return new State(source);
        }

        public State[] newArray(int size) {
            return new State[size];
        }
    };
}
