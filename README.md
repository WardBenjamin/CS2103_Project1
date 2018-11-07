## Project1: Facebuk
###### Benjamin Ward, CS 2103 B01 2018

Required classes:
 - Person
 - Pet
 - Possession
 - Moment

To increase reuse of common code (and therefore minimize redundancy), all objects with names and images (`Person`, `Pet`, `Moment`, `Possession`) derive from `abstract FacebukBase`, which handles name, image, and equals operator override (aka, comparison). 

`Person` and `Pet` derive from `abstract Friendable`, which contains shared code for friend, `Moment`, and clique handling.

`FriendRequest` handles registration of friendship between two `Friendable` objects. The request must be approved by both `Friendable`s, and then they will be added to each others' list of friends.

Note1: This project makes extensive use of standard Java API `java.util` as well as Java 8 features such as `Stream`s and lambda functions.

Note2: Some tests rely on Hamcrest Core, specifically for use of `assertThat`, `anyOf`, and `is`:

```java
assertThat(person1.getFriendWithWhomIAmHappiest(), anyOf(is(person3), is(person4)));
```

Note3: Many of my tests rely on a helper method `getList`, defined within FacebukPartialTester as follows:

```java
/**
 * Shorthand for creating an ArrayList from values in-place.
 *
 * @return Filled ArrayList
 */
private <T> ArrayList<T> getList(T... objects) {
    return new ArrayList<>(Arrays.asList(objects));
}
```