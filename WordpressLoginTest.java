
package com.vuhung.wordpress;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;

import net.serenitybdd.screenplay.abilities.*;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
@DefaultUrl("http://localhost:8000/wp-login.php")
public class WordpressLoginTest {
	
    public static Target USERNAME_FIELD = Target.the("Username field").locatedBy("#user_login");
    public static Target PASSWORD_FIELD = Target.the("Password field").locatedBy("#user_pass");

    @Managed
    WebDriver driver;

    @Steps
    Actor vu = Actor.named("Vu Hung");

    @Test
    public void shouldBeAbleToLoginToWordPress() {
    	vu.can(BrowseTheWeb.with(driver));
    	
    	// login first 
        vu.attemptsTo(
                Open.url("http://localhost:8000/wp-login.php"),
                Login.withCredentials("vuhung", "vuhung")
        );
        
        // verify that the title of the page is correct (the Wordpress dashboard page 
        vu.attemptsTo(
        		Ensure.thatTheCurrentPage().title().contains("Dashboard ‹ oz wordpress — WordPress")
        		);
    }

    // Screenplay task for login
    static class Login implements Task {
        private final String username;
        private final String password;

        private Login(String username, String password) {
            this.username = username;
            this.password = password;
        }

        static Login withCredentials(String username, String password) {
            return new Login(username, password);
        }

        @Override
        public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    Enter.theValue(username).into(Target.the("Username field").locatedBy("#user_login")),
                    Enter.theValue(password).into(Target.the("Password field").locatedBy("#user_pass")),
                    Click.on(Target.the("Login button").locatedBy("#wp-submit"))
            );
        }
    }
    
    @Test
    public void shouldBeAbleToLoginToWordPressAsStandardUser() {
    	
    	
        vu.can(BrowseTheWeb.with(driver));
        
        vu.attemptsTo(
                Open.url("http://localhost:8000/wp-login.php"),
                Ensure.that(Target.the("Username field").locatedBy("#user_login")).isDisplayed(),
                Ensure.that(Target.the("Password field").locatedBy("#user_pass")).isDisplayed(),
                Ensure.that(Target.the("Login button").locatedBy("#wp-submit")).isDisplayed()
                );
        
        vu.attemptsTo( 
                Click.on("#user_login"),
                Enter.theValue("vuhung").into(USERNAME_FIELD)
                ); 
        
        vu.attemptsTo(
        		Click.on("#user_pass"), 
        		Enter.theValue("vuhung").into(PASSWORD_FIELD)
        		); 
        
        vu.attemptsTo(
        		Click.on("#wp-submit")
        		); 
        
        
        // verify that the title of the page is correct (the Wordpress dashboard page 
        vu.attemptsTo(
        		Ensure.thatTheCurrentPage().title().contains("Dashboard ‹ oz wordpress — WordPress")
        		);
       

    }
}
