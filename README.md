## PIGGYBACK   :blue_car: :pig:

# Finding overlapping paths - use case
* There could be 'm' riders who are travelling from any street to any other street AND are willing to provide lift.
* There could be 'n' passengers who are looking for a ride from any point a to b.
* Suppose there is a rider Alice who is travelling from BRITTANY MANOR DR to NOAH WEBSTER CIR.
* There is a passenger Bob who wants to go from S PLEASANT ST to CHURCHILL ST.
* There could be X number of shortest paths which Alice could take and some of these shortest paths would have S PLEASANT ST and CHURCHILL ST as the stops along the route.
* Once a passenger puts a request, we must show Alice any alternative paths which are still shortest in nature but if taken, Bob can benefit by taking a lift from Alice.
* This is our eventual use case, adjusting and matching routes so that, Bob can benefit from Alice but making sure Alice doesn't have to take a long way.


# Data and path finding steps
* We use the Amherst GIS Routes data to get an accurate street map of Amherst town.
* Using the Routes json file we are able to create a graph of the entire Amherst town , where each node is a street name, and connects to other streets ( neighbours of this node )
* We are running a modified BFS on top of this, to get a shortest path of any source to any destination.


# Pending items
* We should do all pair shortest paths and store it in one shot because route data will be constant and won't change.
* We can convert this unweighted graph of the city into a weighted graph to provide better directions. Why? Because many streets are long as compared to another and it doesn't make much sense to take their path length as 1 when compared with much smaller streets. The dataset has segments for long streets with multiple intersections. As a beginner step, we can take the sum of segments as the weight of that path. At a high level, more segments for a street does represent the qualitative length of the street.


# Future items - inclusion of these items dependent on time available.
* We can show the route percentage match for each rider whenever an overlapping passenger request is found. And similary for passenger we can show a similar stat ( More thought needs to be given to this)
* A rider won't know the exact street on which they are booking the ride from. We can use their GPS data to identify the coordinates and find the closest street to their coordinate by simple coordinate distance matching. ( We have the coordinate data for each street in the dataset. )
