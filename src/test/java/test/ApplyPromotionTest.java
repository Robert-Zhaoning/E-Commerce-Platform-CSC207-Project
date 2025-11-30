package test;

import entity.Cart;
import entity.CartItem;
import entity.Product;
import entity.User;
import use_case.apply_promotion.*;

import java.util.HashMap;
import java.util.Map;

public class ApplyPromotionInteractorTest {

    public static void main(String[] args) {

        System.out.println("=== ApplyPromotionInteractor Manual Tests ===");

        testEmptyCode();
        System.out.println("------------------------------------------------");

        testCartNotFound();
        System.out.println("------------------------------------------------");

        testEmptyCartSubtotal();
        System.out.println("------------------------------------------------");

        testInvalidCode();
        System.out.println("------------------------------------------------");

        System.out.println("Tests finished. Check console output above.");
    }

    /**
     * Case 1: promo code is empty string.
     * Expected: presenter.prepareFailView("Promo code cannot be empty.")
     */
    private static void testEmptyCode() {
        System.out.println("[Test] Empty promo code");

        FakeCartDataAccess cartData = new FakeCartDataAccess();
        FakePromotionDataAccess promoData = new FakePromotionDataAccess();
        FakePresenter presenter = new FakePresenter();

        ApplyPromotionInteractor interactor =
                new ApplyPromotionInteractor(cartData, promoData, presenter);

        ApplyPromotionInputData input =
                new ApplyPromotionInputData("alice", "   ");

        interactor.execute(input);

        System.out.println("Expected error: Promo code cannot be empty.");
        System.out.println("Actual error  : " + presenter.lastErrorMessage);
    }

    /**
     * Case 2: cart not found for this user.
     * Expected: presenter.prepareFailView("Cart not found for user: bob")
     */
    private static void testCartNotFound() {
        System.out.println("[Test] Cart not found");

        FakeCartDataAccess cartData = new FakeCartDataAccess(); // no carts stored
        FakePromotionDataAccess promoData = new FakePromotionDataAccess();
        FakePresenter presenter = new FakePresenter();

        ApplyPromotionInteractor interactor =
                new ApplyPromotionInteractor(cartData, promoData, presenter);

        ApplyPromotionInputData input =
                new ApplyPromotionInputData("bob", "SAVE10");

        interactor.execute(input);

        System.out.println("Expected error: Cart not found for user: bob");
        System.out.println("Actual error  : " + presenter.lastErrorMessage);
    }

    /**
     * Case 3: cart exists but subtotal is zero (empty cart).
     * Expected: presenter.prepareFailView("Your cart is empty.")
     */
    private static void testEmptyCartSubtotal() {
        System.out.println("[Test] Empty cart subtotal");

        FakeCartDataAccess cartData = new FakeCartDataAccess();
        FakePromotionDataAccess promoData = new FakePromotionDataAccess();
        FakePresenter presenter = new FakePresenter();

        User user = new User("charlie", "c@example.com", "password", "Address 1");
        Cart emptyCart = new FakeCartWithSubtotal(user, 0.0);
        cartData.saveCart("charlie", emptyCart);

        ApplyPromotionInteractor interactor =
                new ApplyPromotionInteractor(cartData, promoData, presenter);

        ApplyPromotionInputData input =
                new ApplyPromotionInputData("charlie", "SAVE10");

        interactor.execute(input);

        System.out.println("Expected error: Your cart is empty.");
        System.out.println("Actual error  : " + presenter.lastErrorMessage);
    }

    /**
     * Case 4: cart has items and subtotal > 0, but promo code is not found
     * in the promotion repository.
     * Expected: presenter.prepareFailView("Invalid or unknown promo code.")
     */
    private static void testInvalidCode() {
        System.out.println("[Test] Invalid promo code");

        FakeCartDataAccess cartData = new FakeCartDataAccess();
        FakePromotionDataAccess promoData = new FakePromotionDataAccess();
        FakePresenter presenter = new FakePresenter();

        User user = new User("dana", "d@example.com", "password", "Address 1");
        Cart cart = new Cart(user);

        Product p = new Product(1, "Laptop", "Electronics", 1000.0, 5);
        cart.addProduct(p, 1);

        cartData.saveCart("dana", cart);

        ApplyPromotionInteractor interactor =
                new ApplyPromotionInteractor(cartData, promoData, presenter);

        ApplyPromotionInputData input =
                new ApplyPromotionInputData("dana", "UNKNOWN_CODE");

        interactor.execute(input);

        System.out.println("Cart subtotal > 0; promo code UNKNOWN_CODE not in DB.");
        System.out.println("Expected error: Invalid or unknown promo code.");
        System.out.println("Actual error  : " + presenter.lastErrorMessage);
    }
