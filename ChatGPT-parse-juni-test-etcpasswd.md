# Overview 

This document show the prompt, the code and the test code that ChatGPT generats to parse and test /ect/passwd file

# Takeaways 
- Write the prompt as detailed as possible
- The prompt looks like a detail design document for the code
- Write everything you want in the prompt 

# Notes 
- Change from jUnit 4 to jUnit 5 if need
- Update the requirements, especially the non-functional requirements if needed
- Optimized is not considered
- Refactoring should be neccessary by human (The requirement is not included in the prompt)
- Secure coding is not considered

```bash
$mvn clean verify -Dit.test=EtcPasswd
```

# The Results 
- ChatGPT 3.5 can generate the code in less than 60 seconds
- The code runs in the first place without any modification. No debug needed


# The Prompt 

Write a method using Java Core to parse Unix's /etc/passwd file, 
and output the list of users in the file as an array of objects 

Input:
/etc/passwd

Example /etc/passwd file 

```
root:*:0:0:System Administrator:/var/root:/bin/sh
daemon:*:1:1:System Services:/var/root:/usr/bin/false
``` 

Ignore the headers of /etc/passsword

The name of Java package: com.etc.password
Name of the class EtcPasswd

The class EtcPasswd

- String userName
- int userID
- int groupID
- String userFullname
- String homeDirectory
- String shell

Write setter/getters for the class EtcPasswd

Write a test code for the class EtcPasswd using jUnit 4

Using the following sample /etc/passwd as the input for unit test 

```
nobody:*:-2:-2:Unprivileged User:/var/empty:/usr/bin/false
root:*:0:0:System Administrator:/var/root:/bin/sh
daemon:*:1:1:System Services:/var/root:/usr/bin/false
```

Comment the code in details

# The Code 

## EtcPasswd.java

```java

package com.etc.password;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EtcPasswd {

    private String userName;
    private int userID;
    private int groupID;
    private String userFullname;
    private String homeDirectory;
    private String shell;

    // Default constructor
    public EtcPasswd() {
    }

    // Parameterized constructor
    public EtcPasswd(String userName, int userID, int groupID, String userFullname, String homeDirectory, String shell) {
        this.userName = userName;
        this.userID = userID;
        this.groupID = groupID;
        this.userFullname = userFullname;
        this.homeDirectory = homeDirectory;
        this.shell = shell;
    }

    // Getter and Setter methods for all fields
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    // Method to parse the /etc/passwd file and return a list of EtcPasswd objects
    public static List<EtcPasswd> parseEtcPasswdFile(String filePath) {
        List<EtcPasswd> usersList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Ignore lines starting with '#' (comments)
                if (!line.startsWith("#")) {
                    String[] parts = line.split(":");
                    if (parts.length == 7) {
                        EtcPasswd user = new EtcPasswd();
                        user.setUserName(parts[0]);
                        user.setUserID(Integer.parseInt(parts[2]));
                        user.setGroupID(Integer.parseInt(parts[3]));
                        user.setUserFullname(parts[4]);
                        user.setHomeDirectory(parts[5]);
                        user.setShell(parts[6]);
                        usersList.add(user);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usersList;
    }
}
```

## EtcPasswdTest.java

```java


package com.etc.password;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EtcPasswdTest {

    @Test
    void parseEtcPasswdFile() {
        // Provide the path to your /etc/passwd file
        String filePath = "/etc/passwd";

        // Call the static method to parse the file
        List<EtcPasswd> usersList = EtcPasswd.parseEtcPasswdFile(filePath);

        // Perform assertions based on your expectations
        assertNotNull(usersList);
        assertFalse(usersList.isEmpty());

        // Assuming there are at least three users in the sample file
        assertEquals("nobody", usersList.get(0).getUserName());
        assertEquals(-2, usersList.get(0).getUserID());
        assertEquals(-2, usersList.get(0).getGroupID());
        assertEquals("/var/empty", usersList.get(0).getHomeDirectory());

        assertEquals("root", usersList.get(1).getUserName());
        assertEquals(0, usersList.get(1).getUserID());
        assertEquals(0, usersList.get(1).getGroupID());
        assertEquals("/var/root", usersList.get(1).getHomeDirectory());

        assertEquals("daemon", usersList.get(2).getUserName());
        assertEquals(1, usersList.get(2).getUserID());
        assertEquals(1, usersList.get(2).getGroupID());
        assertEquals("/var/root", usersList.get(2).getHomeDirectory());
    }
}

``` 
