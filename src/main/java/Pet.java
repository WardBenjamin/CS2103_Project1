public class Pet extends Friendable {

    private Person _owner;

    public Pet(String name, Image image) {
        super(name, image);
    }

    public void setOwner(Person person) {
        _owner = person;
    }

    public Person getOwner() {
        return _owner;
    }
}
