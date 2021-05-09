package Entity;

public class Entity {

    protected boolean alive;
    public boolean isAlive() {
        return alive;
    }

    public String name;
    protected int id;

    public Entity(String name){
        this.name = name;
        this.alive = true;
        // FIXME: 9.05.2021 
        this.id = 1;
    }

    public Entity(){
        alive = true;
        // FIXME: 9.05.2021 
        id = 1;
    }

}
