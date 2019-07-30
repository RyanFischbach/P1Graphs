import java.util.*;

public class GraphOriginal
{
	// list of vertex objects
	private ArrayList<Vertex> vertexList = new ArrayList<Vertex>();

	// list of Strings that contain vertex names
	public ArrayList<String> vertexNames = new ArrayList<String>();

	// list of edges
	private ArrayList<Edge> edgeList;

	// whether or not the graph is directed
	private boolean isDirected;

	/*
	 * Constructor: Takes in the file name and initializes the list of edges and vertices
	 * of that file. 
	 */
	public GraphOriginal(String filename, boolean directed)
	{
		edgeList = GraphDataFileReader.readDataFile(filename);
		isDirected = directed;
		for(Edge edge : edgeList)
		{
			Vertex firstVertex = new Vertex (edge.getFirst());
			String firstVertexName = firstVertex.toString();
			if(!vertexNames.contains(firstVertexName))
			{
				vertexList.add(firstVertex);
				vertexNames.add(firstVertexName);
				firstVertex.setEdge(edge);
			}
			Vertex secondVertex = new Vertex (edge.getSecond());
			String secondVertexName = secondVertex.toString();
			if(!vertexNames.contains(secondVertexName))
			{
				vertexList.add(secondVertex);
				vertexNames.add(secondVertexName);	
				secondVertex.setEdge(edge);
			}
		}
		assignEdgesToVertexObject();
	}

	/*
	 * Returns the number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		return vertexList.size();
	}

	/*
	 * Returns the number of edges in the graph.
	 */
	public int getNumEdges()
	{
		return edgeList.size();
	}

	/*
	 * Inserts a vertex into the graph. 
	 */
	public void insert(String vertexName) // vertex
	{
		if(containsVertex(findVertexFromName(vertexName)))
		{
			System.out.println("This vertex is already in this graph.");
		}
		else
		{
			vertexNames.add(vertexName);
			vertexList.add(new Vertex(vertexName));
		}
	}

	/*
	 * Inserts an edge into the graph. Assumes the edge isn't already inserted and the vertexes
	 * aren't connected with 2 separate edges already.
	 */
	public void insert(String firstVertexName, String secondVertexName, double weight) // edge
	{
		edgeList.add(new Edge(firstVertexName, secondVertexName, weight));
	}

	/*
	 * Removes an edge from the graph. Assumes that the vertexes input exist. 
	 */
	public void remove(String firstVertexName, String secondVertexName) // edge
	{
		if(firstVertexName != null && secondVertexName != null)
		{
			for(int i = 0; i < edgeList.size(); i++)
			{
				if(edgeList.get(i).getFirst().equals(firstVertexName) || edgeList.get(i).getFirst().equals(secondVertexName)
						&& edgeList.get(i).getSecond().equals(firstVertexName) || edgeList.get(i).getSecond().equals(secondVertexName))
				{
					edgeList.remove(i);
				}
			}
		}
	}

	/*
	 * Removes a vertex from a graph and its connected edges.
	 */
	public void remove(String vertexName) // vertex
	{	
		if(vertexName != null && vertexNames.contains(vertexName))
		{
			Vertex removeVertex = findVertexFromName(vertexName);
			removeVertex.toString();
			for(int i = 0; i < removeVertex.getEdgeList().size(); i++)
			{
				remove(removeVertex.getEdgeList().get(i));
			}
			for(int i = 0; i < vertexList.size(); i++)
			{
				if(vertexList.get(i).equals(removeVertex))
				{
					vertexList.remove(i);
				}
			}
			for(int i = 0; i < vertexList.size(); i++)
			{
				if(vertexList.get(i).toString().equals(vertexName))
				{
					vertexNames.remove(i);
				}
			}

		}
		else
		{
			System.out.println("Error removing vertex, vertex is null or isn't a part of the graph.");
		}
	}

