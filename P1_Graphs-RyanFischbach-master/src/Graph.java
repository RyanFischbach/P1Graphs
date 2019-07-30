import java.util.*;

public class Graph 
{
	// list of vertices in the graph
	private ArrayList<String> names = new ArrayList<String>();

	// 2D array of the vertices connections with each other
	private double[][] graph;

	// whether the graph is directed
	private boolean isDirected;

	// if there is an empty call to graph
	public Graph()
	{
		graph = new double[0][0];
	}

	// proper call to graph
	public Graph(String filename, boolean directed)
	{
		// determine whether or not the graph is directed
		isDirected = directed;

		// read input from file 
		ArrayList<Edge> edgeList = GraphDataFileReader.readDataFile(filename);

		//check whether edgeList is null
		if(edgeList == null)
		{
			System.out.println("Error: Couldn't read file.");
			return; //end method
		}

		// add all vertex names to the ArrayList of names
		for(Edge e : edgeList)
		{
			String firstVertex = e.getFirst();
			String secondVertex = e.getSecond();
			if(!names.contains(firstVertex))
			{
				names.add(firstVertex);
			}

			if(!names.contains(secondVertex))
			{
				names.add(secondVertex);
			}

		}

		// set the size of the graph
		graph = new double[names.size()][names.size()];

		// assign 0 between vertices
		for(int i = 0; i < graph.length; i++)
		{
			for(int j = 0; j < graph[1].length; j++)
			{
				graph[i][j] = 0;
			}
		}

		for(Edge e : edgeList)
		{
			// find the indexes of the two vertices connected to this edge
			String firstIndexString = e.getFirst();
			String secondIndexString = e.getSecond();

			// assign the indexes as the position of the connecting vertices in the ArrayList of strings
			int firstIndex = names.indexOf(firstIndexString);
			int secondIndex = names.indexOf(secondIndexString);

			// assign the weights of this connection
			if(e.getWeight() != 0)
			{
				graph[firstIndex][secondIndex] = e.getWeight();
			}
			else
			{
				graph[firstIndex][secondIndex] = 1;
			}

			if(isDirected == false)
			{
				if(e.getWeight() != 0)
				{
					// if the weight is 0, assign it as 1 in the graph
					graph[firstIndex][secondIndex] = e.getWeight();
				}
				else
				{
					// if it isn't 0, assign it it's weight
					graph[firstIndex][secondIndex] = 1;
				}
			}
		}
	}

	/*
	 * Returns the number of vertices in the graph. SOMETHING WRONG HERE
	 */
	public int getNumVertices()
	{
		return graph.length;
	}

	/*
	 * Returns the number of edges in the graph. SOMETHING WRONG HERE
	 */
	public int getNumEdges()
	{
		int counter = 0;

		for(int i = 0; i < graph.length; i++)
		{
			for(int j = 0; j < graph[1].length; j++)
			{
				// if there is a connection here, add an edge
				if(graph[i][j] != 0)
				{
					counter++;
				}
			}
		}

		return counter;

	}

	/*
	 * Returns a visual representation of the graph in the form of a string.
	 */
	public String toString()
	{
		String returnString = "";
		for(int i = 0; i < names.size(); i++)
		{
			if(names.get(i).length() > 8)
			{
				returnString += names.get(i).substring(0, 8) + "  |  ";
			}
			else
			{
				returnString += names.get(i) + "  |  ";
			}
		}
		for(int j = 0; j < names.size(); j++)
		{
			returnString += "\n";
			if(names.get(j).length() > 8)
			{
				returnString += names.get(j).substring(0, 8) + "  |  ";
			}
			else
			{
				returnString += names.get(j) + "  |  ";
			}

			for(int k = 0; k < names.size(); k++)
			{
				returnString += graph[j][k] + "  |  ";
			}
		}
		return returnString;
	}

	/*
	 * Insert a vertex into the array. 
	 */
	public void insert(String vertexName)
	{
		// make a temp array so we can modify graph
		double[][] temp = graph;

		// test whether or not input is bad
		if(testInputCasesVertexInsert(vertexName))
		{
			return;
		}

		names.add(vertexName);

		// make a new graph
		graph = new double[temp.length + 1][temp.length + 1];

		// loop through the graph to find new values
		for(int i = 0; i < graph.length; i++)
		{
			for(int j = 0; j < graph[1].length; j++)
			{
				if(i == temp.length || j == temp.length)
				{
					graph[i][j] = 0;
				}
				else
				{
					graph[i][j] = temp[i][j];
				}		
			}
		}
	}

	/*
	 * Insert an edge into a graph.
	 */
	public void insert(String firstVertexName, String secondVertexName, double weight)
	{
		// test for bad input
		if(testInputCasesEdgeInsert(firstVertexName, secondVertexName))
		{
			return;
		}
		if(isDirected == true)
		{
			graph[names.indexOf(firstVertexName)][names.indexOf(secondVertexName)] = weight;
		}
		else
		{
			graph[names.indexOf(firstVertexName)][names.indexOf(secondVertexName)] = weight;
			graph[names.indexOf(secondVertexName)][names.indexOf(firstVertexName)] = weight;
		}

	}

	/*
	 * Removes an edge from the graph.
	 */
	public void remove(String firstVertexName, String secondVertexName)
	{
		// test for bad input
		if(testInputCasesEdgeRemove(firstVertexName, secondVertexName))
		{
			return;
		}
		if(isDirected == true)
		{
			graph[names.indexOf(firstVertexName)][names.indexOf(secondVertexName)] = 0;
		}
		else
		{
			graph[names.indexOf(firstVertexName)][names.indexOf(secondVertexName)] = 0;
			graph[names.indexOf(secondVertexName)][names.indexOf(firstVertexName)] = 0;
		}

	}

