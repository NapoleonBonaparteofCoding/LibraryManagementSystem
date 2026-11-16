
package library.model;

public class Magazine extends LibraryItem {

    private String publisher;
    private int issueNumber;

    public Magazine(
            String id,
            String title,
            int publicationYear,
            String publisher,
            int issueNumber) {

        super(id, title, publicationYear);

        setPublisher(publisher);
        setIssueNumber(issueNumber);
    }

    // Getters and setters

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher == null || publisher.isBlank()) {
            throw new IllegalArgumentException(
                "Publisher cannot be empty."
            );
        }

        this.publisher = publisher.trim();
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber < 1) {
            throw new IllegalArgumentException(
                "Issue number must be greater than zero."
            );
        }

        this.issueNumber = issueNumber;
    }

    // Overridden methods

    @Override
    public String getItemType() {
        return "Magazine";
    }

    @Override
    public String getDetails() {
        return "Type: " + getItemType()
                + " | ID: " + getId()
                + " | Title: " + getTitle()
                + " | Publisher: " + publisher
                + " | Issue: " + issueNumber
                + " | Year: " + getPublicationYear()
                + " | Status: "
                + (isAvailable() ? "Available" : "Borrowed");
    }
}
