package use_case.add_to_cart;

import entity.User;

public class AddToCartInteractor implements AddToCartInputBoundary {
    private final AddToCartUserDataAccessInterface userDataAccessObject;
    private final AddToCartOutputBoundary userPresenter;


    @Override
    public void execute(AddToCartInputData addToCartInputData) {}
}
