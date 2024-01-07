# Overview 

ChatGPT can solve quadratic equations. 

# Thoughts 

1. ChatGPT can produce code that is ready to run without any modification
2. In this case, I added a single annotation because I use maven.
3. Though instructed, ChatGPT couldn't write code for the case discriminant < 0
4. The code has room for improvement/refactoring
5. ChatGPT understands zero, root. In general, it is quite smart to get the right thing from bad written prompts
6. "quadratic formula" and "discriminant", ChatGPT can understand and use the better terminology
7. A confusing thing here is the definition of roots. Do we (and does ChatGPT) consider (a, b, c) = (1, 2, 1) having two or one root (being distinct or not)?
8. How do we count the number of roots? Do we limit ourself to only real numbers or complex number? (and being distinct or note, see above).
9. For the two open questions above, we need to write clear instruction for ChatGPT (TODO)
10. I've tried with cubic equations and got good source codes 

# The prompt 

Write a program in Java to solve a Quadratic Equation ax2 + bx + c = 0

Input: 
- Real numbers a, b, c 
- "a" must be non-zero
- if "a" is zero, throw exception 

Output:
- The number of zeros for the equation
- List of zeros for the equation

Additional requirements 
- Use Quadratic Formula and write a method to calculate Quadratic Formula 
- Main Java class name: QuadraicEquationSolver
- Base on Quadratic Formula, which is 0, greater than 0, or less than 0, output the zero(s) accordingly 
- Use OOP 
- Write constructors for the class QuadraicEquationSolver
- Write getters, setters for a, b, c for the class QuadraicEquationSolver
- Java package name: com.vuhung.quadratic.equation
- Don't write a main() function
- For the case of two real roots, the first root is greater than the second root. 

One more additional requirement:
- When quadratic formula is less than 0, output the two zeros as complex numbers

And one more additional requirement:
- Dont write a main() function (but instead, write junit 4 test code)

For junit 4 test cases, use the following equations: 
a) x*x - 3x + 2 = 0
b) x*x - 2x + 1 = 0 
c) x*x + x + 1 = 0 

- Name of junit 4 test class: QuadraicEquationSolverTest 

