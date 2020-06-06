The MVP of a dating app similar to Tinder:
Profiles of other users are shown to a user, he can either like or dislike them. As a result he can chat with the users he liked.

How to deploy and run it:
- A connection to a database is required to run the app.
- src/main/java/org/app/ServerApp.java class contains server port and variables USER_NAME, PASSWORD, URL needed to be defined in the code.
USER_NAME -> Username for database
PASSWORD -> User's password to database
URL -> Database Url
- As an alternative, it's possible to have environmental variables "PORT", "JDBC_DATABASE_USERNAME", "JDBC_DATABASE_PASSWORD", "JDBC_DATABASE_URL" respectively


Originally it was set up to be deployed at https://www.heroku.com
Link to already deployed version: https://tinder-app-mvp.herokuapp.com
To sign in you can register here: http://tinder-app-mvp.herokuapp.com/registration
Or use test user credentials: 
Login: test
Pass: test123


In collaboration with https://github.com/iturkan6
