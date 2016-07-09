package za.co.social_engineer.www.socialengineer.Model;

/**
 * State Transition class used to create state transition objects of each record in the
 * stateTransitions database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class StateTransition {

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
}
