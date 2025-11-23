package interface_adapter.Product;

import entity.Product;
import use_case.open_product.OpenProductInputBoundary;
import use_case.open_product.OpenProductInputData;
/**
 * This class represents the controller for the product use case
 * */
public class ProductController {
    private OpenProductInputBoundary openProductUseCaseInteractor;

    public ProductController(OpenProductInputBoundary openProductUseCaseInteractor) {
        this.openProductUseCaseInteractor = openProductUseCaseInteractor;
    }
    /**
     * Executes the Product Use Case.
     * @param productid the product that the user clicks on
     */
    public void execute(Integer productid){
        final OpenProductInputData openProductInputData = new OpenProductInputData(productid);
        openProductUseCaseInteractor.execute(openProductInputData);
    }
}
