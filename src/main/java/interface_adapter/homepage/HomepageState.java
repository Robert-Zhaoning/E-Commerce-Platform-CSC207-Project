package interface_adapter.homepage;

import entity.Product;

import java.util.List;

public class HomepageState {
    private String username;
    private String filter;
    private List<Product> products;

    public HomepageState(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return this.filter;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }
}