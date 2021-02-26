package lab5;


public class Person {

    private String givenName;
    private String surname;
    private int phoneNumber;


    /**
     * Constructor for Person
     *
     * @param arg  = Firstname
     * @param arg2 = Lastname
     * @param arg3 = TellyFåneNumber
     */

    public Person(String arg, String arg2, int arg3) {

        this.givenName = arg;
        this.surname = arg2;
        this.phoneNumber = arg3;

    } // Const. Person

    public String getFullName() {
        return givenName + " " + surname;
    }

    public String getSurname() {
        return surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

} // Class Person
