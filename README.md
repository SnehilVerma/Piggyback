## PIGGYBACK   :blue_car: :pig:


The students of UMass often face the issue of finding the means to travel within Amherst area. 
One of the most common means of transportation for such cases is the PVTA bus, but it covers only limited routes. For instance, people like us living at The Boulders, have to change buses twice and travel for about 30 minutes, excluding the wait time, to get to the closest Walmart, which is only a 5-minute drive away.
We want to solve this problem by designing a carpooling system that helps match people who are driving to within the city with potential students looking for a ride.
We call our App “Piggyback”
We are planning to extend the idea to support travel to major airports nearby as well.


# Finding overlapping paths - use case
* There could be 'm' riders who are traveling from any street to any other street AND are willing to provide lift.
* There could be 'n' passengers who are looking for a ride from any point a to b.
* Suppose there is a rider Alice who is traveling from BRITTANY MANOR DR to NOAH WEBSTER CIR.
* There is a passenger Bob who wants to go from S PLEASANT ST to CHURCHILL ST.
* There could be X number of shortest paths which Alice could take and some of these shortest paths would have S PLEASANT ST and CHURCHILL ST as the stops along the route.
* Once a passenger puts a request, we must show Alice any alternative paths which are still shortest in nature but if taken, Bob can benefit by taking a lift from Alice.
* This is our eventual use case, adjusting and matching routes so that, Bob can benefit from Alice but making sure Alice doesn't have to take a long way.

# Data and path finding steps
* https://maps2-amherstma.opendata.arcgis.com/datasets/street-centerlines/explore?location=42.378573%2C-72.512894%2C15.84
* We use the Amherst GIS Routes data to get an accurate street map of Amherst town.
* Using the Routes json file we are able to create a graph of the entire Amherst town , where each node is a street name, and connects to other streets ( neighbors of this node )
* We are running a modified BFS on top of this, to get a shortest path of any source to any destination.

# Requirements
* User should be able to Register and provide username and password.
* User should face error if username already exists.
* User should be able to Log in using their login details.
* User should face error if login details are incorrect.
* User should be able to request a ride and provide source and destination information.
* User should be able to view matched rides, and be able to choose a ride.
* User should be able to pair with a driver/passenger and see information about the ride they chose.

# Our tech stack
* Backend : Spring Boot framework and Java.
* Client : React/React Native.
* Database : MongoDB
* Scripting/Data processing : Used Python scripts for Amherst GIS data manipulation.

# Future items - inclusion of these items dependent on time available.
* Add route percentage match for the passenger with the rider.
* Similarly, we can show the route percentage match for each rider whenever an overlapping passenger request is found.
* Use passenger GPS data to identify the coordinates and find the closest pick-up to them by simple distance matching.
* Use Google Maps API to have dynamic information about routes and traffic.
* Incorporation of two-way rating in the app.
* Applying payment coupons in collaboration with local shops as an incentive to use the application and boost local small businesses.
