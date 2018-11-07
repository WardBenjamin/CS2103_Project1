import java.util.Objects;

public class FacebukBase {
    private final String _name;
    private final Image _image;

    FacebukBase(String name, Image image) {
        _name = name;
        _image = image;
    }

    public String getName() {
        return _name;
    }

    @Override
    public boolean equals(Object o) {
        // Self check
        if (this == o)
            return true;

        // Null check
        if (o == null)
            return false;

        // Type check and cast
        if (getClass() != o.getClass())
            return false;
        FacebukBase facebukBase = (FacebukBase) o;

        // Direct field comparison
        return Objects.equals(_name, facebukBase._name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
