package Entity;

public class Entity {

    protected boolean alive;
    public boolean isAlive() {
        return alive;
    }

    public String name;
    protected int id;

    Entity(){
        alive = true;
        id = 1;
    }

}
