package test;

import entity.Address;
import entity.User;
import use_case.manage_address.*;

import java.util.*;

public class ManageAddressTest {

    public static void main(String[] args) {
        System.out.println("Manage Address Use Case Tests");

        testAddAddressSuccess();
        testAddAddressValidationError();
        testAddAddressUserNotFound();

        testEditAddressSuccess();
        testEditAddressAddressNotFound();

        testDeleteAddressSuccess();
        testDeleteAddressNotFound();

        System.out.println(">>> All ManageAddress tests finished without assertion failures.");
    }


    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException("Assertion failed: " + message);
        }
    }

    private static void testAddAddressSuccess() {
        System.out.println("[Test] AddAddressInteractor - success case");

        InMemoryUserDataAccess userData = new InMemoryUserDataAccess();
        User user = new User("alice", "alice@example.com", "password123", "123 Initial St, Toronto");
        userData.saveUser(user);

        TestAddPresenter presenter = new TestAddPresenter();
        AddAddressInteractor interactor = new AddAddressInteractor(userData, presenter);

        AddAddressInputData input = new AddAddressInputData(
                "alice",
                "456 New St",
                "Unit 789",
                "Toronto",
                "ON",
                "M5G 2C3",
                "Canada",
                true,
                false
        );

        interactor.execute(input);

        assertTrue(presenter.successCalled, "AddAddress success view should be called.");
        assertTrue(!presenter.validationCalled, "AddAddress validation error should NOT be called.");
        assertTrue(!presenter.userNotFoundCalled, "AddAddress user-not-found should NOT be called.");

        User updated = userData.getUser("alice");
        assertTrue(updated != null, "User 'alice' should still exist.");
        assertTrue(updated.getBillingAddresses().size() == 2,
                "User should have 2 addresses after adding a new one.");

        System.out.println("  -> OK");
    }

    private static void testAddAddressValidationError() {
        System.out.println("[Test] AddAddressInteractor - validation error");

        InMemoryUserDataAccess userData = new InMemoryUserDataAccess();
        User user = new User("bob", "bob@example.com", "password123", "1 First St");
        userData.saveUser(user);

        TestAddPresenter presenter = new TestAddPresenter();
        AddAddressInteractor interactor = new AddAddressInteractor(userData, presenter);

        AddAddressInputData badInput = new AddAddressInputData(
                "bob",
                "",
                "Unit 10",
                "Toronto",
                "ON",
                "M5G 2C3",
                "Canada",
                false,
                false
        );

        interactor.execute(badInput);

        assertTrue(!presenter.successCalled, "AddAddress success should NOT be called.");
        assertTrue(presenter.validationCalled, "AddAddress validation error SHOULD be called.");
        assertTrue(presenter.validationErrors.containsKey("line1"),
                "Validation errors should contain 'line1'.");

        System.out.println("  -> OK");
    }
