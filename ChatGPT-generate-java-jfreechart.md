# Overview 
Bing/ChatGPT 4 can generate Java code using jfreechart to convert a CSV data into a PNG graph.

# Thoughts 

# Notes 

# The Prompt 

Here is the header of a CSV file containing data about Australia, by years

```
1960	1961	1962	1963	1964	1965	1966	1967	1968	1969	1970	1971	1972	1973	1974	1975	1976	1977	1978	1979	1980	1981	1982	1983	1984	1985	1986	1987	1988	1989	1990	1991	1992	1993	1994	1995	1996	1997	1998	1999	2000	2001	2002	2003	2004	2005	2006	2007	2008	2009	2010	2011	2012	2013	2014	2015	2016	2017	2018	2019	2020	2021	2022
```

And here is the corresonding data 
```
0	0	0	39.08782902	37.25198449	33.1901785	33.73775091	28.70362679	28.17703572	25.93667032	18.95416178	13.96053704	18.49075059	23.24879983	14.54511013	11.26250361	14.32547613	13.26988528	13.56045994	12.8646216	10.71161184	11.25690074	10.84038809	10.83847501	9.942111643	11.56734786	12.52203946	15.50249493	18.75342285	15.27788167	10.5178903	10.65631936	10.14325957	8.16595463	8.945095255	8.532496021	7.681348021	8.293436073	7.105342082	6.69019422	6.058121221	6.235404084	5.776309677	5.01490134	4.820928998	3.89836058	3.434431229	3.425970959	2.441952676	2.282876544	2.491043371	3.005694105	3.005310474	2.994705894	2.764930603	2.965317588	3.166924685	2.990205316	2.885393719	2.105863067	1.428753573	1.673354004	1.767081247
```

This presents the `AUS	Agricultural raw materials exports (%) of merchandise` for Australia by year. 

Please write a Java program to draw a graph and export it as an png image for AUS	Agricultural raw materials exports data by year 

Input: 
- The CSV data above with name "/tmp/Australia-agricultural-raw-material-export.csv"

Output
- `AUS	Agricultural raw materials exports (% of merchandise` for Australia by year. 
- File location: "/tmp/Australia-agricultural-raw-material-export.png"
- Size of "Australia-agricultural-raw-material-export.png" 1,920 x 1,080 pixels

Requirements 
- Java package name: com.vuhung.aus.agricultural 
- Main Java class name: AusAgriculturalExportPNG
- Write a main() main 
- main() method will export the file "Australia-agricultural-raw-material-export.png"

Requirements for "Australia-agricultural-raw-material-export.png"
- Graph background color: (255, 255, 255)
- Graph border: a) color = (0, 0, 139), b) border thickness: 2 pixel 
- Graph line color: (255, 0, 0)
- Graph title: `AUS	Agricultural raw materials exports (%) of merchandise <year_start> - <year_end>`
  where <year_start> and <year_end> are the begining and the last year in the CSV input file 

# ChatGPT 4's Answer

Here is a Java program that reads the CSV file, draws a graph based on the data, and exports it as a PNG image. This program uses the JFreeChart library for creating the chart and Apache Commons CSV for reading the CSV file.

```java
package com.vuhung.aus.agricultural;

import org.apache.commons.csv.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;

public class AusAgriculturalExportPNG {
    public static void main(String[] args) throws IOException {
        String csvFile = "/tmp/Australia-agricultural-raw-material-export.csv";
        String pngFile = "/tmp/Australia-agricultural-raw-material-export.png";
        int width = 1920;
        int height = 1080;

        Reader in = new FileReader(csvFile);
        Iterable<CSVRecord> records = CSVFormat.TDF.withFirstRecordAsHeader().parse(in);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (CSVRecord record : records) {
            for (String year : record.toMap().keySet()) {
                String value = record.get(year);
                dataset.addValue(Double.parseDouble(value), "AUS Agricultural raw materials exports (%) of merchandise", year);
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "AUS Agricultural raw materials exports (%) of merchandise " + dataset.getColumnKey(0) + " - " + dataset.getColumnKey(dataset.getColumnCount() - 1),
                "Year",
                "Exports (%)",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        chart.setBackgroundPaint(new Color(255, 255, 255));
        chart.setBorderPaint(new Color(0, 0, 139));
        chart.setBorderStroke(new BasicStroke(2));
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(255, 0, 0));

        BufferedImage image = chart.createBufferedImage(width, height);
        ImageIO.write(image, "png", new File(pngFile));
    }
}



```

Please note that you need to add the JFreeChart and Apache Commons CSV libraries to your project’s classpath. You can add them to your project using Maven or Gradle. Also, please ensure that the file paths and the file format are correct when running the program. The program assumes that the CSV file uses tabs as delimiters. If the actual CSV file uses a different delimiter, please adjust the CSVFormat.TDF line accordingly. If you encounter any issues, please let me know! I’m here to help. 

# Setup the Enviroment

For my case, as I am using maven, I add the following to my `pom.xml` manually 

```xml
		
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-csv -->
		<dependency>
		    <groupId>org.apache.commons</groupId>
		    <artifactId>commons-csv</artifactId>
		    <version>1.10.0</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.jfree/jfreechart -->
		<dependency>
		    <groupId>org.jfree</groupId>
		    <artifactId>jfreechart</artifactId>
		    <version>1.5.4</version>
		</dependency>
```

# Run the Program & Output

## Create the input `/tmp/Australia-agricultural-raw-material-export.csv`
(The data is available on World Bank's website: https://data.worldbank.org/country/australia?view=chart) 

[Australia-agricultural-raw-material-export.csv](https://github.com/vuhung16au/chatgpt/blob/main/Australia-agricultural-raw-material-export.csv)

## Run as Java Application & Output 

![Australia-agricultural-raw-material-export.png](https://github.com/vuhung16au/chatgpt/blob/main/Australia-agricultural-raw-material-export.png?raw=true)

