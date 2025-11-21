package use_case.search;

public class SearchInteractor implements SearchInputBoundary{
    private SearchOutputBoundary searchPresenter;
    private SearchDataAccessInterface dataAccess;
    public SearchInteractor(SearchOutputBoundary searchPresenter, SearchDataAccessInterface dataAccess) {
        this.searchPresenter = searchPresenter;
        this.dataAccess = dataAccess;
    }
    public void execute(SearchInputData searchInputData) {

    }

    @Override
    public void switchToLoggedInView() {
        this.searchPresenter.switchToLoggedInView();
    }

    @Override
    public void switchToLoggedOutView() {
        this.searchPresenter.switchToLoggedOutView();
    }
}
