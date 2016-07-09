package za.co.social_engineer.www.socialengineer.Model;

/**
 * State class used to create state objects of each record in the State database table.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public class State {

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
}
