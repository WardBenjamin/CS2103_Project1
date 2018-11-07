import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a {@code Person} or {@code Pet}, which may have a list of friends.
 */
public abstract class Friendable extends FacebukBase {

    private ArrayList<Friendable> _friends = new ArrayList<>();
    private ArrayList<Moment> _moments = new ArrayList<>();

    protected Friendable(String name, Image image) {
        super(name, image);
    }

    /**
     * Sets the list of friends for the target friendable
     *
     * @param friends List of friends
     */
    public void setFriends(ArrayList<Friendable> friends) {
        _friends = friends;
    }

    /**
     * Gets a list of the person's/pet's friends
     *
     * @return List of friends
     */
    ArrayList getFriends() {
        return _friends;
    }

    /**
     * Adds a new friend to the list of friends of the target object.
     */
    void addFriend(Friendable friend) {
        if (!_friends.contains(friend)) {
            _friends.add(friend);
        }
    }

    /**
     * Sets the list of {@code Moment}s for the target friendable
     *
     * @param moments List of moments
     */
    void setMoments(ArrayList<Moment> moments) {
        _moments = moments;
    }

    public Moment getOverallHappiestMoment() {
        return _moments.stream().max(Comparator.comparing(m -> m.getHappinessForParticipant(this))).get();
    }

    public Friendable getFriendWithWhomIAmHappiest() {
        // Cover the single-friend case, which short-circuits the `max` stream operation below.
        if (_friends.size() == 1) {
            if (_moments.stream().anyMatch(m -> m.hasParticipated(_friends.get(0))))
                return _friends.get(0);
            return null;
        }

        final ArrayList<Friendable> invalidFriends = new ArrayList<>();

        // Return the friend with whom the current friendable has the greatest
        // average happiness throughout all shared Moments.
        Friendable happiestFriend = _friends.stream().max(Comparator.comparing(f -> {
            float totalHappiness = 0;
            int sharedMoments = 0;

            // For each Moment that the friend has participated in, add the
            // current friendable's happiness in that Moment.
            for (Moment moment : _moments) {
                if (moment.hasParticipated(f)) {
                    totalHappiness += moment.getHappinessForParticipant(this);
                    sharedMoments++;
                }
            }

            // If the friend shares no valid moments, they're not a valid option.
            if (!(sharedMoments > 0)) {
                invalidFriends.add(f);
            }

            // Compute this friendable's average happiness over all shared moments. If the number of
            // shared moments is zero return 0, otherwise return the computed average.
            return !Float.isNaN(totalHappiness / sharedMoments) ? totalHappiness / sharedMoments : 0;
        })).get();

        // If all of the friends are invalid, return null.
        if (invalidFriends.contains(happiestFriend))
            return null;

        return happiestFriend;
    }

    /**
     * Returns if and only if all the people/pets in the specified set are all friends with each other.
     * If set is empty, then this method should return true since there is no one in the set who is not friends
     * with another member of the set.
     *
     * @param set List containing members of a potential clique
     * @return Whether the set is a valid clique
     */
    static boolean isClique(ArrayList<Friendable> set) {
        if (set.size() == 0)
            return true;

        // For each friend in set, check if all of set is contained within their friends
        return set.stream().allMatch(f -> {
            final ArrayList<Friendable> set_copy = (ArrayList<Friendable>) set.clone();
            set_copy.remove(f);
            return f._friends.containsAll(set_copy);
        });
    }

    /**
     * Find the maximum-size clique of friends with whom the target person/pet could go out, such that each of
     * his/her friends is also friends with everyone else in the set. The set will not contain the target person/pet
     * on which the method was called. If there are multiple such "largest sets" (aka maximum cliques) for the
     * target person/pet, any of them may be returned. The set's order is not guaranteed.
     *
     * @return The maximal clique of friends possible
     */
    ArrayList<Friendable> findMaximumCliqueOfFriends() {
        return findMaxmimumCliqueOfFriends(_friends);
    }

    /**
     * By recursing through the list of friends and decreasing the amount contained per one each time, we are
     * guaranteed a maximum-size solution.
     *
     * @param friends
     * @return
     */
    private ArrayList<Friendable> findMaxmimumCliqueOfFriends(ArrayList<Friendable> friends) {
        if (isClique(friends))
            return friends;

        ArrayList<Friendable> maximumClique = null;

        for (Friendable friend : friends) {
            final ArrayList<Friendable> friends_minus = (ArrayList<Friendable>) friends.clone();
            friends_minus.remove(friend);
            ArrayList<Friendable> potentialMax = findMaxmimumCliqueOfFriends(friends_minus);
            if (maximumClique == null || (potentialMax != null && potentialMax.size() > maximumClique.size())) {
                maximumClique = potentialMax;
            }
        }

        return maximumClique;
    }
}
