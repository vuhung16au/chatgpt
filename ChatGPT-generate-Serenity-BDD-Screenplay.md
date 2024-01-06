# Overview 

How to generate Serenity BDD's Screenplay code to verify Wordpress login page.

# Takeaways
- Write the prompt as detailed as possible
- The prompt looks like a detail design document for the code
- Write everything you want in the prompt

# Notes
- The code ChatGPT generated does not work in the first place, we need to modify it a little bit
- ChatGPT is unaware or confusing about using `import`, we need to instruct it
- I have to revive the following prompt several times until it can reproduce the code I need
- Understand the problem, understand the technology (Serenity BDD, Screenplay in this case) is the key to write a good prompt 

# The prompt 

Here is a Wordpress 
http://localhost:8000/

The login page is 
http://localhost:8000/wp-login.php

In the login page above, there are 3 elements and they can be located with 

Enter username: findElement(By.id("user_login"))
Enter password: findElement(By.id("user_pass"))
"Login" submit button, click to submit and login into Wordpress: findElement(By.id("wp-submit"))

The correct username and password to login into Wordpress 
username: vuhung
password: vuhung

If logged in successfully, the user "vuhung" will be directed to "http://localhost:8000/wp-admin/"
The title of the page "http://localhost:8000/wp-admin/" should be "Dashboard ‹ oz wordpress — WordPress".
Otherwise, login is not successfully.

Please write a test program that login with username/password = vuhung/vuhung, using Serenity BDD's Screenplay pattern.
if we can login with username/password = vuhung/vuhung, the test is passed.

The language is Java. 

Use jUnit version 4. 

Create a setup() method @BeforeMethod(alwaysRun = true),
and in setup(), maximze the browser window. 

The package name: com.vuhung.wordpress 
The name of the method for testing: shouldBeAbleToLoginToWordPressAsStandardUser()

The actor name is "vu"
Check that The actor "vu" does have the ability to browse the URL "http://localhost:8000/wp-login.php"

Define a class "WordpressLoginTest" and use it.

Note that the correct "import" are
```
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
``` 
The code should also import 

```
import net.serenitybdd.screenplay.actions.Open;
```

# ChatGPT's Answer (first round)

