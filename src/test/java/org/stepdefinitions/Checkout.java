package org.stepdefinitions;

import java.awt.AWTException;
import java.io.IOException;

import org.base.LibGlobal;
import org.junit.Assert;
import org.pojo.CheckoutPOJO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Checkout extends LibGlobal{
	
	CheckoutPOJO p;

	@When("To launch the flipkart application and verify that homepage loads successfully")
	public void to_launch_the_flipkart_application_and_verify_that_homepage_loads_successfully() throws IOException {
	     
		urlLaunch("https://www.flipkart.com/"); 
		implWaits();
		Assert.assertEquals("User not successfully entered into homepage", getUrl(), "https://www.flipkart.com/");	
	}

	@When("To search product by providing product name")
	public void to_search_product_by_providing_product_name() throws IOException, AWTException {
	    
		p = new CheckoutPOJO();
		sendText(p.getSearchBox(), readDataFromExcel(1,0)); 
		pressEnter(); 
	}

	@When("To click any one of the product in product list page")
	public void to_click_any_one_of_the_product_in_product_list_page() {
	    
		clickButton(p.getSelectProduct());
	}

	@When("To add the product into cart")
	public void to_add_the_product_into_cart() {
		
	    switchWin(1);
	    implWaits();
	    clickButton(p.getAddToCart());
	}

	@When("To Verify that the correct item is in the cart")
	public void to_Verify_that_the_correct_item_is_in_the_cart() {
	    
		Assert.assertTrue("Invalid product added", p.getVerifyProductInCart().getText().contains("Laptop"));
	}

	@When("To check out the product from cart")
	public void to_check_out_the_product_from_cart() {
	    
		clickButton(p.getPlaceOrder());
	}

	@When("To login the flipkart application using valid credentials")
	public void to_login_the_flipkart_application_using_valid_credentials() throws IOException {
	    
		sendText(p.getLoginSearchBox(), readDataFromExcel(1,1)); 
		clickButton(p.getContinueBtn());
		
		// because of many time signed into application its showing message like Somethings wrong
		
		clickButton(p.getLogin());
		clickButton(p.getAddAddress());
		passTextUsingJs(p.getName(), readDataFromExcel(1,2));
		passTextUsingJs(p.getPhNo(), readDataFromExcel(1,1));
		passTextUsingJs(p.getPinCode(), readDataFromExcel(1,3));
		passTextUsingJs(p.getLocality(), readDataFromExcel(1,4));
		passTextUsingJs(p.getAddress(), readDataFromExcel(1,5));
		clickButton(p.getSelectHome());
		clickButton(p.getSaveAddress());
		
		Assert.assertTrue("This is invaid product", p.getOrderSummary().getText().contains("Laptop"));
		clickButton(p.getSumContinue());
		clickButton(p.getAccept());
	}
	

}
