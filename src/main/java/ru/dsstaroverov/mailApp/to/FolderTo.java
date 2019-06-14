package ru.dsstaroverov.mailApp.to;

public class FolderTo extends BaseTo{
    private String name;

    public FolderTo() {
    }

    public FolderTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FolderTo{" +
                "name='" + name + '\'' +
                '}';
    }
}
