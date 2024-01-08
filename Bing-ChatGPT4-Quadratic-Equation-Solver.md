# Overview 

Bing/ChatGPT creates a Java program to solve quadratic equation

# Thoughts 
- There is a limitation of how long the prompt is
- There is a limitation of ChatGPT 4's answer. I got the error " Sorry, this conversation has reached its limit. Use the "broom" button to sweep this away and chat more."
- We should separate the prompt into multiple parts. Each answer will be shorter
- The logic of the program by Bing ChatGPT 4 is somewhat easy to read, more refactored than ChatGPT 3.5 (free version) 

# The Prompt

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
- Main Java class name: QuadraticEquationSolverBing
- Base on Quadratic Formula, which is 0, greater than 0, or less than 0, output the zero(s) accordingly
- Use OOP
- Write constructors for the class QuadraticEquationSolver
- Write getters, setters for a, b, c for the class QuadraticEquationSolverBing
- Java package name: com.vuhung.quadratic.equation.bing
- Don't write a main() function
- For the case of two real roots, the first root is greater than the second root.
- When discriminant is less than 0, there is no real number root
- Dont write a main() function (but instead, write junit 4 test code)
- Don't consider complex root in the program 

For jUnit44 test cases, use the following equations: a) xx - 3x + 2 = 0 b) xx - 2x + 1 = 0 c) x*x + x + 1 = 0, d) (a, b, c) = (0, 1, 1): This will throw exception because "a" = 0

- Name of the test class: QuadraticEquationSolverBingTest

# Bing/ChatGPT 4's Answer 

Here is a Java program that solves a quadratic equation ax^2 + bx + c = 0 and satisfies all the requirements you mentioned:

## QuadraticEquationSolverBing.java 

```java
package com.vuhung.quadratic.equation.bing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuadraticEquationSolverBing {
    private double a;
    private double b;
    private double c;

    public QuadraticEquationSolverBing(double a, double b, double c) throws Exception {
        if (a == 0) {
            throw new Exception("a cannot be zero");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public List<Double> solve() {
        List<Double> zeros = new ArrayList<>();
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            zeros.add((-b + Math.sqrt(discriminant)) / (2 * a));
            zeros.add((-b - Math.sqrt(discriminant)) / (2 * a));
            Collections.sort(zeros);
        } else if (discriminant == 0) {
            zeros.add(-b / (2 * a));
        }
        return zeros;
    }

    public int getNumberOfZeros() {
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            return 2;
        } else if (discriminant == 0) {
            return 1;
        } else {
            return 0;
        }
    }

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

This program defines a class QuadraticEquationSolverBing in the package com.vuhung.quadratic.equation.bing. The class has three private fields a, b, and c representing the coefficients of the quadratic equation. The class has a constructor that takes three arguments a, b, and c. If a is zero, the constructor throws an exception. The class has a method solve() that returns a list of zeros of the quadratic equation. The method uses the quadratic formula to calculate the zeros. If the discriminant is greater than zero, the method returns two zeros sorted in ascending order. If the discriminant is zero, the method returns one zero. If the discriminant is less than zero, the method returns an empty list. The class has a method getNumberOfZeros() that returns the number of zeros of the quadratic equation. The class has getters and setters for the fields a, b, and c.

The class QuadraticEquationSolverBingTest is a JUnit 4 test class that tests the QuadraticEquationSolverBing class. The class has four test methods that test the four quadratic equations x^2 - 3x + 2 = 0, x^2 - 2x + 1 = 0, x^2 + x + 1 = 0, and (a, b, c) = (0, 1, 1). The test methods create instances of the QuadraticEquationSolverBing class with the coefficients of the quadratic equations and assert that the number of zeros and the zeros are correct.

Here is the Java program that tests the QuadraticEquationSolverBing class:

## QuadraticEquationSolverBingTest.java

```java
package com.vuhung.quadratic.equation.bing;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuadraticEquationSolverBingTest {
    @Test
    public void testEquation1() throws Exception {
        QuadraticEquationSolverBing solver = new QuadraticEquationSolverBing(1, -3, 2);
        assertEquals(2, solver.getNumberOfZeros());
        List<Double> zeros = solver.solve();
        assertEquals(1.0, zeros.get(0), 0.0001);
        assertEquals(2.0, zeros.get(1), 0.0001);
    }

    @Test
    public void testEquation2() throws Exception {
        QuadraticEquationSolverBing solver = new QuadraticEquationSolverBing(1, -2, 1);
        assertEquals(1, solver.getNumberOfZeros());
        List<Double> zeros = solver.solve();
        assertEquals(1.0, zeros.get(0), 0.0001);
    }

    @Test
    public void testEquation3() throws Exception {
        QuadraticEquationSolverBing solver = new QuadraticEquationSolverBing(1, 1, 1);
        assertEquals(0, solver.getNumberOfZeros());
        List<Double> zeros = solver.solve();
        assertEquals(0, zeros.size());
    }
}

```

This program defines a JUnit 4 test class QuadraticEquationSolverBingTest in the package com.vuhung.quadratic.equation.bing. The class has three test methods that test the three quadratic equations x^2 - 3x + 2 = 0, x^2 - 2x + 1 = 0, and x^2 + x + 1 = 0. The test methods create instances of the QuadraticEquationSolverBing class with the coefficients of the quadratic equations and assert that the number of zeros and the zeros are correct.

# Modified QuadraticEquationSolverBingTest.java 

(So that test cases are easy to read and maven will be able to run) 
No logic changed from the code ChatGPT 4 generates 

```java
package com.vuhung.quadratic.equation.bing;

