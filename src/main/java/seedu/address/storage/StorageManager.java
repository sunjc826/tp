package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Buyer;
import seedu.address.model.property.Property;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Import/ Export Csv methods ==============================

    /**
     * Exports properties to csv file.
     */
    public void exportProperties(ReadOnlyAddressBook addressBook, File file) throws IOException {
        requireAllNonNull(file, addressBook);
        List<Property> properties = addressBook.getPropertyList();
        CSVWriter writer = new CSVWriter(new FileWriter(file));
        String[] header = {"Name", "Address", "Seller Name", "Phone", "Email", "Price", "Tags"};
        writer.writeNext(header);
        for (Property property : properties) {
            String tagString = property.getTags().stream()
                    .map(x -> x.tagName)
                    .collect(Collectors.joining(","));
            String[] entries = {property.getName().toString(), property.getAddress().toString(),
                    property.getSeller().getName().toString(), property.getSeller().getPhone().toString(),
                    property.getSeller().getEmail().toString(), property.getPrice().toString(), tagString};
            writer.writeNext(entries);
        }
        writer.close();
    }

    /**
     * Exports buyers to csv file.
     */
    public void exportBuyers(ReadOnlyAddressBook addressBook, File file) throws IOException {
        requireAllNonNull(file, addressBook);
        List<Buyer> buyers = addressBook.getBuyerList();
        CSVWriter writer = new CSVWriter(new FileWriter(file));
        String[] header = {"Name", "Phone", "Email", "Budget", "Tags"};
        writer.writeNext(header);
        for (Buyer buyer : buyers) {
            String tagString = buyer.getTags().stream()
                    .map(x -> x.tagName)
                    .collect(Collectors.joining(","));
            String[] entries = {buyer.getName().toString(), buyer.getPhone().toString(),
                    buyer.getEmail().toString(), buyer.getMaxPrice().toString(), tagString};
            writer.writeNext(entries);
        }
        writer.close();
    }
}
