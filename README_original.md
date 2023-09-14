# Welcome to Zerocopy document-upload test

We have organized a sandbox for you to show your skills to deal with real Zerocopy problem.

The project is composed by two maven modules, the client and the server:

- The client is where you need to develop the UX for the test. You can develop the UX in any way (ReactJS, Angular, Thymeleaf, ...), you don’t need to worry about maven integration with ReactJS or Angular, if you choose it, focus on create a working UX.
- The server is where you need to develop the backend API to receive requests from the client. Right now it’s a basic spring-boot application with some useful dependencies to create endpoints and save the data in H2 database.

The result of this exercise will be the base on which we will perform the technical interview. Try to be clean and give a simple solution to the problem.

## What do you need to do?

- Create a small UX where a user can upload PDF documents and see the list of uploaded documents.
- Create two endpoints, one to receive new documents and another to retrieve the existing documents.

**IMPORTANT NOTES:**
- Just PDF documents can be uploaded, process will be tested with different type of formats.
- Just document name and number of pages should be saved.
- By default, server will be listening at port 8080.
- You need to provide us the access to the GIT repository with your work, so create it public or be ready to provide access to our developers.

If you have any question at anytime, do not hesitate to contact us on this email: developers@zerocopy.be