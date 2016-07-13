package za.co.social_engineer.www.socialengineer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Question class used to create question objects of each record in the questions database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class Question implements Parcelable {

    private int id;
    private int questionSet;
    private String question;
    private String optionA;
    private int returnA;
    private String optionB;
    private int returnB;
    private int isSkippable;
    private int isCount;
    private int isFinalCount;

    public Question(int id, int questionSet, String question, String optionA, int returnA,
                    String optionB, int returnB, int isSkippable, int isCount, int isFinalCount) {
        this.id = id;
        this.questionSet = questionSet;
        this.question = question;
        this.optionA = optionA;
        this.returnA = returnA;
        this.optionB = optionB;
        this.returnB = returnB;
        this.isSkippable = isSkippable;
        this.isCount = isCount;
        this.isFinalCount = isFinalCount;
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

    public int getReturnA() {
        return returnA;
    }

    public void setReturnA(int returnA) {
        this.returnA = returnA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public int getReturnB() {
        return returnB;
    }

    public void setReturnB(int returnB) {
        this.returnB = returnB;
    }

    public int getIsSkippable() {
        return isSkippable;
    }

    public void setIsSkippable(int isSkippable) {
        this.isSkippable = isSkippable;
    }

    public int getIsCount() {
        return isCount;
    }

    public void setIsCount(int isCount) {
        this.isCount = isCount;
    }

    public int getIsFinalCount() {
        return isFinalCount;
    }

    public void setIsFinalCount(int isFinalCount) {
        this.isFinalCount = isFinalCount;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.questionSet);
        dest.writeString(this.question);
        dest.writeString(this.optionA);
        dest.writeInt(this.returnA);
        dest.writeString(this.optionB);
        dest.writeInt(this.returnB);
        dest.writeInt(this.isSkippable);
        dest.writeInt(this.isCount);
        dest.writeInt(this.isFinalCount);
    }

    public Question(Parcel in) {
        this.id = in.readInt();
        this.questionSet = in.readInt();
        this.question = in.readString();
        this.optionA = in.readString();
        this.returnA = in.readInt();
        this.optionB = in.readString();
        this.returnB = in.readInt();
        this.isSkippable = in.readInt();
        this.isCount = in.readInt();
        this.isFinalCount = in.readInt();
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
