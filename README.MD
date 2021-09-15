# Client Keycloak application.

## How is it built.

* **application.yaml** contains information required for connection to authorization server:
    1. Realm name
    2. Authorization server URL
    3. Resource (client ID)
    4. And security constraints that specifies which roles are allowed to access for specific URLs
* **ApiController** just and ordinary REST controller, nothing more.
* **HomeController** just another simple HTTP controller (not REST, for test purposes).
* **KeyCloakAppApplication** Spring Boot runner
* **SecurityConfig** maybe the most complicated part of the application
    1. Contains four methods configuring how authorization server will behave when user tries to access guarded by
       authorization server resources.
    2. Note that configure(HttpSecurity http) method contains commented instructions with role based configuration. In
       my test example that block won't affect at all thus I've commented it out.

## How to test client app using **Postman**.

### In our example we need to get **bearer token** using Postman. So let's do it.

1. Enter Keycloak realm's [URL](http://192.168.0.133:8080/auth/realms/dev/protocol/openid-connect/token)
2. Change method to **POST**
3. Pass x-www-form-urlencoded parameters:
    1. resource: dev
    2. grant_type: password
    3. client_id: my-app
    4. username: <your_keycloak_user_with_permissive_role>
    5. password: <your_password>
4. Send POST request to the authorization server
5. It'll answer with JSON body where you can find an object called `access_token` (usually the first object inside body)
   .
6. Add another tab in Postman and send **GET** request to the client application _(http://your_ip:8081)_.
7. Inside _Headers_ add parameter `Authorization: Bearer <access_token>` from 5th step.
8. You should get access to resources located on client application
9. Note! The role `user` has access to `/,/customers`. You can't access /admin because that role must be bound to your
   username.