import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.*;



/**
 * This is a SUBSET of the unit tests with which we will grade your code.
 *
 * Make absolute sure that your code can compile together with this tester!
 * If it does not, you may get a very low grade for your assignment.
 */
public class FacebukPartialTester {
	private Person _barack, _michelle, _kevin, _ina, _joe, _malia;
	private Pet _bo, _sunny;
	private Moment _meAndBarack;
	private ArrayList _michelleAndBarack, _michelleJoeBoAndMalia;

	@Before
	public void setUp () {
		initPeople();
		initPets();
		initGroups();
		initPossessions();
		initMoments();
	}

	private void initPeople () {
		_michelle = new Person("Michelle", new Image("Michelle.png"));
		_barack = new Person("Barack", new Image("Barack.png"));
		_kevin = new Person("Kevin", new Image("Kevin.png"));
		_ina = new Person("Ina", new Image("Ina.png"));
		_joe = new Person("Joe", new Image("Joe.png"));
		_malia = new Person("Malia", new Image("Malia.png"));
	}

	private void initPets () {
		_bo = new Pet("Bo", new Image("Bo.png"));
		_sunny = new Pet("Sunny", new Image("Sunny.png"));

		_bo.setOwner(_michelle);
		_sunny.setOwner(_michelle);
	}

	private void initGroups () {
		// Kevin, Barack, and Ina
		final ArrayList michelleFriends = new ArrayList();
		michelleFriends.add(_kevin);
		michelleFriends.add(_barack);
		michelleFriends.add(_ina);

		// Michelle and Barack
		_michelleAndBarack = new ArrayList();
		_michelleAndBarack.add(_michelle);
		_michelleAndBarack.add(_barack);

		// Michelle, Joe, Bo, and Malia
		_michelleJoeBoAndMalia = new ArrayList();
		_michelleJoeBoAndMalia.add(_michelle);
		_michelleJoeBoAndMalia.add(_joe);
		_michelleJoeBoAndMalia.add(_bo);
		_michelleJoeBoAndMalia.add(_malia);

		// Malia and Sunny
		final ArrayList maliaAndSunny = new ArrayList();
		maliaAndSunny.add(_malia);
		maliaAndSunny.add(_sunny);

		// Michelle
		final ArrayList michelleList = new ArrayList();
		michelleList.add(_michelle);

		// Bo
		final ArrayList boList = new ArrayList();
		boList.add(_bo);

		// Set people's friend lists
		_michelle.setFriends(michelleFriends);
		_malia.setFriends(boList);
		_sunny.setFriends(boList);
		_barack.setFriends(michelleList);
		_kevin.setFriends(michelleList);
		_ina.setFriends(michelleList);
	
		// Finish configuring pets
		_bo.setFriends(maliaAndSunny);
		_sunny.setFriends(boList);
		final ArrayList boAndSunny = new ArrayList();
		boAndSunny.add(_bo);
		boAndSunny.add(_sunny);
		_michelle.setPets(boAndSunny);
	}

	private void initPossessions () {
		final Possession boxingBag = new Possession("BoxingBag", new Image("BoxingBag.png"), 20.0f);
		boxingBag.setOwner(_michelle);
		final ArrayList michellePossessions = new ArrayList();
		michellePossessions.add(boxingBag);
		_michelle.setPossessions(michellePossessions);
	}

