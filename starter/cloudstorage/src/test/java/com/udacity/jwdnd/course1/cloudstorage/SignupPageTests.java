package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPageTests {
    @FindBy(id = "inputFirstName")
    private WebElement firstNameElement;

    @FindBy(id = "inputLastName")
    private WebElement lastNameElement;

    @FindBy(id = "inputUsername")
    private WebElement usernameElement;

    @FindBy(id = "inputPassword")
    private WebElement passwordElement;

    @FindBy(id = "signup-button")
    private WebElement submitElement;

    public SignupPageTests(WebDriver driver) { PageFactory.initElements(driver, this); }

    public void signUpTest(String firstName, String lastName, String username, String password) {
        this.firstNameElement.sendKeys(firstName);
        this.lastNameElement.sendKeys(lastName);
        this.usernameElement.sendKeys(username);
        this.passwordElement.sendKeys(password);
        this.submitElement.click();
    }
}
