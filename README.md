# Zerocopy PDF Upload Test

This is my submission for the technical assessment provided by [zerocopy](https://zerocopy.be/), the instructions for which can be found in the original [README.md](/README_original.md).

## Launching the Applications

I have provided a docker-compose.yaml file along with a Dockerfile to make it easy to launch the server-client application. To build and deploy the two docker images simply navigate to the project's root directory and run the following command:

`docker-compose up`

If maven hangs while downloading dependencies please make sure you are not connected to any vpns.

The client will be listening on localhost:8080 and the server on localhost:8081.

My solution consists of three sub-modules.
1. A shared library 'api-shared' which includes an interface and the Data Transfer Objects (DTOs) which are used to pass data between the different elements of the application. It also includes the Open API 3.0 definition which is exposed by the server and consumed by the client.
2. The server, built with Spring Boot and Spring Data and exposing a REST API to upload pdf files.
3. The client, built as a Spring MVC application with Thymeleaf as the template rendering agent and HTMX on the front end.

## What is HTMX?

[HTMX](https://htmx.org/) is a light-weight javascript library which is designed to extend the functionality of HTML though the addition of custom HTML attributes. 

It allows developers to attach HTTP requests to any HTML element, not just forms and anchors. These requests expect to receive HTML fragments as the response body, with which HTMX will then replace any element on the page. It does this without the need to reload the whole page as was previously the case with applications made with Thymeleaf. 

Thus, we can deliver the seamless feeling and responsive UX of single-page application frameworks such as React and Angular without the need to run a large amount of javascript on the client. 

## Things to improve

If I was to develop this project further the first thing I would look to do would be to include shared exceptions in the shared library to enable easier error handling across different parts of the application, in particular when an error occurs during a request from the client to the server.