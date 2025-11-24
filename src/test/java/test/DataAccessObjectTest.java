package test;

import entity.*;

import data_access.*;

public class DataAccessObjectTest {
    public static void main(String[] args) {
        DataAccessObject dao = new DataAccessObject();

        System.out.println("Get user test:");
        User retrievedUser = dao.getUser("John Doe");
        if (retrievedUser != null) {
            System.out.println("Retrieved user: " + retrievedUser.getUsername());
        } else {
            System.out.println("User not found.");
        }
        
        
        // System.out.println("Post address test:");
        // Address address = new Address(
        //     "John Doe",
        //     "123 Main St",
        //     "Apt 4B",
        //     "New York",
        //     "NY",
        //     "10001",
        //     "USA",
        //     true,
        //     true
        // );
        // boolean success = dao.postAddress(address);
        // System.out.println("Address posted successfully: " + success);
        

        System.out.println("Get address test:");
        Address[] retrievedAddresses = dao.getAddresses("John Doe");
        if (retrievedAddresses != null) {
            for (Address retrievedAddress : retrievedAddresses) {
                System.out.println("Retrieved address: " + retrievedAddress.toSingleLine());
            }
        } else {
            System.out.println("No addresses found.");
        }

    }
}
