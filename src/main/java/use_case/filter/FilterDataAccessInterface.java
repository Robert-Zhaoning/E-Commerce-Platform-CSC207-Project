package use_case.filter;

import entity.Product;

import java.util.List;

public interface FilterDataAccessInterface {
    List<Product> getAllProducts();
}