	/*
	 * Removes an edge from the graph.
	 */
	public void remove(Edge removeEdge)
	{
		if(!edgeList.contains(removeEdge))
		{
			System.out.println("This edge isn't in the graph.");
		}
		for(int i = 0; i < edgeList.size(); i++)
		{
			if(edgeList.get(i).equals(removeEdge))
			{
				edgeList.remove(i);
			}
		}
	}


	
	 /*
	 * Returns the shortest path between two vertices.
	 */
	
	
	public ArrayList<String> getShortestPath(String firstVertexName, String secondVertexName)
	{
		/*
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		ArrayList<Vertex> notVisited = new ArrayList<Vertex>(vertexList);
		ArrayList<Candidate> visited = new ArrayList<Candidate>();
		Vertex start = findVertexFromName(firstVertexName);
		Vertex target = findVertexFromName(secondVertexName);
		assignInitialDistances(notVisited, start);
		visited.add(new Candidate(null, start.toString(), 0.0, false));
		Vertex current = start;
		while(current != null && !current.equals(target) && notVisited.size() != 0)
		{
			addNeighborCandidates(notVisited, candidates, current);
			Candidate smallest = null;
			for(int i = 0; i < candidates.size(); i ++)
			{
				Candidate c = candidates.get(i);
				if(smallest == null || c.getWeight() < smallest.getWeight())
				{
					smallest = c;
				}
			}
			if(smallest != null)
			{
				candidates.remove(smallest);
				visited.add(smallest);
				notVisited.remove(current);
				current = findVertexFromName(smallest.getName());
			}
			else
			{
				current = null;
			}
			
			
		}
		return getPath(visited, start, current);
		*/
		return null;
	}
	
	/*
	private ArrayList<String> getPath(ArrayList<Candidate> visited, Vertex start, Vertex end)
	{
		Candidate current = null;
		ArrayList<String> finished = null;
		for (Candidate c : visited)
		{
			if(c != null && end != null)
			{
				if(c.getName().equals(end.toString()))
				{
					
					current = c;
				}
			}
			
		}
		if(current == null)
		{
			return null;
		}
		else
		{
			finished = new ArrayList<String>();
			finished.add(current.getName());
			
			while(current.getPreviousCandidate() != null)
			{
				boolean done = false;
				int index = 0;
				while(!done)
				{
					for(int i = 0; i < visited.size(); i++)
					{
						index ++;
						if(visited.get(i).equals(current.getPreviousCandidate()))
						{
							done = true;
						}
					}
					done = true;
				}
				
				current = visited.get(index);
				finished.add(current.getName());
			}
			return finished;
		}
		
	}
	*/
	
	/*
	 * Adds neighboring candidates to the list.
	 */
	
	/*
	private void addNeighborCandidates(ArrayList<Vertex> notVisited, ArrayList<Candidate> candidates, Vertex current)
	{
		ArrayList<Edge> possible = current.getEdgeList();
		
		for (Edge e : possible)
		{
			if(indexOf(e.getSecond(), notVisited) != -1)
			{
				double distance = e.getWeight() + vertexList.get(indexOf(e.getFirst(), vertexList)).getDistance();
				
				Candidate hi = new Candidate(notVisited.get(indexOf(e.getFirst(), vertexList)), notVisited.get(indexOf(e.getSecond(),
						vertexList)).toString(), distance, false);
				boolean add = true;
				for (int i = 0; i < candidates.size(); i++)
				{
					Candidate c = candidates.get(i);
					if(c.getName().equals(hi.getName()))
					{
						if(c.getWeight() <= hi.getWeight())
						{
							add = false;
						}
						else
						{
							candidates.remove(i);
							i--;
						}
					}
				}
				if(add)
				{
					candidates.add(hi);
					notVisited.get(indexOf(e.getSecond(), notVisited)).setDistance(distance);
				}
			}
			
		}
	}
	*/
	
	/*
	 * Assigns initial distances to the vertexes. 
	 */
	
	/*
	private void assignInitialDistances(ArrayList<Vertex> notVisited, Vertex vertexIn)
	{
		for(Vertex v : notVisited)
		{
			if(!v.equals(vertexIn))
			{
				v.setDistance(Double.MAX_VALUE);
			}
			else
			{
				v.setDistance(0);
			}
		}
	}
	*/

	/*
	 * Assigns every vertex object the edges that they are connected to. 
	 */
	private void assignEdgesToVertexObject()
	{
		for(Vertex v: vertexList)
		{
			for(Edge e: edgeList)
			{
				if(v.toString().equals(e.getFirst()))
				{
					v.setEdge(e);
				}
			}
		}
	}

	/*
	 * Returns the vertex object from its name.
	 */
	public Vertex findVertexFromName(String vertexName)
	{
		for(int i = 0; i < vertexList.size(); i++)
		{
			if(vertexList.get(i).toString().equals(vertexName))
			{
				return vertexList.get(i);
			}
		}
		return null;

	}

