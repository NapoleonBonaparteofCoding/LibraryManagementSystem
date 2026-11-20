
package library.model;

import java.util.Locale;

public class Member {

    private String memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    // Constructor

    public Member(
            String memberId,
            String firstName,
            String lastName,
            String email,
            String phone) {

        setMemberId(memberId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
    }

    // Getters and setters

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        if (memberId == null || memberId.isBlank()) {
            throw new IllegalArgumentException(
                "Member ID ne može biti prazan."
            );
        }

        this.memberId = memberId.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException(
                "Ime ne može biti prazno."
            );
        }

        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException(
                "Prezime ne može biti prazno."
            );
        }

        this.lastName = lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                "Email ne može biti prazan."
            );
        }

        String normalizedEmail = email.trim();

        String emailRegex =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!normalizedEmail.matches(emailRegex)) {
            throw new IllegalArgumentException(
                "Nevažeća Email adresa."
            );
        }

        this.email = normalizedEmail.toLowerCase(Locale.ROOT);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException(
                "Broj telefona ne može biti prazan."
            );
        }

        String normalizedPhone = phone.trim();

        if (!normalizedPhone.matches("\\+?[0-9][0-9()\\s-]*")) {
            throw new IllegalArgumentException(
                "Nevažeći unos broja telefona."
            );
        }

        String digits = normalizedPhone.replaceAll("\\D", "");

        if (digits.length() < 7 || digits.length() > 15) {
            throw new IllegalArgumentException(
                "Broj telefona mora imati izmedju 7 i 15 cifara."
            );
        }

        this.phone = normalizedPhone;
    }

    // Additional methods

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId
                + " | Ime: " + getFullName()
                + " | Email: " + email
                + " | Broj telefona: " + phone;
    }
}
