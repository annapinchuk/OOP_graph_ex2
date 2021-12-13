## Graphs - Ex2 - OOP

### Defining The Problem
Graph project allows you to display directed graphs.

## References
The links of the algorithms:
* [BFS](https://en.wikipedia.org/wiki/Breadth-first_search)
* [Shortest path problem](https://en.wikipedia.org/wiki/Shortest_path_problem#Single-source_shortest_paths)
* [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
* [Travelling salesman problem](https://en.wikipedia.org/wiki/Travelling_salesman_problem)
* [Graph center](https://en.wikipedia.org/wiki/Graph_center)
                              

## Code Design - classes
### NodeData 
#### each NodeData has:
* Key <br/>
* Location<br/>
* Weight <br/>
* Info<br/>
* Tag<br/>

### Point3D
#### each Point3D has:
* x<br/>
* y <br/>
* z <br/>

### EData
#### each EData has:
* Src<br/>
* Dest <br/>
* Wight <br/>
* Info <br/>
* Tag <br/>

### DGraph
#### each DGraph has:
* hashnode<br/>
* hashedge <br/>
* EdgeZise <br/>
* MC <br/>

### Graph_Algo
#### each Graph_Algo has:
* DGraph<br/>

### Ex2
#### The jar and main that runs everything

### My_GUI
#### runs the GIU actions

### Graph_Panel
#### runs the panle and paint actions

## Results
loading time in ms: <br/>
around 100 vertices 100 edges: <br/>
34 mil/sec <br/>
1,000 vertices 10,000 edges: <br/>
51 mil/sec <br/>
10,000 vertices 100,000 edges: <br/>
396 mil/sec <br/>
## UML Diagram
click to see the full uml
<img src="https://github.com/annapinchuk/OOP_graph_ex2/blob/master/oop_ex2/uml.png" width="1000000">
