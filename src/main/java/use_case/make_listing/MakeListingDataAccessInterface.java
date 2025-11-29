package use_case.make_listing;

import entity.Product;

public interface MakeListingDataAccessInterface {
    void postListing(Product product);
}