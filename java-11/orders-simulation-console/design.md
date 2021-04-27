# Design 

## Business Cases Design

### main workflow cases
- when order event is coming then send order event to kitchen and courier
- when kitchen received order event, then cooked it and send it to shelf
- when courier received order event, then pick up and deliver that specific order

### shelf cases
- when received event form kitchen and single corresponding temperature shelf is not full
  then put the order into corresponding temperature shelf
- when received event form kitchen and single corresponding temperature shelf is full and overflow shelf is not full
  then put the order into overflow shelf 
- when received event form kitchen and single corresponding temperature shelf is full and overflow shelf is full
  then remove a random order from the overflow shelf and put order into overflow shelf

### refresh orders state in shelves room
- depend on the shelf life equation remove order from shelves which deteriorate is 0 in every new order into shelf and before picked up.
- when overflow shelf is full 
  then move one existing order from overflow shelf to corresponding temperature shelf
  
### courier cases
- when the order in shelves
  then pick up and deliver it
- when the order not in shelves
  then cancel the order

## Shelves Room Design
use 3 hash-map for saving the temperature-shelf, order-shelf, shelf-orders relationship.


## Event Driven Design
save all main action to event storage. 
at the same time we can leverage the event storage help us to verify the test result.


 
 
  

