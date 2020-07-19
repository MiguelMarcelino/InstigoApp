# Changelog
All changes made to this project will be documented in this file

## [Unreleased]

## [0.1.2] - 2020-07-19
### Changed
- Login Page was finished. Started testing with login Backend
- Login service is now Authentication Service
- UserCatalog was changed and errors are now based on exception throwing
- Changed User Model. Now has fields firstName and lastName
- Changed UserDTO to incorporate changes made to the user model
- Changed Database to incorporate changes made to the user model


### Added
- Method for user login
- Start of Metod for user update
- Added new class to receive data from Login page
- "_helpers" folder with fake backend for testing
- Added classes for interception of errors and requests to backend
- New component events for all upcoming user events

-------------------------------------------------------------------------

## [0.1.2] - 2020-07-17
### Added
- Certificates for SSL connection establishment
- New startcommand to test with ssl. Still not working

-------------------------------------------------------------------------

## [0.1.2] - 2020-07-15
### Changed
- Login Page was changed. Now trying to do login with simple mysql database
- User Service updated. 
- Continued working on User Component. Signup still not working
- Finished working on database. Everything now working

-------------------------------------------------------------------------

## [0.1.2] - 2020-07-14
### Changed
- Testing with community Component in Angular
- Changed Database Structure


## [0.1.2] - 2020-07-13
### Added
- New Angular Interface
- Login Page for login with firebase
- Working Communities Page 
- Prepared bootstrap navbar
- Added new home page component 

### Changed
- Completed Community Service. Everything is now working (tested with Postman)

-------------------------------------------------------------------------

## [0.1.2] - 2020-07-08
### Added
- Creation of a general community where all users belong to called "ALL_USER_COMMUNITY"
- Creation of 3 main Roles: "DEFAULT_ROLE" where users belong by default, "MAINTAINER_ROLE", which is the role 
maintainers of the system have and ADMIN_ROLE, which is the role of the admin

### Changed
- All instances of Statement are now PreparedStatement to prevent SQL Injection Attacks
- Chaged Roles DB Table to include a row with a foreign key to the community a role belongs to
- Created constants for all SQL Queries
- Improve Project Structure

-------------------------------------------------------------------------

## [0.1.1] - 2020-07-07
### Changed
- Changed Project Structure (mainly UserManagementService and RoleService)
- Changed name of UserRoleService to UserManagementService
- RoleService is now being used again
- Roles are no longer managed by UserManagementService
- Role authorizations are now based on authorization levels, which is an integer ranging from 1 to 3
- Changed controllers to include only GetMapping and PostMapping
- Changed some GetMapping requests to PostMapping for data transfer

### Removed
- Permissions (Permission class) where removed in favour of an integer that is now the authorization level