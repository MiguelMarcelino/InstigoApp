# Changelog
All changes made to this project will be documented in this file

## [Unreleased]

## [0.1.5] - 2020-09-03
### Added
- Class to confirm identity of users when communities are created and deleted
- OperationsManager class to manage operations from Database
- operations table in database to store all operations for roles
- New method for sending community list to frontend

### Changed 
- roles table is being used again. New modifications to role table
- checked database table names and structure for best practices

### Deleted
- Enumerator to store roles

-------------------------------------------------------------------------

## [0.1.4] - 2020-08-13
### Added
- New feature to see events from created communities
- Added created communities to user profile
- New logo and new name for App: Now called Instigo

### Changed 
- Visual tweaks in preparation for presentation
- Updated Events page (now has events from created communities)
- Minor visual changes to user profile foto

-------------------------------------------------------------------------

## [0.1.4] - 2020-08-12
### Added
- New page for user to read feedbacks

### Changed
- Method for retrieving community lists from backend
- Date of feedback publication was added
- Feedback menu changes for Admin Role

-------------------------------------------------------------------------

## [0.1.4] - 2020-08-10
### Added
- Delete Community feature
- Delete Event feature
- Component for when there is no content found

### Changed
- Role based creation and deletion finished
- Changed interface for creating events and communities
- Fixed bugs on creating event service

### Review
- Backend receiving hasSubscribed in CommunityDTO
- Safety checks for User Role and User data authenticity

-------------------------------------------------------------------------

## [0.1.4] - 2020-08-09
### Added
- Backend method to get communities that the logged in user has created

### Changed
- Create Event page frontend finished (still needs testing)
- Changed Database tables "Communities" and "Events" that now contain information
about the user that has created them
- Changed backend to receive and account for creator of communities and events

-------------------------------------------------------------------------

## [0.1.4] - 2020-08-08
### Added
- Added new role-based login
- Added page to create Communities
- Started creating page to create events

### Changed
- AuthGuard now takes roles into account
- App routing now takes roles into account
- Adapted backend for role-based login
- Changed database models to allow for role-based login

-------------------------------------------------------------------------

## [0.1.3] - 2020-08-07
### Added
- New Feedback backend
- New Feedback Service Frontend
- Login error Mesages in Frontend
- Signup error messages in Frontend

### Changed
- Feedback frontend interface
- Backend API calls changed

-------------------------------------------------------------------------

## [0.1.3] - 2020-08-06
### Added
- Subscribe and Unsubscribe features now fully work

### Changed
- Interface is now fixed
- Finished page for user-profile
- Home page after login changed link to User-Page

-------------------------------------------------------------------------

## [0.1.3] - 2020-08-05
### Added
- Role backend was completed
- New page for user profile

### Changed
- Fixed bugs in the interface
- New interface for events page

-------------------------------------------------------------------------

## [0.1.3] - 2020-07-27
### Changed
- Sign up page now woking
- Corrected mistakes in backend
- Corrected mistakes in login page component
- Changed userModel
- Changed Interface of Community list page

### Added
- New option to subscribe to communities in community list page

-------------------------------------------------------------------------

## [0.1.3] - 2020-07-26
### Changed
- Event page finished and working with backend
- Reviewed Event microservice backend
- Corrected error Identifiable model
- Corrected sending of events - now sending EventDTO instead of Event

### Added
- New interface look
- New home page
- New page to welcome user when not logged in
- New contacts page

-------------------------------------------------------------------------

## [0.1.2] - 2020-07-20
### Changed
- Login Page is now working
- Prepared Event Service backend
- Updated Database table to receive new User values
- Events from Communities are now managed by Event Service instead of User Service

-------------------------------------------------------------------------

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