package use_case.make_listing;

import entity.Product;

public class MakeListingOutputData {
    private final Product product;
    private final String message;

    public MakeListingOutputData(Product product, String message) {
        this.product = product;
        this.message = message;
    }
    public Product getProduct() {
        return product;
    }
    public String getMessage() {
        return message;
    }
}