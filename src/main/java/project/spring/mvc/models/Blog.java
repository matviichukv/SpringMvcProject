package project.spring.mvc.models;

public class Blog {

    private int id;
    private int owner_id;
    private String name;
    private String description;

    public Blog() {
    }

    public Blog(int id, int owner_id, String description) {
        this.id = id;
        this.owner_id = owner_id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