	/*
	 * Returns the edge from the two connected vertices.
	 */
	public Edge findEdgeFromVertices(String vertexName1, String vertexName2)
	{
		for(int i = 0; i < edgeList.size(); i++)
		{
			if(edgeList.get(i).getFirst() == vertexName1 || edgeList.get(i).getSecond() == vertexName1)
			{
				if(edgeList.get(i).getFirst() == vertexName2 || edgeList.get(i).getSecond() == vertexName2)
				{
					return edgeList.get(i);
				}
			}
		}
		return null;
	}

	/*
	 * Checks whether or not two vertexes are connected via breadth first search.
	 */
	public boolean areConnected(String firstVertexName, String secondVertexName)
	{
		Vertex firstVertex = findVertexFromName(firstVertexName);
		Vertex secondVertex = findVertexFromName(secondVertexName);

		if(firstVertex == null || secondVertex == null || indexOf(firstVertexName, vertexList)  == -1
				|| indexOf(secondVertexName, vertexList) == -1)
		{
			return false;
		}

		ArrayList<Vertex> visited = new ArrayList<Vertex>();
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(vertexList.get(search(vertexList, firstVertexName)));

		while(!queue.isEmpty())
		{
			Vertex currentVertex = queue.remove();

			if(currentVertex.equals(secondVertex))
			{
				return true;
			}

			visited.add(currentVertex);

			ArrayList<Vertex> neighbors = getAdjacent(currentVertex.toString());
			for(Vertex v : neighbors)
			{
				if(!visited.contains(v))
				{
					//only adds the node to the queue if it has not been visited
					queue.add(v);
				}
			}
		}
		return false;
	}

	/*
	 * Returns the index of a vertex in the vertex array. 
	 */
	private int indexOf(String vertexName, ArrayList<Vertex> vertexList)
	{
		int index = 0;

		for(int i = 0; i < vertexList.size(); i++)
		{
			if(vertexList.get(i).toString().equals(vertexName))
			{
				return index;
			}
			index ++;
		}
		return -1;
	}


	/*
	 * 
	 */
	public double getShortestPathLength(String firstVertexName, String secondVertexName)
	{
		return 0.0; //still a wip
	}

	private ArrayList<Candidate> verifyCandidate(Candidate newC, ArrayList<Candidate> list)
	{
		boolean foundTarget = false;
		for(int i = 0; i < list.size(); i++)
		{
			Candidate check = list.get(i);
			if(newC.getName().equals(check.getName()))
			{
				foundTarget = true;
				if(!check.getVisited())
				{
					if(newC.getName().equals(check.getName()))
					{
						list.remove(i);
						list.add(newC);
						i--;
					}
				}
			}
		}
		if (foundTarget != true)
		{
			list.add(newC);
		}
		return list;
	}

	public ArrayList<Vertex> getAdjacent(String vertexName)
	{
		Vertex targetVertex = findVertexFromName(vertexName);
		ArrayList<Edge> edge = targetVertex.getEdgeList();
		ArrayList<Vertex> neighborList = new ArrayList<Vertex>();
		for(int i = 0; i < edge.size(); i++)
		{
			String name = edge.get(i).getSecond();
			int count = search(vertexList, name);
			if(count != -1)
			{
				neighborList.add(vertexList.get(count));
			}
			
		}
		return neighborList;

	}
	
	private int search(ArrayList<Vertex> list, String target)
	{
		int index = 0;

		for(Vertex v: list)
		{

			if((v.toString()).equals(target))
			{
				return index;
			}
			index++;
		}
		return -1;
	}

	/*
	 * Returns the other vertex from the edge and vertex entered.
	 */
	public Vertex getOtherVertexFromEdge(Edge edge, Vertex vertex)
	{
		if(edge.getFirstVertex() == vertex)
		{
			return edge.getSecondVertex();
		}
		else
		{
			return edge.getFirstVertex();
		}
	}

	/*
	 * Checks whether the graph contains a vertex.
	 */
	public boolean containsVertex(Vertex checkThisVertex)
	{
		for(Vertex v : vertexList)
		{
			if(v.equals(checkThisVertex))
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * Checks whether the graph contains an edge.
	 */
	public boolean containsEdge(Edge checkThisEdge)
	{
		for(Edge e : edgeList)
		{
			if(e.equals(checkThisEdge))
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * Prints out a list of edges (and their vertices) and returns null. 
	 */
	public String toString()
	{
		for(Edge e : edgeList)
		{
			System.out.println(e.toString());
		}
		return null;
	}
}