import java.util.ArrayList;

public class Person extends Friendable {

    private ArrayList<Possession> _possessions = new ArrayList<>();
    private ArrayList<Pet> _pets = new ArrayList<>();

    public Person(String name, Image image) {
        super(name, image);
    }

    public void setPossessions(ArrayList<Possession> possessions) {
        _possessions = possessions;
    }

    public void setPets(ArrayList<Pet> pets) {
        _pets = pets;
    }
}
