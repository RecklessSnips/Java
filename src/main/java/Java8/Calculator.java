package Java8;

@FunctionalInterface
public interface Calculator<T, E> {

    E calculete(T a, T b, String operator);
}