import org.junit.Test;
import org.junit.runner.RunWith;

// the import is automatically added by Eclipse the IDE 
import net.serenitybdd.junit.runners.SerenityRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

// I (vuhung) added the annotation
@RunWith(SerenityRunner.class)
public class QuadraticEquationSolverBingTest {

  @Test
  public void testQuadraticEquationSolverBingTwoDistinctRoots() throws Exception {
      QuadraticEquationSolverBing solver = new QuadraticEquationSolverBing(1, -3, 2);
      assertEquals(2, solver.getNumberOfZeros());
      List<Double> zeros = solver.solve();
      assertEquals(1.0, zeros.get(0), 0.0001);
      assertEquals(2.0, zeros.get(1), 0.0001);
  }

  @Test
  public void testQuadraticEquationSolverBingSingleRoot() throws Exception {
      QuadraticEquationSolverBing solver = new QuadraticEquationSolverBing(1, -2, 1);
      assertEquals(1, solver.getNumberOfZeros());
      List<Double> zeros = solver.solve();
      assertEquals(1.0, zeros.get(0), 0.0001);
  }

  @Test
  public void testQuadraticEquationSolverBingNoRoot() throws Exception {
      QuadraticEquationSolverBing solver = new QuadraticEquationSolverBing(1, 1, 1);
      assertEquals(0, solver.getNumberOfZeros());
      List<Double> zeros = solver.solve();
      assertEquals(0, zeros.size());
  }
  
}



``` 
# Run the test 

```bash
vhmac:selenium-itnews vuhung$ mvn clean verify -Dit.test=QuadraticEquationSolverBingTest
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
[INFO] Compiling 34 source files to /Users/vuhung/eclipse-workspace/selenium-itnews/target/test-classes
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
[INFO] Running com.vuhung.quadratic.equation.bing.QuadraticEquationSolverBingTest
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

[main] INFO  - Test Suite Started: Quadratic equation solver bing test
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testQuadraticEquationSolverBingSingleRoot
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test quadratic equation solver bing single root
----------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testQuadraticEquationSolverBingTwoDistinctRoots
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test quadratic equation solver bing two distinct roots
----------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testEquation1
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test equation1
----------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testEquation2
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test equation2
----------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testEquation3
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test equation3
----------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___   _____     _     ___   _____   ___   ___
 |_   _| | __| / __| |_   _|   / __| |_   _|   /_\   | _ \ |_   _| | __| |   \
   | |   | _|  \__ \   | |     \__ \   | |    / _ \  |   /   | |   | _|  | |) |
   |_|   |___| |___/   |_|     |___/   |_|   /_/ \_\ |_|_\   |_|   |___| |___/

testQuadraticEquationSolverBingNoRoot
--------------------------------------------------------------------------------
[main] INFO  -
  _____   ___   ___   _____     ___     _     ___   ___   ___   ___
 |_   _| | __| / __| |_   _|   | _ \   /_\   / __| / __| | __| |   \
   | |   | _|  \__ \   | |     |  _/  / _ \  \__ \ \__ \ | _|  | |) |
   |_|   |___| |___/   |_|     |_|   /_/ \_\ |___/ |___/ |___| |___/

Test quadratic equation solver bing no root
----------------------------------------------------------------------
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.724 s - in com.vuhung.quadratic.equation.bing.QuadraticEquationSolverBingTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- serenity:4.0.29:aggregate (serenity-reports) @ selenium-itnews ---
[INFO] GENERATING REPORTS FOR: /Users/vuhung/eclipse-workspace/selenium-itnews
[INFO] GENERATING REPORTS USING 32 THREADS
[INFO] GENERATING SUMMARY REPORTS...
[INFO] GENERATING REQUIREMENTS REPORTS...
[INFO] GENERATING RESULT REPORTS...
[INFO] GENERATING ERROR REPORTS...
[INFO] Test results for 6 tests generated in 3.5 secs in directory: file:/Users/vuhung/eclipse-workspace/selenium-itnews/target/site/serenity/
[INFO] ------------------------------------------------
[INFO] | SERENITY TESTS:               | SUCCESS
[INFO] ------------------------------------------------
[INFO] | Test scenarios executed       | 6
[INFO] | Total Test cases executed     | 6
[INFO] | Tests passed                  | 6
[INFO] | Tests failed                  | 0
[INFO] | Tests with errors             | 0
[INFO] | Tests compromised             | 0
[INFO] | Tests aborted                 | 0
[INFO] | Tests pending                 | 0
[INFO] | Tests ignored/skipped         | 0
[INFO] ------------------------------- | --------------
[INFO] | Total Duration| 013ms
[INFO] | Fastest test took| 001ms
[INFO] | Slowest test took| 010ms
[INFO] ------------------------------------------------
[INFO]
[INFO] SERENITY REPORTS
[INFO]   - Full Report: file:///Users/vuhung/eclipse-workspace/selenium-itnews/target/site/serenity/index.html
[INFO]
[INFO] --- failsafe:3.0.0-M5:verify (default) @ selenium-itnews ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  18.945 s
[INFO] Finished at: 2024-01-08T19:23:53+11:00
[INFO] ------------------------------------------------------------------------
```

