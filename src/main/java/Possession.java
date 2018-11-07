public class Possession extends FacebukBase {

    private final float _price;
    private Person _owner;

    public Possession(String name, Image image, float price) {
        super(name, image);
        this._price = price;
    }

    public void setOwner(Person person) {
        _owner = person;
    }

    public Person getOwner() {
        return _owner;
    }

    public float getPrice() {
        return _price;
    }
}
