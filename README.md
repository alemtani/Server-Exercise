# Server Exercise
Server project for MIW

## Application
I used the Maven architecture to import the Rest Repositories, JPA, and H2 dependencies. I also used LDAP for authentication. It was initiated using the Spring Boot Initializer. I created a repository of items that stored the basic properties, as well as storing the number of views and times for surge pricing. I used GetMapping to interact with the server and process the HTTP request, adding a /delete endpoint after the {id} to process a delete request. There are also endpoints to query the list of items and see an individual item. The surge pricing was developed by storing a start time from when the item is created, and then adding to views each time a GET request for the individual item was called by different users. The price would increase by 10% afterwards, as indicated in the ItemController, until the hour is up. Then the clock resets.

## Data Format
I used the JPA repository of items. A GET request could be made simply by the /items or /item/{id} extensions, and the response would be the list of items or properties of the individual requested item. A DELETE request would be made with the /delete extension, which would simply delete the item and the memory would cease to exist in the list.

## Authentication
I used LDAP and followed the tutorial on Spring Boot, because in reality I had no idea how to authenticate before.

## Server
The application can be run from the command line using `mvnw spring-boot:run` on a Windows terminal, and then navigate to http://localhost:8080 to be redirected to the login page. Choose any login from the LDIF file of authenticators (Hint: one of the usernames is `ben` and the password is `benspassword`) and you will be redirected to the homepage. Then do any of the extensions to do your API.

## Conclusion
I realize that this is not the best project, or probably does not fit all the requirements. However, I did my best to meet the requirements for an API. One can retrieve the current inventory using a GET request and the /items or /items/{id} endpoints, although authentication is required. Authentication is required to "buy" an item, which should delete the item from the inventory, using the /items/{id}/delete endpoint.

Furthermore, I followed the four (five) steps:
1. Get Hello World! working, which is sort of the homepage.
2. Get endpoint /items to loop through DB of items
3. Have endpoint /delete to buy item, and when querying /items again there are less items
4. Use a price surging model (sort of)
5. Authentication using LDAP

Unfortunately, I did not get into tests, and am not sure how to do them.

## Acknowledgements
Thanks to the Spring guides for assisting me through this process.
