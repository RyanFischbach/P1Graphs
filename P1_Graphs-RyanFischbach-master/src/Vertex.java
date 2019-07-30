import java.util.*;

public class Vertex {
	
	// the name of the Vertex
	private String vertexName;
	
	// the list of edges that are connected to this Vertex
	private ArrayList<Edge> edgeList = new ArrayList<Edge>();
	
	private double distance;

	/*
	 * Initializes the vertexName from the String input.
	 */
	public Vertex (String inputName)
	{
		vertexName = inputName;
	}

	/*
	 * Returns the name of the Vertex.
	 */
	public String toString() 
	{
		return vertexName;
	}

	/*
	 * Returns the list of edges that are connected to this Vertex.
	 */
	public ArrayList<Edge> getEdgeList()
	{
		return edgeList;
	}
	
	/*
	 * Adds an edge to the list of connected edges.
	 */
	public void setEdge(Edge input)
	{
		edgeList.add(input);
	}
	
	/*
	 * Returns the children of the vertex.
	 */
	public ArrayList<Vertex> getChildren()
	{
		ArrayList<Vertex> childrenList = new ArrayList<Vertex>();
		for (Edge e: edgeList)
		{
			Vertex firstVertex = new Vertex (e.getFirst());
			Vertex secondVertex = new Vertex (e.getSecond());
			if (!firstVertex.toString().equals(vertexName))
			{
				childrenList.add(firstVertex);
			}
			else
			{
				childrenList.add(secondVertex);
			}
		}
		return childrenList;
	}
	
	public void setDistance(double distanceIn)
	{
		distance = distanceIn;
	}
	
	public double getDistance()
	{
		return distance;
	}
}