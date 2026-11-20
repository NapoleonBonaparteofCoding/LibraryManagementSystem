
package library.model;

public abstract class LibraryItem implements Borrowable {

    private String id;
    private String title;
    private int publicationYear;
    private boolean available;

    public LibraryItem(String id, String title, int publicationYear) {
        setId(id);
        setTitle(title);
        setPublicationYear(publicationYear);
        this.available = true;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                "Item ID ne može da bude prazan."
            );
        }

        this.id = id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                "Naslov ne može da bude prazan."
            );
        }

        this.title = title.trim();
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear < 1) {
            throw new IllegalArgumentException(
                "Godina izdavanja mora biti veća od 0."
            );
        }

        this.publicationYear = publicationYear;
    }

    // Borrowable interface implementation

    @Override
    public boolean borrow() {
        if (!available) {
            return false;
        }

        available = false;
        return true;
    }

    @Override
    public boolean returnItem() {
        if (available) {
            return false;
        }

        available = true;
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    // Abstract methods

    public abstract String getItemType();

    public abstract String getDetails();

    @Override
    public String toString() {
        return getDetails();
    }
}
