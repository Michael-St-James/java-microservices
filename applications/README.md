## `/applications`

This folder is used for grouping components together into a single application runtime. It is not required.

#####  Use cases for grouping components:
1. Reduce complexity of infrastructure and orchestration

    By grouping our components together into a single runtime instance we can greatly reduce the amount of infrastructure required to run those components down to a single server. Additionally we do not need to define individual configurations for every component in the group. 

3. Testing or developing against a single application instance

    It is much easier to run a collection of microservices with a single click if they are bundled into the same application.


#####  Bad reasons to group components together:
1. To share schema elements or code

    Each component should be decoupled from the data model of the others. This decreases coupling and circular dependencies and increases the evolvability of the code base.
    
    If code must be shared between two components, a separate library should be created.
