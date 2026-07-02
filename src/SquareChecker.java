
// A custom functional interface — one abstract method, so it can be
// implemented with a lambda expression instead of a full class.
@FunctionalInterface
public interface SquareChecker {
     boolean check(int n);
}
