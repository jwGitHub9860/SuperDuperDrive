# Super*Duper*Drive Cloud Storage
You have been hired by Super*Duper*Drive, which is a brand new company aiming to make a dent in the Cloud Storage market and is already facing stiff competition from rivals like Google Drive and Dropbox. That hasn't dampened their spirits at all, however. They want to include personal information management features in their application to differentiate them from the competition, and the minimum viable product includes three user-facing features:

1. **Simple File Storage:** Upload/download/remove files
2. **Note Management:** Add/update/remove text notes
3. **Password Management:** Save, edit, and delete website credentials.  

Super*Duper*Drive wants you to focus on building the web application with the skills you acquired in this course. That means you are responsible for developing the server, website, and tests, but other tasks like deployment belong to other teams at the company. 

## Starter Project
A senior developer is assigned to be your tech lead and mentor, and they put together a starter project for you. It's a Maven project configured for all the dependencies the project requires, though you should feel free to add any additional dependencies you might require. [You can download or clone the starter repository here](https://github.com/udacity/nd035-c1-spring-boot-basics-project-starter/tree/master/starter/cloudstorage).

Your tech lead already designed a database schema for the project and has added it to the `src/main/resources` directory. That means you don't have to design the database, only develop the Java code to interact with it. 

Your tech lead also created some HTML templates from the design team's website mockups, and they placed them in the `src/main/resources/templates` folder. These are static pages right now, and you have to configure them with Thymeleaf to add functionality and real data from the server you develop. You may also have to change them to support testing the application.

From the link above, you can download the starter code and open it as a Maven project in VSCode or IntelliJ.

## Requirements and Roadmap
Your tech lead is excited to work with you and has laid out a development roadmap with requirements and milestones. They tell you that there are three layers of the application you need to implement:

1. The back-end with Spring Boot
2. The front-end with Thymeleaf
3. Application tests with Selenium

### The Back-End
The back-end is all about security and connecting the front-end to database data and actions. 

1. Managing user access with Spring Security
 - You have to restrict unauthorized users from accessing pages other than the login and signup pages. To do this, you must create a security configuration class that uses `SecurityFilterChain` class from Spring. Place this class in a package reserved for security and configuration. Often this package is called `security` or `config`.
 - Spring Boot has built-in support for handling calls to the `/login` and `/logout` endpoints. You have to use the security configuration to override the default login page with one of your own, discussed in the front-end section.
 - You also need to implement a custom `AuthenticationProvider` which authorizes user logins by matching their credentials against those stored in the database.  


2. Handling front-end calls with controllers
 - You need to write controllers for the application that bind application data and functionality to the front-end. That means using Spring MVC's application model to identify the templates served for different requests and populating the view model with data needed by the template. 
 - The controllers you write should also be responsible for determining what, if any, error messages the application displays to the user. When a controller processes front-end requests, it should delegate the individual steps and logic of those requests to other services in the application, but it should interpret the results to ensure a smooth user experience.
 - It's a good idea to keep your controllers in a single package to isolate the controller layer. Usually, we simply call this package `controller`!
 - If you find yourself repeating tasks over and over again in controller methods, or your controller methods are getting long and complicated, consider abstracting some methods out into services! For example, consider the `HashService` and `EncryptionService` classes included in the starter code package `service`. These classes encapsulate simple, repetitive tasks and are available anywhere dependency injection is supported. Think about additional tasks that can be similarly abstracted and reused, and create new services to support them!


3. Making calls to the database with MyBatis mappers
 - Since you were provided with a database schema to work with, you can design Java classes to match the data in the database. These should be POJOs (Plain Old Java Objects) with fields that match the names and data types in the schema, and you should create one class per database table. These classes typically are placed in a `model` or `entity` package.
 - To connect these model classes with database data, implement MyBatis mapper interfaces for each of the model types. These mappers should have methods that represent specific SQL queries and statements required by the functionality of the application. They should support the basic CRUD (Create, Read, Update, Delete) operations for their respective models at the very least. You can place these classes in (you guessed it!) the `mapper` package.
 - Default supported DB is Postgres SQL and the schema provided in schema.sql is compatible to work with Postgres. You can follow previous lesson video which cover steps to create these tables in postgres.


### The Front-End
Your tech lead has done a thorough job developing HTML templates for the required application pages. They have included fields, modal forms, success and error message elements, as well as styling and functional components using Bootstrap as a framework. You must edit these templates and insert Thymeleaf attributes to supply the back-end data and functionality described by the following individual page requirements:

1. Login page
 - Everyone should be allowed access to this page, and users can use this page to login to the application. 
 - Show login errors, like invalid username/password, on this page. 


2. Sign Up page
 - Everyone should be allowed access to this page, and potential users can use this page to sign up for a new account. 
 - Validate that the username supplied does not already exist in the application, and show such signup errors on the page when they arise.
 - Remember to store the user's password securely!


3. Home page
The home page is the center of the application and hosts the three required pieces of functionality. The existing template presents them as three tabs that can be clicked through by the user:


 i. Files
  - The user should be able to upload files and see any files they previously uploaded. 

  - The user should be able to view/download or delete previously-uploaded files.
  - Any errors related to file actions should be displayed. For example, a user should not be able to upload two files with the same name, but they'll never know unless you tell them!


 ii. Notes
  - The user should be able to create notes and see a list of the notes they have previously created.
  - The user should be able to edit or delete previously-created notes.

 iii. Credentials
 - The user should be able to store credentials for specific websites and see a list of the credentials they've previously stored. If you display passwords in this list, make sure they're encrypted!
 - The user should be able to view/edit or delete individual credentials. When the user views the credential, they should be able to see the unencrypted password.

The home page should have a logout button that allows the user to logout of the application and keep their data private.

### Testing
Your tech lead trusts you to do a good job, but testing is important whether you're an excel number-cruncher or a full-stack coding superstar! The QA team at Super*Duper*Drive carries out extensive user testing. Still, your tech lead wants you to write some simple Selenium tests to verify user-facing functionality and prove that your code is feature-complete before the testers get their hands on it.

1. Write tests for user signup, login, and unauthorized access restrictions.
 - Write a test that verifies that an unauthorized user can only access the login and signup pages.
 - Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible. 


2. Write tests for note creation, viewing, editing, and deletion.
 - Write a test that creates a note, and verifies it is displayed.
 - Write a test that edits an existing note and verifies that the changes are displayed.
 - Write a test that deletes a note and verifies that the note is no longer displayed.


3. Write tests for credential creation, viewing, editing, and deletion.
 - Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
 - Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
 - Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.

## Final Tips and Tricks
### Password Security
Make sure not to save the plain text credentials of the application's users in the database. That's a recipe for data breach disaster! Use a hashing function to store a scrambled version instead. Your tech lead gave you a class called `HashService` that can hash passwords for you. When the user signs up, you only store a hashed version of their password in the database, and on login, you hash the password attempt before comparing it with the hashed password in the database. Your tech lead knows that can be a little confusing, so they provided this code sample to help illustrate the idea:

```
byte[] salt = new byte[16];
random.nextBytes(salt);
String encodedSalt = Base64.getEncoder().encodeToString(salt);
String hashedPassword = hashService.getHashedValue(plainPassword, encodedSalt);
return hashedPassword;
```

For storing credentials in the main part of the application, we can't hash passwords because it's a one-way operation. The user needs access to the unhashed password, after all! So instead, you should encrypt the passwords. Your tech lead provided you with a class called `EncryptionService` that can encrypt and decrypt passwords. When a user adds new credentials, encrypt the password before storing it in the database. When the user views those credentials, decrypt the password before displaying it. Here's a little code snippet on how to use `EncryptionService`:

```
SecureRandom random = new SecureRandom();
byte[] key = new byte[16];
random.nextBytes(key);
String encodedKey = Base64.getEncoder().encodeToString(key);
String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
```

You aren't required to understand hashing or encryption and that's why your tech lead provided these code samples for you. If you're curious and want to learn a little more, you can do a quick Google search or follow the links below:

