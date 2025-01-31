package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SELLER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SELLER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.property.Property;

/**
 * A utility class containing a list of {@code Property} objects to be used in tests.
 */
public class TypicalProperties {

    public static final Property P_ALICE = new PropertyBuilder().withName("Jurong West Ave 6")
            .withSeller("Alice Pauline")
            .withPrice("654321")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("HDB", "condo").build();
    public static final Property P_BENSON = new PropertyBuilder().withName("Mayflower")
            .withSeller("Benson Meier")
            .withPrice("654321")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("condo").build();
    public static final Property P_CARL = new PropertyBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withPrice("123456").build();
    public static final Property P_DANIEL = new PropertyBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withPrice("234561").withTags("friends").build();
    public static final Property P_ELLE = new PropertyBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withPrice("345612").build();
    public static final Property P_FIONA = new PropertyBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withPrice("456123").build();
    public static final Property P_GEORGE = new PropertyBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withPrice("561234").build();

    // Manually added
    public static final Property P_HOON = new PropertyBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Property P_IDA = new PropertyBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Property's details found in {@code CommandTestUtil}
    public static final Property P_AMY = new PropertyBuilder().withName(VALID_NAME_AMY).withSeller(VALID_SELLER_AMY)
            .withPrice(VALID_PRICE_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Property P_BOB = new PropertyBuilder().withName(VALID_NAME_BOB).withSeller(VALID_SELLER_BOB)
            .withPrice(VALID_PRICE_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalProperties() {} // prevents instantiation

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(P_ALICE, P_BENSON, P_CARL, P_DANIEL, P_ELLE, P_FIONA, P_GEORGE));
    }

    public static List<Property> getTypicalPropertiesSortedNameAsc() {
        return new ArrayList<>(Arrays.asList(P_CARL, P_DANIEL, P_ELLE, P_FIONA, P_GEORGE, P_ALICE, P_BENSON));
    }

    public static List<Property> getTypicalPropertiesSortedNameDesc() {
        return new ArrayList<>(Arrays.asList(P_BENSON, P_ALICE, P_GEORGE, P_FIONA, P_ELLE, P_DANIEL, P_CARL));
    }

    public static List<Property> getTypicalPropertiesSortedPriceAsc() {
        return new ArrayList<>(Arrays.asList(P_CARL, P_DANIEL, P_ELLE, P_FIONA, P_GEORGE, P_ALICE, P_BENSON));
    }

    public static List<Property> getTypicalPropertiesSortedPriceDesc() {
        return new ArrayList<>(Arrays.asList(P_ALICE, P_BENSON, P_GEORGE, P_FIONA, P_ELLE, P_DANIEL, P_CARL));
    }
}
