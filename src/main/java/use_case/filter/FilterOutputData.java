package use_case.filter;

import entity.Product;

import java.util.List;

public class FilterOutputData {
    private List<Product> filteredProducts;
    public FilterOutputData(List<Product> filteredProducts){
        this.filteredProducts = filteredProducts;
    }
    public List<Product> getFilteredProducts(){
        return filteredProducts;
    }
}
