public class Candidate 
{
	//whether or not the candidate has been visited
	private boolean visited;
	
	// the name of the current candidate
	private String name;
	
	// the path weight of the candidate
	private double pathWeight;
	
	// the previous candidate
	private Vertex previous;
	
	public Candidate (Vertex previousCandidate, String nameInput, double pathWeightInput, boolean visitedInput)
	{
		visited = visitedInput;
		name = nameInput;
		pathWeight = pathWeightInput;
		previous = previousCandidate;	
	}
	
	/*
	 * Returns the name of the candidate.
	 */
	public String getName()
	{
		return name;
	}
	
	/*
	 * Returns the weight of the candidate.
	 */
	public double getWeight()
	{
		return pathWeight;
	}
	
	/*
	 * Returns whether the candidate has been visited.
	 */
	public boolean getVisited()
	{
		return visited;
	}
	
	/*
	 * Returns the previous candidate.
	 */
	public Vertex getPreviousCandidate()
	{
		return previous;
	}
	
	/*
	 * Sets whether or not the candidate has been visited.
	 */
	public void setVisited(boolean visitedIn)
	{
		visited = visitedIn;
	}
	
	/*
	 * Sets the weight of the path.
	 */
	public void setPathWeight(double pathWeightIn)
	{
		pathWeight = pathWeightIn;
	}
}
