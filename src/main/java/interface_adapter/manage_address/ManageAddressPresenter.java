package interface_adapter.manage_address;

import entity.Address;
import use_case.manage_address.*;

import java.util.List;
import java.util.Map;

/**
 * Presenter converts use case output data into a state object for the ViewModel.
 */
public class ManageAddressPresenter implements
        AddAddressOutputBoundary,
        EditAddressOutputBoundary,
        DeleteAddressOutputBoundary {

    private final ManageAddressViewModel viewModel;

    public ManageAddressPresenter(ManageAddressViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(AddAddressOutputData outputData) {
        updateAddresses(outputData.getUsername(), outputData.getAddresses(), null, "Address added.");
    }

    @Override
    public void prepareFailView(Map<String, String> errors) {
        ManageAddressState state = viewModel.getState();
        state.setFieldErrors(errors);
        state.setMessage("Validation errors.");
        viewModel.setState(state);
    }

    @Override
    public void prepareUserNotFoundView(String message) {
        ManageAddressState state = viewModel.getState();
        state.setMessage(message);
        viewModel.setState(state);
    }

    @Override
    public void prepareSuccessView(EditAddressOutputData outputData) {
        updateAddresses(outputData.getUsername(), outputData.getAddresses(), null, "Address updated.");
    }

    @Override
    public void prepareFailView(Map<String, String> errors) {
        ManageAddressState state = viewModel.getState();
        state.setFieldErrors(errors);
        state.setMessage("Validation errors.");
        viewModel.setState(state);
    }

    @Override
    public void prepareNotFoundView(String message) {
        ManageAddressState state = viewModel.getState();
        state.setMessage(message);
        viewModel.setState(state);
    }

    @Override
    public void prepareSuccessView(DeleteAddressOutputData outputData) {
        ManageAddressState state = viewModel.getState();
        state.setMessage("Address deleted: " + outputData.getDeletedAddressId());
        viewModel.setState(state);
    }

    private void updateAddresses(String username, List<Address> addresses,
                                 Map<String, String> errors, String message) {
        ManageAddressState state = viewModel.getState();
        state.setUsername(username);
        state.setAddresses(addresses);
        state.setFieldErrors(errors);
        state.setMessage(message);
        viewModel.setState(state);
    }
}