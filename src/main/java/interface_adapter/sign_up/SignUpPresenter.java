package interface_adapter.sign_up;

import entity.User;
import use_case.sign_up.SignUpInputBoundary;
import use_case.sign_up.SignUpInputData;
import use_case.sign_up.SignUpOutputBoundary;

/**
 * This class on wrap
 * */
public class SignUpPresenter implements SignUpOutputBoundary{
    private User user;
    private String error;
    private SignUpViewModel signUpViewModel;
    private SignUpState signUpState;
    public SignUpPresenter(){
        this.user = null;
        this.error = null;
    }

    public void updateSuccess(SignUpOutputData outputData){
        this.signUpState = new SignUpState();
        this.signUpState.setState("Success", outputData.getUser());
        this.signUpViewModel = new SignUpViewModel(signUpState);
    }

    public void updateFailure(SignUpOutputData outputData) {
        this.signUpState = new SignUpState();
        this.signUpState.setState("Failure", outputData.getError());
        this.signUpViewModel = new SignUpViewModel(signUpState);
    }
}
