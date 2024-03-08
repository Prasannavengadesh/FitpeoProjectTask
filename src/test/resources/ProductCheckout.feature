Feature: To verify that the product checkout process in Flipkart

	@Sanity
  Scenario: To verify that user can checkout the products
    When To launch the flipkart application and Verify that homepage loads successfully
    And To search product by providing product name
    And To click any one of the product in product list page
    And To add the product into cart
    And To Verify that the correct item is in the cart
    And To check out the product from cart
    And To login the flipkart application using valid credentials
