package assign09;

/**
 * Serves as a guide for how to define a functor that contains hashing function
 * for String items (i.e., the hash method)
 * 
 * @author rjrus
 *
 */
public interface HashFunctor {
	// Returns hash code of passed in spring parameter.
	public int hash(String item);
}
