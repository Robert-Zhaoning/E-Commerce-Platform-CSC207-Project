package view;

import interface_adapter.homepage.HomepageState;
import interface_adapter.homepage.HomepageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * This class creates the view for the homepage use case.
 * It contains a view name, a HomepageViewModel and a HomepageController.
 * */
public class HomepageView extends JPanel implements PropertyChangeListener {
    private final String homepageViewName = "Homepage";
    private HomepageViewModel homepageViewModel;
    private HomepageController homepageController;
    private JPanel variablePanel = new JPanel();

    /**
     * Creates a HomepageView object for the homepage use case.
     * @param homepageViewModel the view model for the homepage use case
     * */
    public HomepageView(HomepageViewModel homepageViewModel){
        this.homepageViewModel = homepageViewModel;
        this.homepageViewModel.addPropertyChangeListener(this);

        JPanel buttonsLayerTwoPanel = new JPanel();
        JButton dealsButton = new JButton("Daily Deals");
        JButton searchButton = new JButton("Search");
        JButton filterButton = new JButton("Apply Filters");
        buttonsLayerTwoPanel.add(dealsButton);
        buttonsLayerTwoPanel.add(searchButton);
        buttonsLayerTwoPanel.add(filterButton);

        JPanel productOnePanel = new JPanel();
        JLabel productOneLabel = new JLabel("Product One");
        JLabel productOnePriceLabel = new JLabel("$1.99");
        JLabel productOneCompanyLabel = new JLabel("Product One Company");
        JLabel productOneCategoryLabel = new JLabel("Product One Category");
        JButton productOneInfoButton = new JButton("Info");
        JButton productOneCartButton = new JButton("Add to Cart");
        productOnePanel.add(productOneLabel);
        productOnePanel.add(productOneCompanyLabel);
        productOnePanel.add(productOneCategoryLabel);
        productOnePanel.add(productOnePriceLabel);
        productOnePanel.add(productOneInfoButton);
        productOnePanel.add(productOneCartButton);

        JPanel productTwoPanel = new JPanel();
        JLabel productTwoLabel = new JLabel("Product Two");
        JLabel productTwoPriceLabel = new JLabel("$10.99");
        JLabel productTwoCompanyLabel = new JLabel("Product Two Company");
        JLabel productTwoCategoryLabel = new JLabel("Product Two Category");
        JButton productTwoInfoButton = new JButton("Info");
        JButton productTwoCartButton = new JButton("Add to Cart");
        productTwoPanel.add(productTwoLabel);
        productTwoPanel.add(productTwoCompanyLabel);
        productTwoPanel.add(productTwoCategoryLabel);
        productTwoPanel.add(productTwoPriceLabel);
        productTwoPanel.add(productTwoInfoButton);
        productTwoPanel.add(productTwoCartButton);

        JPanel productThreePanel = new JPanel();
        JLabel productThreeLabel = new JLabel("Product Two");
        JLabel productThreePriceLabel = new JLabel("$18.99");
        JLabel productThreeCompanyLabel = new JLabel("Product Two Company");
        JLabel productThreeCategoryLabel = new JLabel("Product Two Category");
        JButton productThreeInfoButton = new JButton("Info");
        JButton productThreeCartButton = new JButton("Add to Cart");
        productThreePanel.add(productThreeLabel);
        productThreePanel.add(productThreeCompanyLabel);
        productThreePanel.add(productThreeCategoryLabel);
        productThreePanel.add(productThreePriceLabel);
        productThreePanel.add(productThreeInfoButton);
        productThreePanel.add(productThreeCartButton);

        JPanel productShowcasePanel = new JPanel();
        productShowcasePanel.setLayout(new BoxLayout(productShowcasePanel, BoxLayout.Y_AXIS));
        productShowcasePanel.add(productOnePanel);
        productShowcasePanel.add(productTwoPanel);
        productShowcasePanel.add(productThreePanel);
        JScrollPane productShowcaseScroll = new JScrollPane(productShowcasePanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(variablePanel);
        mainPanel.add(buttonsLayerTwoPanel);
        mainPanel.add(productShowcaseScroll);

    }

    /**
     * Sets the controller for the homepage use case.
     * @param homepageController the controller for homepage use case
     * */
    public void setController(HomepageController homepageController){
        this.homepageController = homepageController;
    }

    /**
     * Listens to the event of the property change to updates the view accordingly
     * @param evt the event of the property change
     * */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomepageState homepageState = (HomepageState) evt.getNewValue();
        if (homepageState.getUsername() != null){
            this.homepageController.switchToHomepageView();
            JPanel usernamePanel = new JPanel();
            usernamePanel.add(new JLabel(homepageState.getUsername()));
            JPanel buttonsLayerOnePanel = new JPanel();
            JButton listingButton = new JButton("Create Listing");
            JButton addressButton = new JButton("Manage Addresses");
            JButton cartButton = new JButton("Cart");
            JButton fundButton = new JButton("Add Funds");
            JButton logoutButton = new JButton("Logout");
            buttonsLayerOnePanel.add(listingButton);
            buttonsLayerOnePanel.add(addressButton);
            buttonsLayerOnePanel.add(fundButton);
            buttonsLayerOnePanel.add(cartButton);
            buttonsLayerOnePanel.add(logoutButton);
            this.variablePanel.setLayout(new BoxLayout(this.variablePanel, BoxLayout.Y_AXIS));
            this.variablePanel.add(usernamePanel);
            this.variablePanel.add(buttonsLayerOnePanel);
        }
        if (signUpState.getFailure() != null){
            this.errorLabel.setText(signUpState.getFailure());
        } else {
            this.errorLabel.setText("");
        }
    }

    public String getViewName(){
        return this.signUpViewName;
    }
}
