The idea is to do outside-in but using TDD classic if it makes sense. 
The main goal is to see how the design emerge from a unique class and how this clase
is divided in other responsibilities or clases.

Develop an API that moves a rover around on a grid.
- [ ] You are given the initial starting point (0,0,N) of a rover
- [ ] 0,0 are X, Y coordinates on a grid of (10,10)
- [ ] N is the direction it is facing (i.e N,S,E,W)
- [ ] L and R allow the rover to rotate left and right
- [ ] M allows the rover to move one point in the current direction
- [ ] The rover recibes a char array of e.g RMMLM and returns the finish point after the moves e.g 2:1:N
- [ ] The rover wraps around if it reaches the end of the grid
- [ ] The grid may have obstacles. If a given sequence of command encounter an obstacle, the recover moves up to the last possible point and reports the obstacle e.g 0:2:2:N
