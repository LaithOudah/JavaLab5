package lab5;

public class Person {

    private String givenName;
    private String surname;
    private int phoneNumber;


    public Person(String arg, String arg2, int number) {

    }

    public String getFullName() {
        return givenName + "" + surname;
    }

    public String getSurname() {
        return surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
