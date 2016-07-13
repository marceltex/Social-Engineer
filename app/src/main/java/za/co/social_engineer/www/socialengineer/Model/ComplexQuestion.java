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

    private String id;
    private String questionSet;
    private String questions;
    private String count;
    private String _return;

    public ComplexQuestion(String id, String questionSet, String questions, String count,
                           String _return) {
        this.id = id;
        this.questionSet = questionSet;
        this.questions = questions;
        this.count = count;
        this._return = _return;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getReturn() {
        return _return;
    }

    public void setReturn(String _return) {
        this._return = _return;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.questionSet);
        dest.writeString(this.questions);
        dest.writeString(this.count);
        dest.writeString(this._return);
    }

    protected ComplexQuestion(Parcel in) {
        this.id = in.readString();
        this.questionSet = in.readString();
        this.questions = in.readString();
        this.count = in.readString();
        this._return = in.readString();
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
