package app.tests;

import app.DatabaseManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by thomas on 2017.04.24..
 */
class DatabaseManagerTest {
    @Test
    void getInstance() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();

    }

}