package interface_adapter.sign_up;

import interface_adapter.ViewModel;

/**
 * This class extends the ViewModel and initialise it for the signup use case
 * */
public class SignUpViewModel extends ViewModel {

    /**
     * Creates a SignUpViewModel object for the updating the SignUpView
     * */
    public SignUpViewModel(){
        super("Sign up");
        this.setState(new SignUpState());
    }
}
