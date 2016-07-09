package za.co.social_engineer.www.socialengineer.Model;

/**
 * Question class used to create question objects of each record in the questions database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class Question {

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

    public Question(String id, String questionSet, String question, String optionA, String returnA,
                    String optionB, String returnB, String isSkippable, String isCount,
                    String isFinalCount) {
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
}
