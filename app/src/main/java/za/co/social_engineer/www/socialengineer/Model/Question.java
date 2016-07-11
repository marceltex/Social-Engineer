package za.co.social_engineer.www.socialengineer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Question class used to create question objects of each record in the questions database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class Question implements Parcelable {

    private String id;
    private String questionSet;
    private String question;
    private String optionA;
    private String returnA;
    private String optionB;
    private String returnB;
    private String isSkippable;
    private String isCount;
    private String isFinalCount;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getReturnA() {
        return returnA;
    }

    public void setReturnA(String returnA) {
        this.returnA = returnA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getReturnB() {
        return returnB;
    }

    public void setReturnB(String returnB) {
        this.returnB = returnB;
    }

    public String getIsSkippable() {
        return isSkippable;
    }

    public void setIsSkippable(String isSkippable) {
        this.isSkippable = isSkippable;
    }

    public String getIsCount() {
        return isCount;
    }

    public void setIsCount(String isCount) {
        this.isCount = isCount;
    }

    public String getIsFinalCount() {
        return isFinalCount;
    }

    public void setIsFinalCount(String isFinalCount) {
        this.isFinalCount = isFinalCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.questionSet);
        dest.writeString(this.question);
        dest.writeString(this.optionA);
        dest.writeString(this.returnA);
        dest.writeString(this.optionB);
        dest.writeString(this.returnB);
        dest.writeString(this.isSkippable);
        dest.writeString(this.isCount);
        dest.writeString(this.isFinalCount);
    }

    public Question() {

    }

    protected Question(Parcel in) {
        this.id = in.readString();
        this.questionSet = in.readString();
        this.question = in.readString();
        this.optionA = in.readString();
        this.returnA = in.readString();
        this.optionB = in.readString();
        this.returnB = in.readString();
        this.isSkippable = in.readString();
        this.isCount = in.readString();
        this.isFinalCount = in.readString();
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
