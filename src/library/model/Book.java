
package library.model;

public class Book extends LibraryItem {

    private String author;
    private String isbn;

    public Book(
            String id,
            String title,
            int publicationYear,
            String author,
            String isbn) {

        super(id, title, publicationYear);

        setAuthor(author);
        setIsbn(isbn);
    }

    // Getters and setters

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException(
                "Author ne može da bude prazan."
            );
        }

        this.author = author.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException(
                "ISBN ne može da bude prazan."
            );
        }

        this.isbn = isbn.trim();
    }

    // Overridden methods

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public String getDetails() {
        return "Tip: " + getItemType()
                + " | ID: " + getId()
                + " | Naslov: " + getTitle()
                + " | Autor: " + author
                + " | ISBN: " + isbn
                + " | Godina izdavanja: " + getPublicationYear()
                + " | Status: "
                + (isAvailable() ? "Dostupno" : "Pozajmljeno");
    }
}
