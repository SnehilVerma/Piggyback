## Setup steps

### Prerequisites to test the project after pulling.
1) Install npm if not installed. It will be needed to run react client.
2) Use IntelliJ if possible ( makes things easier ) else you can use anything  ¯\\ _(ツ) _/¯


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


MongoDB setup:
* Download MongoDB compass to visualize the database.
* The cluster and the databse is already created. DB name : **PiggyBack**, right now only one collection exists : **Users**.
* MongoDB spring boot dependency setup in the pom file.
* Checked saving and querying dummy data. ✔️
* You can find the Mongo connection srv string in the **application.properties** file of the Spring Boot project.

If this setup works for you, you should be good to go and build functionalities on top of this setup.



<br>
<br>

### Async data fetching ideas ###

RequestRide endpoint: <br>


(Initial thoughts and ideas) - Need to discuss tradeoffs between WebSockets and ServerSide events.
1. Avoid polling/long polling - easy to implement but not used much anymore.
2. WebSockets - heavily used but overkill for our project.
3. SSE - best of both worlds for now - easier learning curve and used in industry ( so good to learn )

/piggyback/request_ride
POST
{src:"X",dst:"Y",time:"date/time"}


Must go through : 
1) https://turkogluc.com/server-sent-events-with-spring-boot-and-reactjs/
2) https://medium.com/yemeksepeti-teknoloji/what-is-server-sent-events-sse-and-how-to-implement-it-904938bffd73

1. We'll be using Server Side Events concept for this api. 
2. The client will be using EventSource interface to subscribe to the server emitter. ( setup a connection with server )
3. Once the client sends request, we deliver the request to the queue/job which will try to match up a rider with a passenger. Once that is achieved, the server side emitter will be responsible to send the update back to the client. All this will be done asynchronously.
4. The client cannot make any further request unless they exit the waiting screen on UI. ( in which case we close the connection )
5. Other are we close the connection ( when the ride is booked ).

