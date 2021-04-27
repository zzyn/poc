# Question

## order conditions
- Orders must be parsed from the file and ingested into your system at a rate of 2 orders per second
- You are expected to make your order ingestion rate configurable
- Each order should only be ingested once
- when all orders have been consumed, your system should terminate
- unit of order age is second
- when order delivery, order age start to accumulate

## kitchen conditions
- The kitchen should receive ingestion rate(2) delivery orders per second
- The kitchen should instantly cook the order upon receiving it and then place the order on the best-available shelf to await pick up by a courier.

## shelf conditions
- Each order should be placed on a shelf that matches the order’s temperature
- If that shelf is full, an order can be placed on the overflow shelf
- If the overflow shelf is full, an existing order of your choosing on the overflow should be moved to an allowable shelf with room
- If no such move is possible, a random order from the overflow shelf should be discarded as waste (and will not be available for a courier pickup)

## courier conditions
- Upon receiving an order, the system should dispatch a courier to pick up and deliver that specific order 
- The courier should arrive randomly between 2-6 seconds later
- The courier should instantly pick up the order upon arrival
- Once an order is picked up, the courier should instantly deliver it

## shelves 
| Name              | Allowable Temperature(s) | Capacity |
| :---------------- | :----------------------: | :------: |    
| Hot shelf         | Hot                      | 10       |
| Cold shelf        | Cold                     | 10       |
| Frozen shelf      | Frozen                   | 10       |
| Overflow shelf    | Any temperature          | 15       |

## shelf life conditions
- Orders have an inherent value that will deteriorate over time, based on the order’s shelfLife and decayRate fields

- Orders that have reached a value of zero are considered wasted: they should never be delivered and should be removed from the shelf. Please display the current order value when displaying an order in your system’s output.
  ```
    value = (shelfLife - orderAge - decayRate * orderAge * shelfDecayModifier) / shelfLife
  ```
- shelfDecayModifier is 1 for single-temperature shelves and 2 for the overflow shelf.