	/*
	 * Removes a vertex from the graph.
	 */
	public void remove(String vertexName)
	{
		if(testInputCasesVertexRemove(vertexName))
		{
			return;
		}

		double[][] temp = graph;
		graph = new double[temp.length - 1][temp.length - 1];
		
		for(int i = 0; i < graph.length; i++)
		{
			for(int j = 0; j < graph[1].length; j++)
			{
				if(i >= names.indexOf(vertexName))
				{
					if(j >= names.indexOf(vertexName))
					{
						graph[i][j] = temp[i+1][j+1];
					}
					else
					{	
						graph[i][j] = temp[i+1][j];
					}
				}
				else if(j >= names.indexOf(vertexName))
				{
					graph[i][j] = temp[i][j+1];
				}
				else
				{
					graph[i][j] = temp[i][j];
				}
			}
		}
		names.remove(vertexName);
	}

	/*
	 * Test whether the two vertices are connected.
	 */
	public boolean areConnected(String firstVertexName, String secondVertexName)
	{
		if(testInputCasesAreConnected(firstVertexName, secondVertexName))
		{
			return false;
		}

		ArrayList<String> queue = new ArrayList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		queue.add(firstVertexName);
		while(queue.size() != 0)
		{
			String removeString = queue.remove(0);
			if(removeString.equals(secondVertexName))
			{
				return true;
			}
			int index = names.indexOf(removeString);
			for(int i = 0; i < names.size(); i++)
			{
				// if there is no path between the index vertex and the loop value
				if(graph[index][i] != 0)
				{
					// if this vertex hasn't been visited and isn't in the queue already
					if(!visited.contains(names.get(i)) && !queue.contains(names.get(i)))
					{
						// add it to the queue
						queue.add(names.get(i));
					}
				}
				visited.add(removeString);
			}
		}
		return false;
	}

	/*
	 * Didn't do for my new implementation.
	 */
	public ArrayList<String> getShortestPath(String firstVertexName, String secondVertexName)
	{
		return null;
	}

	/*
	 * Didn't do for my new implementation.
	 */
	public double getShortestPathLength(String firstVertexName, String secondVertexName)
	{
		return 1;
	}

	/*
	 * Test input cases for errors. You won't get me this time Cesar.
	 */
	public boolean testInputCasesVertexInsert(String vertexName)
	{
		boolean endMethod = false;

		if(vertexName == null)
		{
			System.out.println("Error: Vertex is null.");
			endMethod = true;
		}
		else if(names.contains(vertexName))
		{
			System.out.println("Error: Vertex already exists in the graph.");
			endMethod = true;
		}
	
		return endMethod;
	}
	
	/*
	 * Test input cases for errors. You won't get me this time Cesar.
	 */
	public boolean testInputCasesVertexRemove(String vertexName)
	{
		boolean endMethod = false;

		if(vertexName == null)
		{
			System.out.println("Error: The vertex is null.");
			endMethod = true;
		}
		else if(!names.contains(vertexName))
		{
			System.out.println("Error: One or both vertices are not contained in the graph.");
			endMethod = true;
		}

		return endMethod;
	}

	/*
	 * Test input cases for errors. You won't get me this time Cesar.
	 */
	public boolean testInputCasesEdgeInsert(String vertexName1, String vertexName2)
	{
		boolean endMethod = false;

		if(vertexName1 == null || vertexName2 == null)
		{
			System.out.println("Error: One or both vertices is null.");
			endMethod = true;
		}
		else if(!names.contains(vertexName1) || !names.contains(vertexName2))
		{
			System.out.println("Error: One or both vertices are not contained in the graph.");
			endMethod = true;
		}
		else if(vertexName1.equals(vertexName2))
		{
			System.out.println("Error: The vertices equal each other.");
			endMethod = true;
		}
		else if(graph[names.indexOf(vertexName1)][names.indexOf(vertexName2)] != 0)
		{
			System.out.println("Error: An edge already exists between the two vertices");
			endMethod = true;
		}

		return endMethod;
	}
	
	/*
	 * Test input cases for errors. You won't get me this time Cesar.
	 */
	public boolean testInputCasesEdgeRemove(String vertexName1, String vertexName2)
	{
		boolean endMethod = false;

		if(vertexName1 == null || vertexName2 == null)
		{
			System.out.println("Error: One or both vertices is null.");
			endMethod = true;
		}
		else if(!names.contains(vertexName1) || !names.contains(vertexName2))
		{
			System.out.println("Error: One or both vertices are not contained in the graph.");
			endMethod = true;
		}
		else if(vertexName1.equals(vertexName2))
		{
			System.out.println("Error: The vertices equal each other.");
			endMethod = true;
		}

		return endMethod;
	}
	
	/*
	 * Test input cases for errors. You won't get me this time Cesar.
	 */
	public boolean testInputCasesAreConnected(String vertexName1, String vertexName2)
	{
		boolean endMethod = false;

		if(vertexName1 == null || vertexName2 == null)
		{
			System.out.println("Error: One or both vertices is null.");
			endMethod = true;
		}
		else if(!names.contains(vertexName1) || !names.contains(vertexName2))
		{
			System.out.println("Error: One or both vertices are not contained in the graph.");
			endMethod = true;
		}
		else if(vertexName1.equals(vertexName2))
		{
			System.out.println("Error: The vertices equal each other.");
			endMethod = true;
		}

		return endMethod;
	}
}