	private void initMoments () {
		// Smiles
		final ArrayList michelleAndBarackSmiles = new ArrayList();
		michelleAndBarackSmiles.add(0.25f);
		michelleAndBarackSmiles.add(0.75f);

		final ArrayList michelleJoeBoAndMaliaSmiles = new ArrayList();
		michelleJoeBoAndMaliaSmiles.add(0.2f);
		michelleJoeBoAndMaliaSmiles.add(0.3f);
		michelleJoeBoAndMaliaSmiles.add(0.4f);
		michelleJoeBoAndMaliaSmiles.add(0.5f);

		// Moments
		_meAndBarack = new Moment("Me & Barack", new Image("MeAndBarack.png"), _michelleAndBarack, michelleAndBarackSmiles);
		final Moment meJoeAndCo = new Moment("Me, Joe & co.", new Image("MeJoeAndCo.png"), _michelleJoeBoAndMalia, michelleJoeBoAndMaliaSmiles);

		final ArrayList michelleMoments = new ArrayList();
		michelleMoments.add(_meAndBarack);
		michelleMoments.add(meJoeAndCo);
		_michelle.setMoments(michelleMoments);

		final ArrayList barackMoments = new ArrayList();
		barackMoments.add(_meAndBarack);
		_barack.setMoments(barackMoments);

		final ArrayList joeMoments = new ArrayList();
		joeMoments.add(meJoeAndCo);
		_joe.setMoments(joeMoments);

		final ArrayList maliaMoments = new ArrayList();
		maliaMoments.add(meJoeAndCo);
		_malia.setMoments(maliaMoments);

		final ArrayList boMoments = new ArrayList();
		boMoments.add(meJoeAndCo);
		_bo.setMoments(boMoments);
	}

	@Test
	public void testEquals () {
		assertEquals(_michelle, new Person("Michelle", new Image("Michelle.png")));
		assertEquals(_michelle, new Person("Michelle", new Image("Michelle2.png")));  // should still work
		assertNotEquals(_michelle, _barack);
	}

	@Test
	public void testFindBestMoment () {
		assertEquals(_michelle.getOverallHappiestMoment(), _meAndBarack);
	}

	@Test
	public void testGetFriendWithWhomIAmHappiest () {
		assertEquals(_michelle.getFriendWithWhomIAmHappiest(), _barack);
	}

	@Test
	public void testFriendRequest1 () {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Pet pet1 = new Pet("pet1", new Image("pet1.png"));

		FriendRequest friendRequest1 = new FriendRequest(person1, person2);
		// Make sure the code also compiles for making friend requests for people and pets
		FriendRequest friendRequest2 = new FriendRequest(person1, pet1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFriendRequest2 () {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		FriendRequest friendRequest = new FriendRequest(person1, person2);
		// This should raise an IllegalArgumentException:
		friendRequest.approve(person3);
	}

	// TODO: write more methods to test addFriend

	@Test
	public void testAddFriend1() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));

		assertFalse(person1.getFriends().contains(person2));
		assertFalse(person2.getFriends().contains(person1));

		person1.addFriend(person2);

		assert (person1.getFriends().contains(person2));
		assertFalse(person2.getFriends().contains(person1));

		person2.addFriend(person1);

