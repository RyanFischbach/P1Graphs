public class EdgePrototype {
	private String first;
	private String second;
	private double weight;
	private Vertex firstVertex;
	private Vertex secondVertex;

	public EdgePrototype (String iFirst, String iSecond, Double iWeight)
	{
		first = iFirst;
		second = iSecond;
		weight = iWeight;
		firstVertex = new Vertex(first);
		secondVertex = new Vertex(second);
	}

	public String getFirst()
	{
		return first;
	}

	public String getSecond()
	{
		return second;
	}
	
	public Vertex getFirstVertex()
	{
		return firstVertex;
	}
	
	public Vertex getSecondVertex()
	{
		return secondVertex;
	}

	public double getWeight()
	{
		return weight;
	}

	public String toString() 
	{
		return first + " -> " + second + " (" + weight + ")";
	}
}