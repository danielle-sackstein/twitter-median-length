# Home Assignment

## Introduction
This project is a Java REST API which connects to the [Twitter Standard search API](https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets).

The project contains a `GET` endpoint:
 - `GET /median?q=<term>` - Searches for given amount of last published tweets which contain the query provided, returns the median length of these tweets.
 
## Technical details

### Used frameworks

This project uses 
 - [Spring Framework](https://spring.io) which enables easy REST endpoint mapping, configuration system.
 
 - [Gradle](https://gradle.org/) for dependency management.
 
 - [Twitter4J](https://github.com/Twitter4J/Twitter4J) as an API wrapper to Twitter's Web API.
 
 - [JUnit testing framework](https://junit.org) for tests.

### Implementation details

