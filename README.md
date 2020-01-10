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

## Framework setup
Now you have set up your pom file, you can now reimport your project as a Maven project and begin setting up your 
framework.

### .gitignore

If committing your project to a git hosting site, then your first step should be to create a `.gitignore` within the 
root of your repository like the example in the repository. Once created, you can commit your Maven repository. 

### Folder structure
Since these are tests, we only need a test folder. A typical layout is `src/test`, then we have a folder for our code 
`java` and a folder for our resources aptly called `resources`. 

After creating your structure you may need to amend your project structure settings in your IDE of choice, for example, 
IntelliJ sometimes struggles knowing straight away which is your resources folder.

### Page Object Model
Best practice is to follow the `Page Object Model` when writing your code. This is where you split up your actions per
page into it's own class. You can then call upon these actions individually in multiple step definitions. It makes your
code reusable and easier to maintain if you only have one place to refactor any possible changes. 

### Driver setup
Because we're using Maven, it means we can use a plugin that will handle our driver files. In this tutorial we use
[Bonigarcia WebDriverManager](https://github.com/bonigarcia/webdrivermanager) as our driver manager. You can find out
what the latest version is via the [Maven Repository](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager).

More details on the set up of the driver can be found within the files under [src/test/java/](https://github.com/swaistle/java-cucumber-selenium-template/tree/driver-setup/src/test/java/utils).

### Feature setup
Once we have a driver set up, we will need to create a feature to, not only create our scenario, but also test out that 
our driver is set up correctly!
You can find more details on feature setup within the files under [src/test/resources/](https://github.com/swaistle/java-cucumber-selenium-template/tree/feature-setup/src/test/resources)
and [src/test/java/](https://github.com/swaistle/java-cucumber-selenium-template/tree/feature-setup/src/test/java).

### Running tests
You have a scenario, now you want to run it... but how? There are multiple ways to do so and each have their own benefits
to doing so depending on how it's going to be used. 

#### Executing the command
To run a test you need run a Maven command to tell the build what to do. The basic command we need is 
`mvn -Dtest=<name of the test runner class> test`. 
* We can run this in the terminal within the root of the repository.
* Put the command in a shell script `.sh` and run that

We can elaborate this command with arguments to control how our tests are executed via the `-D<name>` parameter; e.g. 
which environment to run the tests on. This command is then typically called within CI pipelines such as GitLab's CI/CD 
section or Jenkins to run the tests.

If you use IntelliJ, you can also use their `Configuration` option to run your runner class. 

![alt text](img/IntelliJConfiguration.png "IntelliJ Test Configuration")

You can do this by either right clicking on the Test Runner class and clicking run (which will fail to run 
because of the default settings IntelliJ populates, so you will need to edit it), or create your own from scratch.

![alt text](img/IntelliJAddConfiguration.png "IntelliJ Test Add Configuration")

We use JUnit in this framework to execute the class, so you add a JUnit configuration. Title the configuration 
appropriately in the `Name:` field, and locate the runner class you want to execute in the `Class:` field.
You need to make sure that the `Working directory:` field is blank, and that the `VM options:` field either contains the 
arguments that are relevant to your test or remains blank. In the example screenshot above, we use an argument to 
stipulate which environment we want to run our tests against `-Denvironment=<name>`. Once set up, we can click the `Run` 
button.

_Having the class set up within IntelliJ helps when needing to debug your tests._

#### Cucumber tags
You might have multiple different types of tests you want to run at different times. You can create a Test Runner
for each type of test and assign a tag to it so it will only run that type of test for you. I typically create a:
* Regression Runner
* Smoke Runner
* Work In Progress (WIP) Runner

I then assign that Cucumber tag in my desired location within the feature file. A demonstration of this can be found in
the runner classes and feature files.