# The main code that ChatGPT generated
(It doesn't run in the first place) 

```java
    @Test
    public void shouldBeAbleToLoginToWordPress() {
        vu.attemptsTo(
                Open.url("http://localhost:8000/wp-login.php"),
                Login.withCredentials("vuhung", "vuhung"),
                Ensure.that(Target.the("Dashboard page title").locatedBy("title"))
                        .text()
                        .contains("Dashboard ‹ oz wordpress — WordPress")
        );
    }
``` 

# Modified Code 
With a little modification so that the code can run, it becomes: 

```java
// File: src/test/java/com/vuhung/wordpress/WordpressLoginTest.java

package com.vuhung.wordpress;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

@RunWith(SerenityRunner.class)
public class WordpressLoginTest {

    @Managed
    WebDriver driver;

    @Steps
    Actor vu;

    @Test
    public void shouldBeAbleToLoginToWordPress() {
        vu.attemptsTo(
                Ensure.that(vu).can(BrowseTheWeb.at("http://localhost:8000/wp-login.php")),
                Login.withCredentials("vuhung", "vuhung"),
                Ensure.that(Target.the("Dashboard page title").locatedBy("title"))
                        .text()
                        .contains("Dashboard ‹ oz wordpress — WordPress")
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
}
``` 

# ChatGPT's Answer (second round) 


# The main code that ChatGPT generated
(It doesn't run in the first place) 

```java
    @Test
    public void shouldBeAbleToLoginToWordPress() {
        vu.can(BrowseTheWeb.with(driver));

        vu.attemptsTo(
                Open.url("http://localhost:8000/wp-login.php"),
                Ensure.that(Target.the("Username field").locatedBy("#user_login")).isVisible(),
                Ensure.that(Target.the("Password field").locatedBy("#user_pass")).isVisible(),
                Ensure.that(Target.the("Login button").locatedBy("#wp-submit")).isVisible(),
                Ensure.that(Target.the("vu can browse the URL").locatedBy("body")).isVisible(),
                Click.on("#user_login").then(Enter.theValue("vuhung")),
                Click.on("#user_pass").then(Enter.theValue("vuhung")),
                Click.on("#wp-submit"),
                WaitUntil.the(Target.the("Dashboard page title").locatedBy("title")).isPresent(),
                Ensure.that(Target.the("Dashboard page title").locatedBy("title")).textContains("Dashboard ‹ oz wordpress — WordPress")
        );
    }
``` 

# Modified Code 
With a little modification so that the code can run, it becomes: 

```java
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
```

## Final Code (Combined the Two Test Cases in a Java Class) 

Final source code: [WordpressLoginTest.java](https://github.com/vuhung16au/chatgpt/blob/main/WordpressLoginTest.java)

# Run the Serenity BDD's Screenplay code with maven 

```bash
$ mvn clean verify -Dit.test=WordpressLoginTest
```

```bash
vhmac:selenium-itnews vuhung$ mvn clean verify -Dit.test=WordpressLoginTest
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< com.vuhung.seleniium:selenium-itnews >----------------
[INFO] Building Serenity project with JUnit and WebDriver 1.1
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- clean:3.2.0:clean (default-clean) @ selenium-itnews ---
[INFO] Deleting /Users/vuhung/eclipse-workspace/selenium-itnews/target
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ selenium-itnews ---
[INFO] skip non existing resourceDirectory /Users/vuhung/eclipse-workspace/selenium-itnews/src/main/resources
[INFO]
[INFO] --- compiler:3.2:compile (default-compile) @ selenium-itnews ---
[INFO] No sources to compile
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ selenium-itnews ---
[INFO] Copying 1 resource from src/test/resources to target/test-classes
[INFO]
[INFO] --- compiler:3.2:testCompile (default-testCompile) @ selenium-itnews ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 25 source files to /Users/vuhung/eclipse-workspace/selenium-itnews/target/test-classes
[INFO]
[INFO] --- surefire:3.0.0-M5:test (default-test) @ selenium-itnews ---
[INFO] Tests are skipped.
[INFO]
[INFO] --- jar:3.3.0:jar (default-jar) @ selenium-itnews ---
[WARNING] JAR will be empty - no content was marked for inclusion!
[INFO] Building jar: /Users/vuhung/eclipse-workspace/selenium-itnews/target/selenium-itnews-1.1.jar
[INFO]
[INFO] --- failsafe:3.0.0-M5:integration-test (default) @ selenium-itnews ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.vuhung.wordpress.WordpressLoginTest
[main] INFO  -

-------------------------------------------------------------------------------------
     _______. _______ .______       _______ .__   __.  __  .___________.____    ____
    /       ||   ____||   _  \     |   ____||  \ |  | |  | |           |\   \  /   /
   |   (----`|  |__   |  |_)  |    |  |__   |   \|  | |  | `---|  |----` \   \/   /
    \   \    |   __|  |      /     |   __|  |  . `  | |  |     |  |       \_    _/
.----)   |   |  |____ |  |\  \----.|  |____ |  |\   | |  |     |  |         |  |
|_______/    |_______|| _| `._____||_______||__| \__| |__|     |__|         |__|

 News and tutorials at https://serenity-bdd.info
 Documentation at https://serenity-bdd.github.io
 Test Automation Training and Coaching: https://www.serenity-dojo.com
 Commercial Support: https://www.serenity-dojo.com/serenity-bdd-enterprise-support
 Join the Serenity Community on Gitter: https://gitter.im/serenity-bdd/serenity-core
-------------------------------------------------------------------------------------

[main] INFO  - Test Suite Started: Wordpress login test
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

shouldBeAbleToLoginToWordPress
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Should be able to login to word press
----------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

shouldBeAbleToLoginToWordPressAsStandardUser
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Should be able to login to word press as standard user
----------------------------------------------------------------------
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 16.807 s - in com.vuhung.wordpress.WordpressLoginTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- serenity:4.0.29:aggregate (serenity-reports) @ selenium-itnews ---
[INFO] GENERATING REPORTS FOR: /Users/vuhung/eclipse-workspace/selenium-itnews
[INFO] GENERATING REPORTS USING 32 THREADS
[INFO] GENERATING SUMMARY REPORTS...
[INFO] GENERATING REQUIREMENTS REPORTS...
[INFO] GENERATING RESULT REPORTS...
[INFO] GENERATING ERROR REPORTS...
[INFO] Test results for 2 tests generated in 2.7 secs in directory: file:/Users/vuhung/eclipse-workspace/selenium-itnews/target/site/serenity/
[INFO] ------------------------------------------------
[INFO] | SERENITY TESTS:               | SUCCESS
[INFO] ------------------------------------------------
[INFO] | Test scenarios executed       | 2
[INFO] | Total Test cases executed     | 2
[INFO] | Tests passed                  | 2
[INFO] | Tests failed                  | 0
[INFO] | Tests with errors             | 0
[INFO] | Tests compromised             | 0
[INFO] | Tests aborted                 | 0
[INFO] | Tests pending                 | 0
[INFO] | Tests ignored/skipped         | 0
[INFO] ------------------------------- | --------------
[INFO] | Total Duration| 7s 734ms
[INFO] | Fastest test took| 2s 999ms
[INFO] | Slowest test took| 4s 735ms
[INFO] ------------------------------------------------
[INFO]
[INFO] SERENITY REPORTS
[INFO]   - Full Report: file:///Users/vuhung/eclipse-workspace/selenium-itnews/target/site/serenity/index.html
[INFO]
[INFO] --- failsafe:3.0.0-M5:verify (default) @ selenium-itnews ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  28.640 s
[INFO] Finished at: 2024-01-06T19:58:22+11:00
[INFO] ------------------------------------------------------------------------
```

# Serenity BDD's Test Reports 

The same test case with different implemenations by ChatGPT 

![shouldBeAbleToLoginToWordPress](https://github.com/vuhung16au/chatgpt/blob/main/ChatGPT-SerenityBDD-shouldBeAbleToLoginToWordPress.png?raw=true)
![shouldBeAbleToLoginToWordPressAsStandardUser](https://github.com/vuhung16au/chatgpt/blob/main/ChatGPT-SerenityBDD-shouldBeAbleToLoginToWordPressAsStandardUser.png?raw=true)
