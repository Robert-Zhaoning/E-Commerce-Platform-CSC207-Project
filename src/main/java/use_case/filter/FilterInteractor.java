package use_case.filter;

import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class FilterInteractor implements FilterInputBoundary{
    private FilterOutputBoundary filterPresenter;
    private FilterDataAccessInterface dataAccess;

    public FilterInteractor(FilterOutputBoundary filterPresenter, FilterDataAccessInterface dataAccess){
        this.filterPresenter = filterPresenter;
        this.dataAccess = dataAccess;
    }

    public void execute(FilterInputData filterInputData){
        List<Product> allProducts = dataAccess.getAllProducts();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product: allProducts){
            if (product.getCategory().equals(filterInputData.getFilter())){
                filteredProducts.add(product);
            }
        }
        FilterOutputData filterOutputData = new FilterOutputData(filteredProducts);
        this.filterPresenter.updateFilteredProducts(filterOutputData);
    }

    public void switchToHomepageView(){
        this.filterPresenter.switchToHomepageView();
    }
}
