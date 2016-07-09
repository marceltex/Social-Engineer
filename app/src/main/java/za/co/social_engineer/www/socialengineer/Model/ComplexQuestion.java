package za.co.social_engineer.www.socialengineer.model;

/**
 * Complex Question class used to create complex question objects of each record in the
 * complexQuestions database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class ComplexQuestion {

    private String id;
    private String questionSet;
    private String questions;
    private String count;
    private String _return;

    public ComplexQuestion(String id, String questionSet, String questions, String count, String _return) {
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
}
