public class Edge {
	
	// Name of the first connected Vertex
	private String first;
	
	// Name of the second connected Vertex
	private String second;
	
	// Weight of the edge
	private double weight;
	
	// First connected vertex object
	private Vertex firstVertex;
	
	// Second connected vertex object
	private Vertex secondVertex;
	

	/*
	 * Initializes the weight, and first two vertex objects from the input.
	 */
	public Edge (String iFirst, String iSecond, Double iWeight)
	{
		first = iFirst;
		second = iSecond;
		weight = iWeight;
		firstVertex = new Vertex(first);
		secondVertex = new Vertex(second);
	}

	/*
	 * Returns the string of the first connected vertex.
	 */
	public String getFirst()
	{
		return first;
	}

	/*
	 * Returns the string of the second connected vertex.
	 */
	public String getSecond()
	{
		return second;
	}
	
	/*
	 * Returns the first connected vertex object.
	 */
	public Vertex getFirstVertex()
	{
		return firstVertex;
	}
	
	/*
	 * Returns the second connected vertex object.
	 */
	public Vertex getSecondVertex()
	{
		return secondVertex;
	}

	/*
	 * Returns the weight of the edge.
	 */
	public double getWeight()
	{
		return weight;
	}

	/*
	 * Returns edge object's characteristics as a String.
	 */
	public String toString() 
	{
		return first + " -> " + second + " (" + weight + ")";
	}
}