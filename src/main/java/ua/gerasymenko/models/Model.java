package ua.gerasymenko.models;

public abstract class Model {

    protected int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (id != model.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

