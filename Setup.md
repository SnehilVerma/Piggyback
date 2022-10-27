## Setup steps

### Prerequisites to test the project after pulling.
1) Install npm if not installed will be needed to run react client.
2) Use IntelliJ if possible, makes things doing easier else use anything  ¯\_(ツ)_/¯


Backend - Spring Boot: ( Description - not required to run these again )
* use Spring initializer to generate initial dependencies. ( Maven, Web starter and Redis framework( for now ))
* Build project using Maven Build
* Add Model,View, Controllers directories.
* Create a test post endpoint (/queryEndpoint) which accepts a pickup and drop location and returns a stringified version of it.
* Tested on local using a curl request.

command to run spring boot project after build :  *./mvnw spring-boot:run* ( inside com.fivetwenty.piggyback )


Frontend - React
* install npm
* install npx via npm
* create-react-app (starter project)
* Create a test endpoint which on page load calls the backend to test the /queryRoute endpoint.
* On page load, check that it works on the ui.

command to run react UI : npm start ( inside src/client directory )

If this setup works for you, you should be good to go and build functionalities on top of this setup.
