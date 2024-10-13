Lessons learnt during refactoring:
1.The code must have used constants instead of directly using values to verify the conditions. 
2.When there is a && condition inside an if condition, it is better to use another helper function to get the result to keep the CCN low.

Apart from that since the code was modular it was easier to introduce new functions and checks without modifying the code much