		assert (person1.getFriends().contains(person2));
		assert (person2.getFriends().contains(person1));

	}

	@Test
	public void testAddFriend2() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));

		person1.setFriends(getList(person2));

		assert (person1.getFriends().contains(person2));

		person1.addFriend(person2);

		assert (person1.getFriends().contains(person2));
	}


	// TODO: write more methods to test approve

	@Test
	public void testFriendRequest3() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));

		FriendRequest friendRequest1 = new FriendRequest(person1, person2);

		// Make sure that person1 and person2 are not friends already;
		assertFalse(person1.getFriends().contains(person2));
		assertFalse(person2.getFriends().contains(person1));

		friendRequest1.approve(person1);

		// Make sure that one person's approval doesn't make them friends.
		assertFalse(person1.getFriends().contains(person2));
		assertFalse(person2.getFriends().contains(person1));

		friendRequest1.approve(person2);

		// Make sure that they are friends after both have approved.
		assert (person1.getFriends().contains(person2));
		assert (person2.getFriends().contains(person1));
	}

	// TODO: write more methods to test getFriendWithWhomIAmHappiest

	@Test
	public void testGetFriendWithWhomIAmHappiest2() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));

		// Test with only one friend, since this short-circuits the `max` function on a Java `Stream`
		person1.setFriends(getList(person2));

		Moment m1 = new Moment("Moment 1", new Image("M1.png"), getList(person1, person3, person4), getList(1.0f, 1.0f, 1.0f));
		person1.setMoments(getList(m1));

		assertNull(person1.getFriendWithWhomIAmHappiest());
	}

	@Test
	public void testGetFriendWithWhomIAmHappiest3() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));

		// Test with two friends, only one of which is in a shared Moment.
		person1.setFriends(getList(person2, person3));

		Moment m1 = new Moment("Moment 1", new Image("M1.png"), getList(person1, person3, person4), getList(1.0f, 1.0f, 1.0f));
		person1.setMoments(getList(m1));

		assertEquals(person1.getFriendWithWhomIAmHappiest(), person3);
	}

	@Test
	public void testGetFriendWithWhomIAmHappiest4() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));

		// Test with three friends, two of which are in a shared Moment and have the same happiness level.
		person1.setFriends(getList(person2, person3, person4));

		Moment m1 = new Moment("Moment 1", new Image("M1.png"), getList(person1, person3, person4), getList(1.0f, 1.0f, 1.0f));
		person1.setMoments(getList(m1));

		assertThat(person1.getFriendWithWhomIAmHappiest(), anyOf(is(person3), is(person4)));
	}

	@Test
	public void testGetFriendWithWhomIAmHappiest5() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));

		// Test with two friends which are in shared Moments with different happiness levels
		person1.setFriends(getList(person2, person3));

		Moment m1 = new Moment("Moment 1", new Image("M1.png"), getList(person1, person3, person4), getList(1.0f, 1.0f, 1.0f));
		Moment m2 = new Moment("Moment 2", new Image("M2.png"), getList(person1, person2, person4), getList(2.0f, 1.0f, 1.0f));
		person1.setMoments(getList(m1, m2));

		assertEquals(person1.getFriendWithWhomIAmHappiest(), person2);
	}

	// TODO: write more methods to test getOverallHappiestMoment

	// TODO: write methods to test isClique

	@Test
	public void testIsClique1() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));

		// Person4 is not friends with Person3
		person1.setFriends(getList(person2, person3, person4));
		person2.setFriends(getList(person1, person3, person4));
		person3.setFriends(getList(person1, person2));
		person4.setFriends(getList(person1, person2));

		assertFalse(Friendable.isClique(getList(person1, person2, person3, person4)));
	}

	@Test
	public void testIsClique2() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));

		person1.setFriends(getList(person2, person3, person4));
		person2.setFriends(getList(person1, person3, person4));
		person3.setFriends(getList(person1, person2, person4));
		person4.setFriends(getList(person1, person2, person3));

		assert (Friendable.isClique(getList(person1, person2, person3, person4)));
	}

	// TODO: write methods to test findMaximumCliqueOfFriends

	@Test
	public void testFindMaximumCliqueOfFriends() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));

		person1.setFriends(getList(person2, person3));
		person2.setFriends(getList(person1, person3, person4));
		person3.setFriends(getList(person1, person2, person4));
		person4.setFriends(getList(person1, person2, person3));

		// Make sure that the person 1's maximal clique does not contain person 4
		assert (person1.findMaximumCliqueOfFriends().containsAll(getList(person2, person3)));
	}

	@Test
	public void testFindMaximumCliqueOfFriends2() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));

		person1.setFriends(getList(person2, person3, person4));
		person2.setFriends(getList(person1, person3, person4));
		person3.setFriends(getList(person1, person2, person4));
		person4.setFriends(getList(person1, person2, person3));

		// Other than the person themselves, all friends should have the same maximal clique
		assert (person1.findMaximumCliqueOfFriends().containsAll(getList(person2, person3, person4)));
		assert (person2.findMaximumCliqueOfFriends().containsAll(getList(person1, person3, person4)));
		assert (person3.findMaximumCliqueOfFriends().containsAll(getList(person1, person2, person4)));
		assert (person4.findMaximumCliqueOfFriends().containsAll(getList(person1, person2, person3)));
	}

	@Test
	public void testFindMaximumCliqueOfFriends3() {
		Person person1 = new Person("person1", new Image("person1.png"));
		Person person2 = new Person("person2", new Image("person2.png"));
		Person person3 = new Person("person3", new Image("person3.png"));
		Person person4 = new Person("person4", new Image("person4.png"));
		Person person5 = new Person("person4", new Image("person4.png"));
		Person person6 = new Person("person4", new Image("person4.png"));
		Person person7 = new Person("person4", new Image("person4.png"));

		person1.setFriends(getList(person2, person3, person4, person5, person6, person7));
		person2.setFriends(getList(person1, person3, person4, person5, person6, person7));
		person3.setFriends(getList(person1, person2, person4));
		person4.setFriends(getList(person1, person2, person3));
		person5.setFriends(getList(person1, person2, person3));
		person6.setFriends(getList(person1, person2, person3));
		person7.setFriends(getList(person1, person2, person3));

		assert (person1.findMaximumCliqueOfFriends().containsAll(getList(person2, person3, person4)));
	}

	@Test
	public void testFindMaximumCliqueOfFriends4() {
		/* As per example in assignment details:
			Melania's friends are: Ivana Trump, Kevin Hart, Hillary Trump, and Marlon Bundo.
			Marlon's friends are: Melania Trump, Charlotte Pence, Kevin Hart, and Humphrey.
			Ivana's friends are: Melania Trump, Tom Cruise, Hillary Clinton, and Kevin Hart.
			Kevin's friends are: Melania Trump, Marlon Bundo, Robin Wright, Hillary Clinton, Humphrey, and Ivana Trump.
			Hillary's friends are: Melania Trump, Ivana Trump, Kevin Hart, and Robin Wright.
			Melania's maximum clique is Ivana, Kevin, and Hillary.
		 */
		Person melania = new Person("Melania Trump", new Image("melania.png"));
		Pet marlon = new Pet("Marlon Bundo", new Image("marlonbundo.png"));
		Person ivana = new Person("Ivana Trump", new Image("ivana.png"));
		Person kevin = new Person("Kevin Hart", new Image("kevin.png"));
		Person hillary = new Person("Hillary Clinton", new Image("hillary.png"));
		Person tom = new Person("Tom Cruise", new Image("tom.png"));
		Pet humphrey = new Pet("Humphrey", new Image("humphrey.png"));
		Person charlotte = new Person("Charlotte Pence", new Image("charlotte.png"));
		Person robin = new Person("Robin Wright", new Image("robin.png"));

		melania.setFriends(getList(ivana, kevin, hillary, marlon));
		marlon.setFriends(getList(melania, charlotte, kevin, humphrey));
		ivana.setFriends(getList(melania, tom, hillary, kevin));
		kevin.setFriends(getList(melania, marlon, robin, hillary, humphrey, ivana));
		hillary.setFriends(getList(melania, ivana, kevin, robin));

		assert (melania.findMaximumCliqueOfFriends().containsAll(getList(ivana, kevin, hillary)));
	}

	// TODO: write methods to test getPrice

	@Test
	public void testGetPrice() {
		Possession possession = new Possession("Possession1", new Image("p1.gif"), 5.0f);
		assertEquals(possession.getPrice(), 5.0f, 1E-3);
	}

	// TODO: write methods to test getOwner

	@Test
	public void testGetOwner1() {
		Possession possession = new Possession("Possession1", new Image("p1.gif"), 5.0f);
		Person owner = new Person("Owner1", new Image("o1.jpeg"));

		assertNull(possession.getOwner());

		possession.setOwner(owner);

		assertEquals(possession.getOwner(), owner);
	}

	@Test
	public void testGetOwner2() {
		Pet pet = new Pet("Pet1", new Image("p1.tiff"));
		Person owner = new Person("Owner1", new Image("o1.jpeg"));

		assertNull(pet.getOwner());

		pet.setOwner(owner);

		assertEquals(pet.getOwner(), owner);
	}



	/**
	 * Shorthand for creating an ArrayList from values in-place.
	 *
	 * @return Filled ArrayList
	 */
	private <T> ArrayList<T> getList(T... objects) {
		return new ArrayList<>(Arrays.asList(objects));
	}
}
