package mainScene;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
        this("randomGuy");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
