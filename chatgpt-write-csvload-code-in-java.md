# Prompt

```

use Java Core load a CSV file 

Input 

CSV file name: /Users/vuhung/eclipse-workspace/selenium-itnews/testData/WordpressUsers.csv

CSV file content
``` 

```csv
No, Username, password
1, inactive01, inactive01
2, inactive02, inactive02
3, inactive03, inactive03
4, inactive04, inactive04
5, inactive05, inactive05
```
```
Load the CSV file content to an array of objects 
```
```java
public class WordpressUser {
	private String userName;
	private String password; 

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

} 
``` 


# ChatGPT Answer


To load the CSV file content into an array of WordpressUser objects in Java, you can use the following code. This example assumes that you have the WordpressUser class defined as mentioned:


```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public static void main(String[] args) {
        String csvFile = "/Users/vuhung/eclipse-workspace/selenium-itnews/testData/WordpressUsers.csv";
        List<WordpressUser> usersList = loadCSV(csvFile);

        // Print loaded data
        for (WordpressUser user : usersList) {
            System.out.println("Username: " + user.getUserName() + ", Password: " + user.getPassword());
        }
    }

    public static List<WordpressUser> loadCSV(String csvFile) {
        List<WordpressUser> usersList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read the CSV header (if any)
            String line = br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                WordpressUser user = new WordpressUser();

                // Assuming the CSV columns order is No, Username, Password
                // Adjust indexes accordingly if the order is different
                user.setUserName(data[1].trim());
                user.setPassword(data[2].trim());

                usersList.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return usersList;
    }
}
```

In this code:

- The loadCSV method reads the CSV file line by line, skipping the header line.
- It splits each line into an array of strings using the comma as a delimiter.
- It creates a new WordpressUser object and sets the userName and password properties based on the values in the CSV.
- The WordpressUser objects are added to the usersList.
- Finally, the loaded WordpressUser objects are printed to the console for verification.
- Make sure to adjust the indexes in the setUserName and setPassword lines if the CSV column order is different in your actual file.


# Thoughts
- Correct code
- Can run without modifying anything
- The detailed information in the prompt is the key 
