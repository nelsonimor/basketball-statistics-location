package fr.basketball.statistics.location.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Interface used to verify the Equals() contract.
 *
 * <ul>
 * <li><b>Reflexivity</b> : Apple == Apple</li>
 * <li><b>Symetry</b> : if Apple == Orange then Orange == Apple</li>
 * <li><b>Transitivity</b> : if Apple == Banana and Banana == Orange then Orange
 * == Apple</li>
 * <li><b>Consistency</b> : if Apple == Orange now then Apple == Orange
 * always</li>
 * <li><b>Non Nullity</b> : Apple == null is never true</li>
 * </ul>
 *
 * See :
 * <ul>
 * <li>Chapter 3 of Effective Java Programming Language Guide By Joshua
 * Bloch</li>
 * <li>Java Cookbook 8.2. Overriding the equals() and hashCode() Methods</li>
 * </ul>
 */
public interface EqualsContractTester {

	/**
	 * Global equality tester method.
	 *
	 * Implement this in the manner of :
	 *
	 * <pre>
	 * <code>
	 * &#64;Test
	 * public final void testEqualsObject() {
	 *     // Instantiate here 3 same objects (obj1, obj2, obj3) and 1 different (obj4)
	 *     assertReflexive(obj1);
	 *     assertSymmetric(obj1, obj2);
	 *     assertTransitive(obj1, obj2, obj3);
	 *     assertNonNullity(obj1);
	 *     assertDifferent(obj1, obj4);
	 * }
	 * </code>
	 * </pre>
	 */
	public void testEqualsObject();

	/**
	 * for any non-null reference values x and y, x.equals(y) should return true if
	 * and only if y.equals(x) returns true.
	 *
	 * @param obj1 the first object
	 * @param obj2 the second object
	 */
	public default void assertSymmetric(final Object obj1, final Object obj2) {
		assertThat(obj1).isEqualTo(obj2);
		assertThat(obj2).isEqualTo(obj1);
	}

	/**
	 * for any non-null reference values x, y, and z, if x.equals(y) returns true
	 * and y.equals(z) returns true, then x.equals(z) should return true.
	 *
	 * @param obj1 the first object
	 * @param obj2 the second object
	 * @param obj3 the third object
	 */
	public default void assertTransitive(final Object obj1, final Object obj2, final Object obj3) {
		assertThat(obj1).isEqualTo(obj2);
		assertThat(obj2).isEqualTo(obj3);
		assertThat(obj1).isEqualTo(obj3);
	}

	/**
	 * For any non-null reference value x, x.equals(null) should return false.
	 *
	 * @param obj the object
	 */
	public default void assertNonNullity(final Object obj) {
		assertThat(obj).isNotNull();
	}

	/**
	 * test that obj1 and obj2 are different.
	 *
	 * @param obj1 the first object
	 * @param obj2 the second object
	 */
	public default void assertDifferent(final Object obj1, final Object obj2) {
		assertThat(obj1).isNotEqualTo(obj2);
	}

}