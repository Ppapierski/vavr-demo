# A tale of Either and friends - Introduction to Vavr
## Overview

This project was created as introduction and demonstration of the Java VAVR library. 
Helidon SE  was used as a simple functional Web Server framework to enhance the experience.    

For more info on VAVR see:

https://docs.vavr.io/

https://www.vavr.io/

For more info on the Helidon framework see:

https://helidon.io/#/

https://helidon.io/docs/latest/#/se/guides/02_quickstart

## Build and run

###With JDK11+
Just run using your favorite IDE... Be sure to enable annotation processing!

Or use the command line:
```bash
mvn package
java -jar target/helidon-quickstart-se.jar
```

## Agenda
The demonstration was separated into 5 introductory parts for easier comprehension on the presented themes.
The sixth part is an exemplary application that uses the earlier presented parts in a working environment

### Collections
This section contains examples of usage of the vavr.collections API. 

- Tuple for easier, typesafe passing of objects of different type.
- List for a functional style, iterative and immutable implementation of a List
- Map for a functional style, iterative and immutable implementation of a Map
- Set for a functional style, iterative and immutable implementation of a Set

Look into the a_Collections class for examples from the presentation.

### Java Lookalikes
This section contains examples of usage of Vavr's interpretation of the API that were added to Java 8+: 
- Option as a more flexible but also tricky alternative to Java's Optional
- Stream with an API that far exceeds that of Java's Stream - with little drawbacks
- Future that is quite comparable with CompletableFuture, but when used with API.For() introduces a more intuitive merging of multiple results.

Look into the b_JavaLookalikes class for examples from the presentation.

### Functions everywhere
This section compares the Java Functions to Vavr's Function's.
It discloses how Vavr's function enhances the general usability and flexibility of using functions and lambda expressions. Specifically:
- composition
- lifting
- partial application
- memoization

Look into the c_FunctionsEverywhere class for examples from the presentation.

### Cool new and shiny
This section features:
- Either pseudo-monadic container - used for flow control when an operation may be Either Successful or Unsuccessful 
- Try pseudo-monadic container - used when an operation may throw exception. 
  A more elegant substitute to try..catch blocks in Java.
- the Validation applicative functor that allows to validate multiple inputs and merge all the unsuccessful validation into one. 
- the Lazy for lazily evaluated Suppliers

Look into the d_CoolNewAndShiny class for examples from the presentation.

### API
This section features the functionalities that ara available through the API class:
- API.unchecked() for enhanced handling exceptions inside Streams
- API.TODO() for easier handling of unimplemented code
- API.TODO() for easier handling of unimplemented code
- API.For() in all of its forms, that is based on Scala's for comprehension. 
  It is used to easily merge all Vavr's Collections and Monadic containers(Future, Try, Option, Either,  etc.)   
- API.Match that is based on Scala's Pattern Matching. 
  A very flexible and powerful tool that makes using if statement almost obsolete.  

Look into the e_API class for examples from the presentation.

### The working web server application with demonstration 
#### Example of using Validation
Check the ClientValidator class see how More complicated Validations may be used

#### Example of using Either
Check the Validator class and the ClientRestService#createClientHandler method to see how to manage a request flow.

#### Example of using Pattern Matching
Check the ClientFraudValidator and Model classes see how to use Pattern Matching and how to use it with complicated Pojo's

#### Example of using Try
Check ReportGenerator and ReportGenerator2 classes to see how elegantly exceptions may be handled.

#### But does it work?
Run the BankApp, and try to play with these curl commands:

----

curl --location --request POST 'http://localhost:8080/bank/client' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "John",
"middleName" : "Johny",
"lastName" : "Doe",
"idNumber":  "123abc456"

}'

---

curl --location --request POST 'http://localhost:8080/bank/client' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "Jam",
"lastName" : "Łasica",
"idNumber":  "123abc456"

}'

----

curl --location --request POST 'http://localhost:8080/bank/client' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "@#$",
"middleName" : ".,/%",
"lastName" : "123",
"idNumber":  "123abc456"

}'

----

curl --location --request POST 'http://localhost:8080/bank/client' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "Zbigniew",
"middleName" : "Tadeusz",
"lastName" : "Ziobro",
"idNumber":  "123abc456"

}'

----

curl --location --request POST 'http://localhost:8080/bank/client' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "Jarosław",
"middleName" : "J",
"lastName" : "Kaczyński",
"idNumber":  "123abc456"

}'

----

curl --location --request POST 'http://localhost:8080/bank/client' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "Jacek",
"middleName" : "Bańka",
"lastName" : "Sasin",
"idNumber":  "123abc456"

}'

----

curl --location --request GET 'http://localhost:8080/csv/report  -> the output will be saved in the /tmp/reports directory

