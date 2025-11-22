package interface_adapter.filter;

import use_case.filter.FilterInputBoundary;
import use_case.filter.FilterInputData;
import use_case.filter.FilterOutputData;

public class FilterController {
    private FilterInputBoundary filterInteractor;
    public FilterController(FilterInputBoundary filterInteractor){
        this.filterInteractor = filterInteractor;
    }
    public void execute(String filter){
        FilterInputData filterInputData = new FilterInputData(filter);
        this.filterInteractor.execute(filterInputData);
    }
    public void switchToHomepageView(){
        this.filterInteractor.switchToHomepageView();
    }
}
