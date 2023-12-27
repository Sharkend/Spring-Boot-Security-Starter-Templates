# Spring Security Starter Templates
Boilerplate templates with sample configurations for various common Spring Security implementations.

The templates are based off of [Ali Bouali's implementation](https://github.com/ali-bouali/spring-boot-3-jwt-security) with changes in nomenclature and folder structure.

## Templates
* **BackendJwt**: 
A minimalist template for server side implementation of stateless JSON Web Tokens (JWTs)
* **BackendJwtLogout**: Implements JWTs that can expire or be revoked
* **BackendJwtLogoutRefreshtoken**: Generates an access token as well as a refresh token (with longer lifespan) to renew expired access tokens.
* **BackendJwtLogoutRoles**: Implements role and permission based access in addition to features of *BackendJwtLogout* (does not include refresh tokens)
* **BackendJwtLogoutRolesSwagger**: Adds OpenApi v3 (Swagger UI) documentation. Sample configuration is added to select methods in `ManagementController.java` which may be duplicated and modified as required.
* **BackendJwtLogoutRolesSwaggerChangepassword**: Added functionality for changing passwords


## Getting Started
* Navigate to `/src/main/resources` of the desired template, rename `rename_application.yml.txt` to `application.yml` and modify it with project specific configuration and database credentials.
* Refactor classes, packages, and imports as necessary before integrating the template into a project.
* The templates are designed for Spring Boot 3 and Spring Security 6. Compatibility changes may be required for other versions.