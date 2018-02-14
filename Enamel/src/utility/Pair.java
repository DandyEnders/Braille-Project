package utility;

/**
 * Generic class for storing a pair of objects.
 * 
 * @author Jinho Hwang
 *
 * @param <A>
 *            First object.
 * @param <B>
 *            Second object.
 */
public class Pair<A, B> {

	A a;
	B b;

	Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public A getFirst() {
		return this.a;
	}

	public B getSecond() {
		return this.b;
	}

	public void setFirst(A a) {
		this.a = a;
	}

	public void setSecond(B b) {
		this.b = b;
	}

	public void set(A a, B b) {
		setFirst(a);
		setSecond(b);
	}
}
