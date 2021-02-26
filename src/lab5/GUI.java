package lab5;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {

    public static PhoneBook phoneBook = new PhoneBook();
    public static ArrayList<Person> personList;
    public static int position; // This little boy here helps me keep count on where iam in the arraylist when buttons get pressed.
    private JPanel buttonPanel, textPanel;
    private JTextField nameInputField, numInputField, searchInputField;
    private JButton add, delete, search, next, load, save;

    public GUI() {

        // Font
        Font font = new Font("Comic Sans", Font.PLAIN, 15); // Best font 2021

        // Frame Settings
        setLocationRelativeTo(null); // Center of screen
        setTitle("SuperAids&BronzeAged- Phonebook searcher"); // Window Title
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Terminate on exit
        setVisible(true); // Show me da window
        setSize(1024, 512); // Size Stuff

        // Container stuff
        Container container = getContentPane(); // Container
        container.setBackground(Color.MAGENTA); // Best faking color hello?
        container.setLayout(new GridLayout(1, 2)); // yes.

        // Buttons and their shit settings
        add = new JButton("Add"); add.setFont(font); add.setVisible(true); add.addActionListener(this);
        delete = new JButton("Delete"); delete.setFont(font); delete.setVisible(true); delete.addActionListener(this);
        search = new JButton("Search"); search.setFont(font); search.setVisible(true); search.addActionListener(this);
        next = new JButton("Next"); next.setFont(font); next.setVisible(true); next.addActionListener(this);
        load = new JButton("Load Phonebook"); load.setFont(font); load.setVisible(true); load.addActionListener(this);
        save = new JButton("Save Phonebook"); save.setFont(font); save.setVisible(true); save.addActionListener(this);

        // Fields and also their shitty settings
        nameInputField = new JTextField("", 20); nameInputField.setFont(font); nameInputField.setEditable(true); nameInputField.addActionListener(this);
        numInputField = new JTextField("", 20); numInputField.setFont(font); numInputField.setEditable(true); numInputField.addActionListener(this);
        searchInputField = new JTextField("", 20); searchInputField.setFont(font); searchInputField.setEditable(true); searchInputField.addActionListener(this);

        // Init the panel
        buttonPanel = new JPanel(new GridLayout(3, 3));
        textPanel = new JPanel(new GridLayout(3, 1));

        // Adding the most hated buttons on earth to the panel
        this.buttonPanel.add(add); this.buttonPanel.add(delete); this.buttonPanel.add(search); this.buttonPanel.add(next); this.buttonPanel.add(load); this.buttonPanel.add(save);

        // Adding the text stuff to another panel
        textPanel.add(nameInputField); textPanel.add(numInputField); textPanel.add(searchInputField);

        // Adding stuff to container
        container.add(buttonPanel); container.add(textPanel); container.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        /*
         * Aight so below will be a lof of if statements for the buttons.
         * Of course each button will do something.
         * If you dont understand whats going on in the functions.
         * Read again.

         * Ok I'll explain the first
         * SO
         *
         * 1. CHECKING IF the actionListener get the button "ADD" pressed.
         * 2. yes a counter boi.
         * 3. Reset my fields so they empty bby
         */


        if (e.getSource().equals(add)) {

            if (position == 0) {
                nameInputField.setText(null); // Reset the stuff
                numInputField.setText(null); // Reset the stuff
            }

            position += 1; // Increment me position
            searchInputField.setText("Name and telly-FÅN-number"); // Soooooooooooo, this will change the input field to this.

            searchInputField.setEditable(true); // Allow the USER delete my shitty text and put in theirs.
            numInputField.setEditable(true); // Allow the user type in a a number

            if (position >= 2) {
                // Get text if its this input
                if (phoneBook.addPerson(nameInputField.getText(), Integer.parseInt(nameInputField.getText()))) {

                    searchInputField.setText("Person Added my guy =)"); // Say "Person added my guy with a smiley

                    nameInputField.setText(null); // reset
                    nameInputField.setEditable(false); // no edit for u

                    numInputField.setText(null); // reset
                    numInputField.setEditable(false); // no edit for u

                } else { // Else i tell u, no kneegrows allowed!
                    searchInputField.setText("nono kneegrows not allowed, try again.");
                }
            }
        }

        if (e.getSource().equals(delete)) {

            nameInputField.setEditable(false);
            numInputField.setEditable(false);

            if (!numInputField.getText().equals("")) {
                searchInputField.setText(phoneBook.deletePerson(nameInputField.getText(), Integer.parseInt(numInputField.getText())));
            }

        }

        if (e.getSource().equals(search)) {

            String holder; // useless piece of shit holding stuff for me. Just like a bitch but for free.

            nameInputField.setEditable(false);
            numInputField.setEditable(false);
            holder = searchInputField.getText();
            searchInputField.setText(null);

            personList = phoneBook.search(holder);

            if (personList.size() == 0) {
                nameInputField.setText("Name please for the sake of god");
                numInputField.setText(null);
            } else if (personList.size() == 1) {
                nameInputField.setText(personList.get(0).getFullName());
                numInputField.setText(String.valueOf(personList.get(0).getPhoneNumber()));
            } else {
                next.setEnabled(true);
                position = 0; // go back to 0
                nameInputField.setText(personList.get(0).getFullName());
                numInputField.setText(String.valueOf(personList.get(0).getPhoneNumber()));
            }

        }

        if (e.getSource().equals(next)) {

            nameInputField.setEditable(false);
            numInputField.setEditable(false);
            position += 1; // go on next boi.
            nameInputField.setText(personList.get(position).getFullName());
            numInputField.setText(String.valueOf(personList.get(position).getPhoneNumber()));

            // We do no wanna fuck up memory yes, so if we get out of memory we go back and no more search for u
            if (position == personList.size() - 1) {
                position = 0;
                next.setEnabled(false);
            }
        }

        if (e.getSource().equals(load)) {
            String holder; // useless piece of shit holding stuff for me. Just like a bitch but for free.
            nameInputField.setEditable(false);
            numInputField.setEditable(false);
            holder = searchInputField.getText();

            searchInputField.setText(null);
            nameInputField.setText(phoneBook.load(holder));

            if (nameInputField.getText().equals("Phone book loaded")) {
                load.setEnabled(true);
                save.setEnabled(true);
                search.setEnabled(true);
                add.setEnabled(true);
                delete.setEnabled(true);
            }

        }

        if (e.getSource().equals(save)) {

            String holder; // useless piece of shit holding stuff for me. Just like a bitch but for free.

            nameInputField.setEditable(false);
            numInputField.setEditable(false);
            holder = searchInputField.getText();

            searchInputField.setText(null);

            if (holder != null) {
                nameInputField.setText(phoneBook.save(holder));
            } else nameInputField.setText("File name senpai? o.o");

        } // Save Func
    } // Action Event
} // Public GUI Class