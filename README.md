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

### GeoLocation
#### each GeoLocation has:
* x<br/>
* y <br/>
* z <br/>

### EdgeData
#### each EdgeData has:
* Src<br/>
* Dest <br/>
* Wight <br/>
* Info <br/>
* Tag <br/>

### DirectedWeightedGraph
#### each DirectedWeightedGraph has:
* hashnode<br/>
* hashedge <br/>
* MC <br/>

### DirectedWeightedGraphAlgorithms
#### each DirectedWeightedGraphAlgorithms has:
* graph<br/>

## Results
loading time in ms:
around 100 vertices 100 edges:
34 mil/sec
1,000 vertices 10,000 edges:
51 mil/sec
10,000 vertices 100,000 edges:
396 mil/sec
## UML Diagram
<img src="https://github.com/annapinchuk/ElevatorsEx1/blob/master/UML.png" width="600">
