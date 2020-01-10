# test-framework-template

A basic template for test automation with Maven.

## Maven

Maven allows you to use plugins so you can bypass installing tools such as Selenium and web drivers onto your machine. 
Maven works by using a `pom` file, which is an xml file that allows you to stipulate what plugin you want from the Maven
 repository.

## Dependencies
The plugins are called `dependencies`. 
 
 ```
        <dependencies>
            <dependency>
                <groupId>org.seleniumhq.selenium</groupId>
                <artifactId>selenium-java</artifactId>
                <version>2.53.1</version>
            </dependency>
        </dependencies>
  ```

In your dependency, you need to give:
* the `groupId` (the name of the group supplying the plugin)
* the `artifactId` (the name of the plugin)
* the `version` (which version of the plugin you want to use)

_A good website to check what versions are released is https://mvnrepository.com/_

For automating a user interface, typically you would use:
* Something to interact with the browser, such as Selenium
* Something to help describe and action tests, such as Cucumber or JUnit
* Something to help execute the actions, such as Java, Scala, JavaScript

This is completely flexible and can be tailored to your project. For example, you may not benefit from using 
behaviour driven tests and so can opt for unit tests (which you can use JUnit to execute). Another example, your project 
maybe not be Java based so you can swap out the language to one that matches your project. 

For this framework though we will be using Selenium, Cucumber, and Java.

## Version control

Compatibility between versions of the plugins is one of the nuisances of dependency set up - and it's simply down to
trial and error to figure out what you can use. But good practice is to use the latest stable version.

It's also good practice to parametrise any version that's shared amongst a `groupId`. For example, if you view the 
`pom.xml` we have a number of `io.cucumber` plugins that have the same version number as their latest. So we can add a 
generic version number to our properties and reference it in our dependencies:
```
       <properties>
           <cucumber.version>4.7.4</cucumber.version>
       </properties>
       
        <dependencies>
               <dependency>
                   <groupId>io.cucumber</groupId>
                   <artifactId>cucumber-java</artifactId>
                   <version>${cucumber.version}</version>
               </dependency>
               <dependency>
                   <groupId>io.cucumber</groupId>
                   <artifactId>cucumber-junit</artifactId>
                   <version>${cucumber.version}</version>
               </dependency>
         </dependencies>

```