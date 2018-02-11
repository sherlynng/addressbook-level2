package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Sorts all persons in the address book and lists the sorted list to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book according to alphabetical order and " +
            "lists them with index numbers.\n" + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        sortAllPersons(addressBook.getUniquePersonList().getInternalList());
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }

    /**
     * Sorts the address book according to names
     * @param list
     */
    private void sortAllPersons(List<Person> list){
        Collections.sort(list, new sortComparator());
    }

    private class sortComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().toString().compareToIgnoreCase(p2.getName().toString());
        }
    }
}