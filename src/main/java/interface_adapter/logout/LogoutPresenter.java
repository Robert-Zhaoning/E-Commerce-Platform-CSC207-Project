package interface_adapter.logout;

import interface_adapter.homepage.HomepageState;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           HomepageViewModel homepageViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        HomepageState loggedInState = this.homepageViewModel.getState();
        loggedInState.setUsername(null);
        homepageViewModel.setState(loggedInState);

        loginViewModel.setState(new LoginState());
        viewManagerModel.setActiveViewName(homepageViewModel.getViewName());
    }

    @Override
    public void switchToHomePage() {
        viewManagerModel.setActiveViewName(homepageViewModel.getViewName());
    }
}
