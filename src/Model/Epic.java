package Model;

import java.util.ArrayList;

public class Epic extends Task {

    private  ArrayList<Integer> subTaskIds = new ArrayList<>();


    public Epic(int id, String name, String description){
        super(id, name,  description);
    }

    public ArrayList<Integer> getSubTaskIds() {
        return new ArrayList<>(subTaskIds);
    }

    public void addSubTaskIds(int id) {
        subTaskIds.add(id);
    }

    public void setSubTaskIds(ArrayList<Integer> subTaskIds) {
        this.subTaskIds = new ArrayList<>(subTaskIds);
    }
}
