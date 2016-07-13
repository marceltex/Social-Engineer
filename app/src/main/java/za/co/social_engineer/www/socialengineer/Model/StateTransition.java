package za.co.social_engineer.www.socialengineer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * State Transition class used to create state transition objects of each record in the
 * stateTransitions database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class StateTransition implements Parcelable {

    private String id;
    private String state;
    private String match;
    private String transition;

    public StateTransition(String id, String state, String match, String transition) {
        this.id = id;
        this.state = state;
        this.match = match;
        this.transition = transition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getTransition() {
        return transition;
    }

    public void setTransition(String transition) {
        this.transition = transition;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.state);
        dest.writeString(this.match);
        dest.writeString(this.transition);
    }

    protected StateTransition(Parcel in) {
        this.id = in.readString();
        this.state = in.readString();
        this.match = in.readString();
        this.transition = in.readString();
    }

    public static final Parcelable.Creator<StateTransition> CREATOR = new Parcelable.Creator<StateTransition>() {
        public StateTransition createFromParcel(Parcel source) {
            return new StateTransition(source);
        }

        public StateTransition[] newArray(int size) {
            return new StateTransition[size];
        }
    };
}
