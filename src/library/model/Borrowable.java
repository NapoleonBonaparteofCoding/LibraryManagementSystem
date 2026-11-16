package library.model;

public interface Borrowable {

    boolean borrow();

    boolean returnItem();

    boolean isAvailable();
}
