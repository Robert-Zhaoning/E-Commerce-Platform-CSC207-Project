package interface_adapter.sign_up;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.sign_up.SignUpOutputBoundary;
import use_case.sign_up.SignUpOutputData;

/**
 * This class unwraps the formatted output data from the SignUpInteractor into raw data for the SignUpView.
 * SignUpPresenter contains a SignUpViewModel and a SignUpState.
 * */
public class SignUpPresenter implements SignUpOutputBoundary{
    private SignUpViewModel signUpViewModel;
    private SignUpState signUpState;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private LoggedOutViewModel loggedOutViewModel;
    private LoggedInViewModel loggedInViewModel;

    /**
     * Creates a SignUpPresenter object to unwrap the formatted output data of SignUpInteractor.
     * @param signUpViewModel the view model for signup use case
     * @param signUpState the state for the signup use case
     * @param viewManagerModel the view manager model for the app
     * @param loginViewModel the view model for the log in view
     * @param loggedOutViewModel the view model for the logged out view
     * @param loggedInViewModel the view model for the logged in view
     * */
    public SignUpPresenter(SignUpViewModel signUpViewModel, SignUpState signUpState, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedOutViewModel loggedOutViewModel, LoggedInViewModel loggedInViewModel){
        this.signUpViewModel = signUpViewModel;
        this.signUpState = signUpState;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.loggedOutViewModel = loggedOutViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * Updates the SignUpView to show that the process was successful.
     * @param outputData the output data of the SignUpInteractor
     * */
    public void updateSuccess(SignUpOutputData outputData){
        this.signUpState.setSuccess(outputData.getUser().getUsername());
        this.signUpState.setFailure(null);
        this.signUpViewModel.setState(signUpState);
    }

    /**
     * Updates the SignUpView to show that the process has failed.
     * @param outputData the output data of the SignUpInteractor
     * */
    public void updateFailure(SignUpOutputData outputData) {
        this.signUpState.setSuccess(null);
        this.signUpState.setFailure(outputData.getError());
        this.signUpViewModel.setState(signUpState);
    }

    /**
     * Switches to log in view
     * */
    public void switchToLoginView(){
        this.signUpState.setSuccess(null);
        this.signUpState.setFailure(null);
        this.viewManagerModel.setActiveViewName(loginViewModel.getViewName());
    }

    /**
     * Switches to logged out view
     * */
    public void switchToLoggedOutView(){
        this.signUpState.setSuccess(null);
        this.signUpState.setFailure(null);
        this.viewManagerModel.setActiveViewName(loggedOutViewModel.getViewName());
    }

    /**
     * Switches to logged in view
     * */
    public void switchToLoggedInView(){
        this.signUpState.setSuccess(null);
        this.signUpState.setFailure(null);
        this.viewManagerModel.setActiveViewName(loggedInViewModel.getViewName());
    }
}
