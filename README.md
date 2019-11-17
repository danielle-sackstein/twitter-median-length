# Home Assignment - Twitter Median Length Finder

## Introduction
This project is a Java REST API which connects to the [Twitter Standard search API](https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets).

The project contains a `GET` endpoint:
 - `GET /median?q=<term>` - Searches for given amount of last published tweets which contain the query provided, returns 
 the median length of these tweets.
 
## Overview

### Used frameworks

This project uses 
 - [Spring Framework](https://spring.io) which enables easy REST endpoint mapping, configuration system.
 
 - [Gradle](https://gradle.org/) for dependency management.
 
 - [Twitter4J](https://github.com/Twitter4J/Twitter4J) as an API wrapper to Twitter's Web API.
 
 - [JUnit testing framework](https://junit.org) for tests.

### Implementation overview

| File name                                                                                                                                     	| Short description                                                        	|
|-----------------------------------------------------------------------------------------------------------------------------------------------	|--------------------------------------------------------------------------	|
| [`build.gradle`](https://github.com/danielle-sackstein/twitter-median-length/blob/master/build.gradle)                                               	| Contains the dependencies mentioned above.                               	|
| [`MainApplication.java`](https://github.com/danielle-sackstein/twitter-median-length/blob/master/src/main/java/twitter/median/length/MainApplication.java)     	| The main class of the project which Spring Application runs.             	|
| [`Configuration.java`](https://github.com/danielle-sackstein/twitter-median-length/blob/master/src/main/java/twitter/median/length/Configuration.java)         	| This class includes properties which the application needs from the user 	|
| [`TwitterController.java`](https://github.com/danielle-sackstein/twitter-median-length/blob/master/src/main/java/twitter/median/length/TwitterController.java) 	| The REST controller of the program.                                      	|
| [`TwitterService.java`](https://github.com/danielle-sackstein/twitter-median-length/blob/master/src/main/java/twitter/median/length/TwitterService.java)       	| This class is is responsible for communicating with twitter.             	|

#### Flow

The application works in the following way:

At first a request from the client is fired, the rest controller then forwards the given query to the TwitterService, 
which communicates with the Twitter API, and then calculates the median length of the last tweets received with the
term provided.

#### Experience

Gradle is used to manage all of the libraries used in the app.

I decided to use Spring Framework, because it provides an easy to-use system for building REST APIs 
using simple annotations (such as `GetMapping`, `PostMapping`) to listen to specific REST endpoints.

After I managed to send a HTTP GET request to the application, I to usr an existing Java wrapper - `Twitter4J`
to interact with the Twitter search API. This library allows us to communicate with Twitter search API without sending
requests to it, and then parsing the JSON result which involves creating data classes, etc.

## Running the application

The JAR of the application and an `application.properties` configuration file is [uploaded in the releases tab of this repository](https://github.com/danielle-sackstein/twitter-median-length/releases/tag/v1.0).

Running in the commandline (Windows / Linux) is as following:
```
java -jar twitter-median-length.jar --spring.config.location=path-to-properties/application.properties
```