import java.util.ArrayList;

public class Moment extends FacebukBase {
    private final ArrayList<Friendable> _participants;
    private final ArrayList<Float> _smileValues;

    public Moment(String name, Image image, ArrayList<Friendable> participants, ArrayList<Float> smileValues) {
        super(name, image);
        _participants = participants;
        _smileValues = smileValues;
    }

    /**
     * Gets the happiness, or "smile value" of the specified participant in this moment.
     *
     * @param participant The participant for which to retrieve happiness level
     * @return Happiness level of the specified participant
     * @throws IllegalArgumentException If the participant did not actually participate in this {@code Moment}
     */
    public float getHappinessForParticipant(Friendable participant) throws IllegalArgumentException {
        try {
            return _smileValues.get(_participants.indexOf(participant));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The Friendable did not participate in this Moment");
        }
    }

    public boolean hasParticipated(Friendable participant) {
        return _participants.contains(participant);
    }
}
