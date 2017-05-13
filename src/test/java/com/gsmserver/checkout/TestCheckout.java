package com.gsmserver.checkout;


import checkout.CheckOutObjects;
import com.gsmserver.AbstractObjects;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class TestCheckout extends AbstractObjects {

    private Integer cartItems = 0;

    @Test(priority=1)
    public void checkUrlAndPageTitle () {
        CheckOutObjects checkOut = page(CheckOutObjects.class);
        open(baseUrl);
        checkOut.checkCurrentUrl(baseUrl, GSM_MAIN_PAGE_TITLE);
    }

    @Test(priority=2)
    public void TestProductExistAndAddHimToCart () {
        CheckOutObjects checkOut = page(CheckOutObjects.class);
        checkOut.checkElementNotVisible(checkOut.basketWithProduct);
        checkOut.checkElementHasAttributeValue(checkOut.cart, checkOut.cartAttribute, cartItems.toString());
        checkOut.clickOnElement(checkOut.thirdProduct);
        cartItems ++;
        checkOut.checkElementVisible(checkOut.basketWithProduct);
        refresh();
        checkOut.checkElementHasAttributeValue(checkOut.cart, checkOut.cartAttribute, cartItems.toString());
        checkOut.clickOnElement(checkOut.decreaseCount);
        checkOut.checkElementVisible(checkOut.thirdProduct);
        cartItems --;
        refresh();
        checkOut.checkElementHasAttributeValue(checkOut.cart, checkOut.cartAttribute, cartItems.toString());
        checkOut.checkElementNotVisible(checkOut.basketWithProduct);
        checkOut.clickOnElement(checkOut.thirdProduct);
        cartItems ++;

    }

    private void searchProductByCodeAndAddHimToCart (String code, Integer count) {
        CheckOutObjects checkOut = page(CheckOutObjects.class);
        checkOut.insertTextAndSubmit(checkOut.searchField, code);
        checkOut.clickOnElement(checkOut.addToCart);
        cartItems ++;
        checkOut.checkElementHasAttributeValue(checkOut.cart, checkOut.cartAttribute, cartItems.toString());
        checkOut.checkElementVisible(checkOut.increaseCount);
        for (Integer i=0; i<count-1; i++) {
            checkOut.clickOnElement(checkOut.increaseCount);
            cartItems ++;
        }
        checkOut.checkElementHasAttributeValue(checkOut.cart, checkOut.cartAttribute, cartItems.toString());

    }
    @Test(priority=3)
    public void addProductsByCodes (){
        this.searchProductByCodeAndAddHimToCart("847372", 2);
        this.searchProductByCodeAndAddHimToCart("849356", 5);
    }

    @Test(priority=4)
    public void checkCart (){
        CheckOutObjects checkOut = page(CheckOutObjects.class);
        checkOut.checkElementHasAttributeValue(checkOut.cart, checkOut.cartAttribute, "8");
        checkOut.clickOnElement(checkOut.cart);
        checkOut.checkElementVisible(checkOut.productSpecialPriceExist);
        checkOut.checkElementNotVisible(checkOut.productSpecialPriceNotExist);
        checkOut.clickOnElement(checkOut.proceedToCheckout);
        checkOut.clickOnElement(checkOut.buyWithoutRegistration);
        checkOut.clickOnElement(checkOut.nextStep);
        checkOut.checkElementVisible(checkOut.contactInfoLable);

    }

}
