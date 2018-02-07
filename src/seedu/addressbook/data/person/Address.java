package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should be in this format a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+, .+, .+, .+";

    public final String value;
    public Block block;
    public Street street;
    public Unit unit;
    public PostalCode postalCode;

    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();

        String splitAddress[] = new String[4];
        splitAddress = address.split(",");
        try {
            block = new Block(splitAddress[0]);
            street = new Street(splitAddress[1]);
            unit = new Unit(splitAddress[2]);
            postalCode = new PostalCode(splitAddress[3]);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }

        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = trimmedAddress;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}

class Block{
    public final String block;

    public Block(String block) {
        this.block = block.trim();
    }
}

class Street{
    public final String street;

    public Street(String street) {
        this.street = street.trim();
    }
}

class Unit{
    public final String unit;

    public Unit(String unit) {
        this.unit = unit.trim();
    }
}

class PostalCode{
    public final String postalCode;

    public PostalCode(String postalCode) {
        this.postalCode = postalCode.trim();
    }
}