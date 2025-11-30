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
