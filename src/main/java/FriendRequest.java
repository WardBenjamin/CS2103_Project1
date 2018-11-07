public class FriendRequest {

    private Friendable _person1, _person2;
    private boolean _person1Accepted, _person2Accepted;

    public FriendRequest(Friendable person1, Friendable person2) {
        _person1 = person1;
        _person2 = person2;
    }

    /**
     * Approves the friend request for the specified person. Once both parties have approved the request
     * (but not before), then A is added to B's list of friends, and B is also added to A's list of friends.
     *
     * @param person Person who has accepted the friend request
     * @throws IllegalArgumentException If {@code person} is not one of the people participating in the friend request.
     */
    public void approve(Friendable person) throws IllegalArgumentException {
        final boolean isPerson1 = _person1.equals(person);
        final boolean isPerson2 = _person2.equals(person);

        // If the specified person isn't part of the friend request, reject the approval.
        if (!isPerson1 && !isPerson2) throw new IllegalArgumentException();

        // Figure out who accepted the request
        if (isPerson1) _person1Accepted = true;
        else _person2Accepted = true;

        // If both people have accepted, make them friends
        if (_person1Accepted && _person2Accepted) {
            _person1.addFriend(_person2);
            _person2.addFriend(_person1);
        }
    }
}
