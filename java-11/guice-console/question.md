# Question

## Design and implement a console application in any program language that adds alerts for temperature values from console input. 

### The application should:

- On the startup, get the user input of the freezing threshold, the boiling threshold, and a fluctuation value.
- Alerts are generated when a specific threshold has been reached or exceeded.
- Alerts should not be repeatedly triggered if the temperature is fluctuating around the thresholds within the range of fluctuation value.
- Alerts are defined with directions: 
  - “freezing” and “unfreezing”
    - Alert “freezing” is triggered if the previous temperature was above freezing threshold, 
    - Alert “unfreezing” is triggered if the previous temperature was below (freezing threshold + fluctuation value).
  - "boiling" and "unboiling"
    - Alert “boiling” is triggered if the previous temperature was below boiling threshold, 
    - Alert “unboiling” is triggered if the previous temperature was above (boiling threshold - fluctuation value).

## For example:
```
considering the freezing threshold is set to 0, the boiling threshold is set to 100, and fluctuation value is set to 0.5
```

### Sample 1:
- With the following temperature inputs:
```
 4.0 1.0 0.5 0.0 -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 2.0
```
- The output will be:
```
 4.0 1.0 0.5 0.0 freezing -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 unfreezing 2.0
```
### Sample 2:
- With the following temperature inputs:
```
 5.0 -0.5 0.5 -0.2 100 101
```
- The output will be:
```
 5.0 -0.5 freezing 0.5 -0.2 100 unfreezing boiling 101
```
### Sample 3:
- With the following temperature inputs:
```
 0.0 0.3 0.5 0.4 0.7
```
- The output will be:
```
0.0 freezing 0.3 0.5 0.4 0.7 unfreezing
```
