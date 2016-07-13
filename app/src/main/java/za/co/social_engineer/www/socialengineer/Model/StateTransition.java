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

    private int id;
    private int state;
    private int match;
    private int transition;
    private int circular;

    public StateTransition(int id, int state, int match, int transition, int circular) {
        this.id = id;
        this.state = state;
        this.match = match;
        this.transition = transition;
        this.circular = circular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public int getTransition() {
        return transition;
    }

    public void setTransition(int transition) {
        this.transition = transition;
    }

    public int getCircular() {
        return circular;
    }

    public void setCircular(int circular) {
        this.circular = circular;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.state);
        dest.writeInt(this.match);
        dest.writeInt(this.transition);
        dest.writeInt(this.circular);
    }

    public StateTransition(Parcel in) {
        this.id = in.readInt();
        this.state = in.readInt();
        this.match = in.readInt();
        this.transition = in.readInt();
        this.circular = in.readInt();
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
