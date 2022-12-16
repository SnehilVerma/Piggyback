## PIGGYBACK   :blue_car: :pig:

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

# Future items - inclusion of these items dependent on time available.
* We can show the route percentage match for each rider whenever an overlapping passenger request is found. And similarly for passenger we can show a similar stat ( More thought needs to be given to this)
* A rider won't know the exact street on which they are booking the ride from. We can use their GPS data to identify the coordinates and find the closest street to their coordinate by simple coordinate distance matching. ( We have the coordinate data for each street in the dataset. )
* Use Google Maps API to have dynamic information about routes and traffic.
* Incorporation of two-way rating in the app.
* Applying payment coupons in collaboration with local shops as an incentive to use the application and boost local small businesses.

# Requirement
* User should be able to Register and provide username and password.
* User should face error if username already exists.
* User should be able to Login using their login details.
* User should face error if login details are incorrect.
* User should be able to request a ride and provide source and destination information.
* User should be able to view matched rides, and be able to choose a ride.
* User should be able to pair with a driver/passenger and see information about the ride they chose.
