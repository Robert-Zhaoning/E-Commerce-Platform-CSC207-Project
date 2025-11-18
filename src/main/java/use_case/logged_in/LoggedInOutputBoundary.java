package use_case.logged_in;

/**
 * The output boundary interface for the logged-in use case.
 */
public interface LoggedInOutputBoundary {
    
    void switchToMakeListingView();

    void switchToLoginView();

    void switchToAddToCartView();

    // Add other view switching methods as needed

}