[Hash Function](https://en.wikipedia.org/wiki/Hash_function)
[Encryption](https://en.wikipedia.org/wiki/Encryption)

All of us here at Super*Duper*Drive wish you good luck with the project!


<br/>


## Description:

The project is a web application that edits file storage, manages notes, and manages passwords.

## Files Used

_None_


## Date created

**8/29/2025**

## Udacity Mentors Who Have Answered Questions In _Knowledge_ To Help With Project

- 

## Credits
###### References used while making project

“@Controller vs. @RestController Annotation in Spring.” _GeeksforGeeks_, GeeksforGeeks, 3 Sept. 2025, www.geeksforgeeks.org/springboot/controller-vs-restcontroller-annotation-in-spring/. Accessed 12 Sep. 2025.

abc32112, and dectarin. “How Do I Find the Library Containing Org.Springframework.Stereotype.Service?” Edited by Vertexwahn, _Stack Overflow_, 8 Aug. 2014, stackoverflow.com/questions/25206487/how-do-i-find-the-library-containing-org-springframework-stereotype-service. Accessed 18 Sep. 2025.

“AuthenticationService.” _HYLAND_, support.hyland.com/r/Alfresco/Alfresco-Content-Services/23.4/Alfresco-Content-Services/Develop/Reference/Java-Foundation-API/AuthenticationService. Accessed 7 Sep. 2025.

B, Zachary. “Testing Delete.” _Knowledge_, Udacity, 2022, knowledge.udacity.com/questions/813835. Accessed 12 Sep. 2025.

baeldung. “Convert Long to String in Java.” _Baeldung_, 4 Oct. 2021, www.baeldung.com/java-long-to-string. Accessed 21 Sep. 2025.

baeldung. “File Upload with Spring MVC.” _Baeldung_, 11 June 2025, www.baeldung.com/spring-file-upload. Accessed 11 Sep. 2025.

baeldung. “Iteration in Thymeleaf.” _Baeldung_, 25 July 2024, www.baeldung.com/thymeleaf-iteration. Accessed 29 Sep. 2025.

baeldung. “Spring MVC and the @ModelAttribute Annotation.” _Baeldung_, 27 Feb. 2025, www.baeldung.com/spring-mvc-and-the-modelattribute-annotation. Accessed 16 Sep. 2025.

Bezkoder. “Thymeleaf File Upload with Spring Boot.” _BezKoder_, 26 May 2024, www.bezkoder.com/thymeleaf-file-upload/. Accessed 29 Sep. 2025.

Boon, Isaac Tan. “What Do They Mean by This? I Was Unsure about the Instructions for Super Duper Drive.” _Knowledge_, Udacity, 2020, knowledge.udacity.com/questions/349430. Accessed 6 Sep. 2025.

“Class AuthenticationService.” _Authenticationservice (Charm 0.0.1 API)_, 14 June 2015, docs.gluonhq.com/javadoc/0.0.1/com/gluonhq/charm/connect/service/AuthenticationService.html. Accessed 7 Sep. 2025.

“Class AuthenticationService.” _Oracle Entitlements Server for Java API Reference: Class AuthenticationService_, docs.oracle.com/cd/E12890_01/ales/docs32/javadocs/javaapi/com/bea/security/AuthenticationService.html. Accessed 7 Sep. 2025.

“Class SecureRandom.” _SecureRandom (Java Platform SE 8 )_, 15 July 2025, docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html. Accessed 17 Sep. 2025.

“Class SecureRandomFactoryBean.” _SecureRandomFactoryBean (Spring-Security-Docs 6.5.5 API)_, docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/token/SecureRandomFactoryBean.html. Accessed 17 Sep. 2025.

“Class User.” _User (Spring-Security-Docs 6.5.5 API)_, docs.spring.io/spring-security/reference/api/java/org/springframework/security/core/userdetails/User.html. Accessed 17 Sep. 2025.

“Class UsernamePasswordAuthenticationToken.” _Usernamepasswordauthenticationtoken (Spring Security 4.0.4.Release API)_, 25 Feb. 2016, docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/authentication/UsernamePasswordAuthenticationToken.html. Accessed 18 Sep. 2025.

Cookies, Dev. “The Scope of Beans in Spring Boot: A Comprehensive Guide.” _Medium_, Medium, 6 Nov. 2024, devcookies.medium.com/the-scope-of-beans-in-spring-boot-a-comprehensive-guide-ca4de7c531f3. Accessed 30 Sep. 2025.

D, Dao Anh. “I Can’t Run the Sample Test Case.” _Knowledge_, Udacity, 2022, knowledge.udacity.com/questions/880822. Accessed 12 Sep. 2025.

“Delete a File Using Java.” _GeeksforGeeks_, GeeksforGeeks, 10 Jan. 2025, www.geeksforgeeks.org/java/delete-file-using-java/. Accessed 12 Sep. 2025.

devs5003. “Spring Security UserDetailsService Using Spring Boot 3.” _JavaTechOnline_, 17 Apr. 2025, javatechonline.com/spring-security-userdetailsservice-using-spring-boot-3/. Accessed 17 Sep. 2025.

Dimitrov, Evgeni. “Spring Security Custom Authentication - AuthenticationProvider vs UserDetailsService.” _Stack Overflow_, 25 July 2015, stackoverflow.com/questions/31630818/spring-security-custom-authentication-authenticationprovider-vs-userdetailsser. Accessed 7 Sep. 2025.

“Do You Always Need to Separate Services and Controllers?” _Corey Cleary_, www.coreycleary.me/do-you-always-need-to-separate-services-and-controllers. Accessed 13 Sep. 2025.

“Download Spring-Context JAR File with All Dependencies.” _JAR Search and Dependency Download from the Maven Repository_, jar-download.com/artifacts/org.springframework/spring-context/org.apache.commons.net.ftp.FTPClient. Accessed 18 Sep. 2025.

Ghobril, Samir, and m59. “What Is Href="#" and Why Is It Used?” Edited by User247702 and Stephen Ostermiller, _Stack Overflow_, 31 Jan. 2011, stackoverflow.com/questions/4855168/what-is-href-and-why-is-it-used. Accessed 29 Sep. 2025.

heisenberg, et al. “Model.addAttribute() Parameters.” _Stack Overflow_, 28 Oct. 2016, stackoverflow.com/questions/40303916/model-addattribute-parameters. Accessed 16 Sep. 2025.

“How TO - File Upload Button.” _W3schools.Com_, www.w3schools.com/howto/howto_html_file_upload_button.asp. Accessed 4 Oct. 2025.

“How to Add Link to HTML Button?” _GeeksforGeeks_, 12 July 2025, www.geeksforgeeks.org/html/how-to-create-an-html-button-that-acts-like-a-link/. Accessed 4 Oct. 2025.

“HTML - Href Attribute.” _Tutorialspoint_, www.tutorialspoint.com/html/html_href_attribute.htm. Accessed 29 Sep. 2025.

“HTML - Target Attribute.” _Tutorialspoint_, www.tutorialspoint.com/html/html_target_attribute.htm. Accessed 29 Sep. 2025.

“Interface Model.” _Spring_, 10 Apr. 2019, docs.spring.io/spring-framework/docs/5.1.6.RELEASE_to_5.2.0.M1/Spring%20Framework%205.2.0.M1/org/springframework/ui/Model.html. Accessed 16 Sep. 2025.

“Interface Multipartfile.” _MultipartFile (Spring Framework 6.2.11 API)_, docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html#getOriginalFilename(). Accessed 20 Sep. 2025.

“Interface UserInfo.” _UserInfo (Oracle Access Manager Extensibility Java API Reference)_, 1 Mar. 2013, docs.oracle.com/cd/E37115_01/apirefs.1112/e27137/oracle/security/am/engines/common/identity/provider/UserInfo.html. Accessed 9 Sep. 2025.

“Java Delete Files.” _W3Schools Online Web Tutorials_, www.w3schools.com/java/java_files_delete.asp. Accessed 11 Sep. 2025.

“Java Program to Convert Byte Array to String.” _GeeksforGeeks_, 23 July 2025, www.geeksforgeeks.org/java/java-program-to-convert-byte-array-to-string/. Accessed 26 Sep. 2025.

K, Christian. “Please I Need Quick Guide, Please Answer Now.” _Knowledge_, Udacity, 2022, knowledge.udacity.com/questions/811826#812044. Accessed 12 Sep. 2025.

Kremer, Thomas. “Spring Boot Web Security Application, WebSecurityConfigurerAdapter Is Deprecated, What Can I Use Instead to Make My Websecurityconfig Class Work?” _Stack Overflow_, 30 Sept. 2023, stackoverflow.com/questions/77161239/spring-boot-web-security-application-websecurityconfigureradapter-is-deprecated. Accessed 3 Sep. 2025.

L, Keith. “Controllers.” _Knowledge_, Udacity, 2020, knowledge.udacity.com/questions/358225. Accessed 9 Sep. 2025.

“MLA Works Cited: Electronic Sources (Web Publications).” _MLA Works Cited: Electronic Sources - Purdue OWL® - Purdue University_, owl.purdue.edu/owl/research_and_citation/mla_style/mla_formatting_and_style_guide/mla_works_cited_electronic_sources.html. Accessed 29 Aug. 2025.

“MongoDB-Specific Data Manipulation Methods.” _MongoDB-Specific Data Manipulation Methods :: Spring Data MongoDB_, docs.spring.io/spring-data/mongodb/reference/mongodb/repositories/modifying-methods.html. Accessed 19 Sep. 2025.

Obregon, Alexander. “How to Handle File Uploads and Downloads with Spring Boot.” _Medium_, Medium, 17 Apr. 2024, medium.com/@AlexanderObregon/how-to-handle-file-uploads-and-downloads-with-spring-boot-84638463fd6f. Accessed 14 Sep. 2025.

Ozler, Hakan. “Java @Override Annotation.” _Baeldung_, 7 Apr. 2025, www.baeldung.com/java-override. Accessed 17 Sep. 2025.

Phate, et al. “What Is Responseentity for and Why Should I Keep It?” _Stack Overflow_, 10 Apr. 2020, stackoverflow.com/questions/61138943/what-is-responseentity-for-and-why-should-i-keep-it. Accessed 19 Sep. 2025.

Pollack, Mark, et al. _Spring Data Mongodb - Reference Documentation_, 12 May 2023, docs.spring.io/spring-data/mongodb/docs/4.0.6/reference/html/. Accessed 19 Sep. 2025.

r/javahelp, and _jetrun. “Explain like i’m Five - What Is Serializable?” _Reddit_, 2024, www.reddit.com/r/javahelp/comments/1cdo4k2/explain_like_im_five_what_is_serializable/. Accessed 30 Sep. 2025.

“Random vs Secure Random Numbers in Java.” _GeeksforGeeks_, GeeksforGeeks, 23 July 2025, www.geeksforgeeks.org/java/random-vs-secure-random-numbers-java/. Accessed 17 Sep. 2025.

“Serialization and Deserialization in Java.” _GeeksforGeeks_, GeeksforGeeks, 2 June 2025, www.geeksforgeeks.org/java/serialization-and-deserialization-in-java/. Accessed 30 Sep. 2025.

“Serving Web Content with Spring MVC.” _Getting Started | Serving Web Content with Spring MVC_, spring.io/guides/gs/serving-web-content. Accessed 29 Sep. 2025.

“Servlet Authentication Architecture.” _Servlet Authentication Architecture :: Spring Security_, docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#:~:text=SecurityContextHolder%20%2D%20The%20SecurityContextHolder%20is%20where,Spring%20Security’s%20Filters%20perform%20authentication. Accessed 16 Sep. 2025.

Singh, Amandeep. “Spring Security in Spring Boot 2.x Using Websecurityconfigureradapter.” _Medium_, Medium, 8 Mar. 2025, lazy-programmer.medium.com/spring-security-in-spring-boot-2-x-using-websecurityconfigureradapter-15b0b5878c0d. Accessed 8 Sep. 2025.

“Spring - @PostMapping and @GetMapping Annotation.” _GeeksforGeeks_, GeeksforGeeks, 4 Jan. 2025, www.geeksforgeeks.org/advance-java/spring-postmapping-and-getmapping-annotation/. Accessed 15 Sep. 2025.

“Spring Boot - File Handling.” _GeeksforGeeks_, GeeksforGeeks, 23 July 2025, www.geeksforgeeks.org/java/spring-boot-file-handling/. Accessed 11 Sep. 2025.

“Spring Context » 7.0.0-M9.” _MVN REPOSITORY_, mvnrepository.com/artifact/org.springframework/spring-context. Accessed 19 Sep. 2025.

“Spring Security - Authentication Providers.” _GeeksforGeeks_, GeeksforGeeks, 28 Apr. 2025, www.geeksforgeeks.org/java/spring-security-authentication-providers/. Accessed 7 Sep. 2025.

Stein-Kousathana, Eleftheria. “Spring Security without the WebSecurityConfigurerAdapter.” _Spring Blog_, 21 Feb. 2022, spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter. Accessed 3 Sep. 2025.

SudKul. “Nd035-C1-Spring-Boot-Basics-Project-Starter.” _GitHub_, Udacity, Jan. 2025, github.com/udacity/nd035-c1-spring-boot-basics-project-starter. Accessed 29 Aug. 2025.

“Udacity Git Commit Message Style Guide.” _Udacity Nanodegree Style Guide_, udacity.github.io/git-styleguide/. Accessed 29 Aug. 2025.

“Upload Multiple Files in Spring Boot Using JPA, Thymeleaf, Multipart.” _GeeksforGeeks_, GeeksforGeeks, 23 July 2025, www.geeksforgeeks.org/java/upload-multiple-files-in-spring-boot-using-jpa-thymeleaf-multipart/. Accessed 28 Sep. 2025.

“Uploading Files.” _Getting Started | Uploading Files_, spring.io/guides/gs/uploading-files. Accessed 11 Sep. 2025.

“Username/Password Authentication.” _Username/Password Authentication :: Spring Security_, docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html. Accessed 7 Sep. 2025.
