package lab5;

import java.io.*;
import java.util.ArrayList;

public class PhoneBook {

    private ArrayList<Person> listOfNumbers;

    public PhoneBook() {
        this.listOfNumbers = new ArrayList<>();
    }

    public String load(String fileName) {

        FileReader newFileReader;

        try {
            newFileReader = new FileReader(fileName);
            BufferedReader fileReader = new BufferedReader(newFileReader);
            String fileContents;

            while ((fileContents =  fileReader.readLine()) != null) {
                String[] readArray = fileContents.split(" ");
                Person person = new Person(readArray[0], readArray[1], Integer.parseInt(readArray[2]));
                listOfNumbers.add(person);

            } // While

            fileReader.close();
            newFileReader.close();

        } catch (IOException e) {
            System.out.println("IO error \n Try again");
        }

        return "Phone book loaded!";
    } // Load func

    public ArrayList<Person> search(String usrInput) {

        ArrayList<Person> personList = new ArrayList<>();

        for (Person listOfNumber : listOfNumbers) {
            Person person = new Person(listOfNumber.getFullName(), listOfNumber.getSurname(), listOfNumber.getPhoneNumber());
            String num = Integer.toString(listOfNumber.getPhoneNumber());

            if (usrInput.equals(num) || usrInput.equals(person.getSurname())) {
                personList.add(person);
            }
        }

        return personList;
    }


    public String deletePerson(String name, int TelNum) {

        String tempNumber = Integer.toString(TelNum);
        ArrayList<Person> searchedPerson = search(tempNumber);

        for (Person person : searchedPerson) {
            if (listOfNumbers.contains(person) && person.getFullName().equals(name)) {
                listOfNumbers.remove(person);
                return "Person deleted";
            }
        }

        return "Person not found";
    }

    public boolean addPerson(String name, int TelNum) {
        String[] fullName = name.split(" ");

        for (Person personNumber : listOfNumbers) {

            if (fullName.length == 2) {
                Person person = new Person(fullName[0], fullName[1], TelNum);
                listOfNumbers.add(person);
                return true;
            }
            if (listOfNumbers.contains(personNumber)) {
                return false;
            }
        }
        return false;
    }


    public String save(String saveArg) {

        ArrayList<String> formattedArray = new ArrayList<>(); // Arraylist for the save?
        File newFile = new File(saveArg); // New file
        FileWriter fileWriter; // New file writer

        try {
            newFile.createNewFile();
            fileWriter = new FileWriter(saveArg, false);

            for (Person listOfNumber : listOfNumbers) {
                String currentPerson = listOfNumber.toString();
                formattedArray.add(String.format(currentPerson, "%20s\t|\t%5d\n", listOfNumber.getFullName(), listOfNumber.getPhoneNumber()));
            }
            fileWriter.write(formattedArray.toString());
            fileWriter.close();

        } catch (IOException e) {
            return ("Error encountered");
        }
        return "Saved " + listOfNumbers.size() + " people to the file";
    }

}




