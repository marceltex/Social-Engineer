package za.co.social_engineer.www.socialengineer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Complex Question class used to create complex question objects of each record in the
 * complexQuestions database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class ComplexQuestion implements Parcelable {

    private int id;
    private int questionSet;
    private int questions;
    private int count;
    private int _return;

    public ComplexQuestion(int id, int questionSet, int questions, int count, int _return) {
        this.id = id;
        this.questionSet = questionSet;
        this.questions = questions;
        this.count = count;
        this._return = _return;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(int questionSet) {
        this.questionSet = questionSet;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getReturn() {
        return _return;
    }

    public void setReturn(int _return) {
        this._return = _return;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.questionSet);
        dest.writeInt(this.questions);
        dest.writeInt(this.count);
        dest.writeInt(this._return);
    }

    public ComplexQuestion(Parcel in) {
        this.id = in.readInt();
        this.questionSet = in.readInt();
        this.questions = in.readInt();
        this.count = in.readInt();
        this._return = in.readInt();
    }

    public static final Parcelable.Creator<ComplexQuestion> CREATOR = new Parcelable.Creator<ComplexQuestion>(){
        public ComplexQuestion createFromParcel(Parcel source) {
            return new ComplexQuestion(source);
        }

        public ComplexQuestion[] newArray(int size) {
            return new ComplexQuestion[size];
        }
    };
}