Write all code in your answer (don't refer to the previous code) in your next answer.
Put everything in a single Java program (a package, Java classes)

# ChatGPT's Answer 

## QuadraticEquationSolver.java 

```java
package com.vuhung.quadratic.equation;

public class QuadraticEquationSolver {

    private double a;
    private double b;
    private double c;

    public QuadraticEquationSolver(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Coefficient 'a' must be non-zero.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getNumberOfZeros() {
        double discriminant = calculateDiscriminant();
        if (discriminant > 0) {
            return 2;
        } else if (discriminant == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public double[] solve() {
        double discriminant = calculateDiscriminant();
        double sqrtDiscriminant = Math.sqrt(Math.abs(discriminant));

        if (discriminant > 0) {
            double root1 = (-b + sqrtDiscriminant) / (2 * a);
            double root2 = (-b - sqrtDiscriminant) / (2 * a);
            
            // Ensure root1 is greater than root2
            if (root1 < root2) {
                double temp = root1;
                root1 = root2;
                root2 = temp;
            }
            
            return new double[]{root1, root2};
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return new double[]{root};
        } else {
            // For complex roots, return an empty array
            return new double[]{};
        }
    }

    private double calculateDiscriminant() {
        return b * b - 4 * a * c;
    }

    // Getters and Setters
    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}

```

## QuadraticEquationSolverTest.java

Because I run the test with maven, I have to add the following annotation to the test method 

```
@RunWith(SerenityRunner.class)
```

The final code is 

```java
package com.vuhung.quadratic.equation;

import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;

import static org.junit.Assert.*;

// I added the following annotation or the code won't run with my maven 
@RunWith(SerenityRunner.class)
public class QuadraticEquationSolverTest {
    
    @Test
    public void testEquationWithTwoRealRoots() {
        QuadraticEquationSolver equationSolver = new QuadraticEquationSolver(1, -3, 2);
        assertEquals(2, equationSolver.getNumberOfZeros());
        assertArrayEquals(new double[]{2.0, 1.0}, equationSolver.solve(), 0.01);
    }

    @Test
    public void testEquationWithOneRealRoot() {
        QuadraticEquationSolver equationSolver = new QuadraticEquationSolver(1, -2, 1);
        assertEquals(1, equationSolver.getNumberOfZeros());
        assertArrayEquals(new double[]{1.0}, equationSolver.solve(), 0.01);
    }

    @Test
    public void testEquationWithComplexRoots() {
        QuadraticEquationSolver equationSolver = new QuadraticEquationSolver(1, 1, 1);
        assertEquals(0, equationSolver.getNumberOfZeros());
        assertArrayEquals(new double[]{}, equationSolver.solve(), 0.01);
    }
}

```

# The Test Run

```bash
vhmac:selenium-itnews vuhung$ mvn clean verify -Dit.test=QuadraticEquationSolverTest
```

```bash
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
[INFO] Compiling 27 source files to /Users/vuhung/eclipse-workspace/selenium-itnews/target/test-classes
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
[INFO] Running com.vuhung.quadratic.equation.QuadraticEquationSolverTest
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

[main] INFO  - Test Suite Started: Quadratic equation solver test
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testEquationWithTwoRealRoots
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test equation with two real roots
----------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testEquationWithComplexRoots
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test equation with complex roots
----------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testEquationWithOneRealRoot
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test equation with one real root
----------------------------------------------------------------------
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.817 s - in com.vuhung.quadratic.equation.QuadraticEquationSolverTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- serenity:4.0.29:aggregate (serenity-reports) @ selenium-itnews ---
[INFO] GENERATING REPORTS FOR: /Users/vuhung/eclipse-workspace/selenium-itnews
[INFO] GENERATING REPORTS USING 32 THREADS
[INFO] GENERATING SUMMARY REPORTS...
[INFO] GENERATING REQUIREMENTS REPORTS...
[INFO] GENERATING RESULT REPORTS...
[INFO] GENERATING ERROR REPORTS...
[INFO] Test results for 3 tests generated in 2.9 secs in directory: file:/Users/vuhung/eclipse-workspace/selenium-itnews/target/site/serenity/
[INFO] ------------------------------------------------
[INFO] | SERENITY TESTS:               | SUCCESS
[INFO] ------------------------------------------------
[INFO] | Test scenarios executed       | 3
[INFO] | Total Test cases executed     | 3
[INFO] | Tests passed                  | 3
[INFO] | Tests failed                  | 0
[INFO] | Tests with errors             | 0
[INFO] | Tests compromised             | 0
[INFO] | Tests aborted                 | 0
[INFO] | Tests pending                 | 0
[INFO] | Tests ignored/skipped         | 0
[INFO] ------------------------------- | --------------
[INFO] | Total Duration| 014ms
[INFO] | Fastest test took| 001ms
[INFO] | Slowest test took| 012ms
[INFO] ------------------------------------------------
[INFO]
[INFO] SERENITY REPORTS
[INFO]   - Full Report: file:///Users/vuhung/eclipse-workspace/selenium-itnews/target/site/serenity/index.html
[INFO]
[INFO] --- failsafe:3.0.0-M5:verify (default) @ selenium-itnews ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  13.725 s
[INFO] Finished at: 2024-01-07T18:47:20+11:00
[INFO] ------------------------------------------------------------------------
vhmac:selenium-itnews vuhung$ mvn clean verify -Dit.test=QuadraticEquationSolverTest
```

# Follow-up Prompt (Test the case when a = 0)

## The prompt 
```
Add another test with (a, b, c) = (0, 2, 1)
this should throws exception. 

Note that we are using junit version 4 (not 5)
```

## ChatGPT's Answer 

```java
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWhenAIsZero() {
        QuadraticEquationSolver solver = new QuadraticEquationSolver(0, 2, 1);
    }
```

And its explanation

In this modified code, the `testExceptionWhenAIsZero` test checks that an `IllegalArgumentException` is thrown when trying to create a `QuadraticEquationSolver` with a being zero. The `@Test(expected = IllegalArgumentException.class)` annotation is used to specify that the test is expected to throw this specific exception.
