package interface_adapter.Product;

import interface_adapter.ViewModel;
import interface_adapter.filter.FilterState;

public class ProductViewModel extends ViewModel<FilterState> {
    /**
     * Creates a FilterViewModel object to update the filter view
     * */
    public ProductViewModel(){
        super("Product");
        setState(new FilterState());
    }
}
