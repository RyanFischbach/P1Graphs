import java.util.ArrayList;

public class GDFRTester {
	public static void main (String [] args) 
	{
		String filename;

		if ( args != null && args.length > 0 ) 
		{
			filename = args[0];
		}
		else
		{
			filename = "calCities.txt";
		}

		ArrayList<Edge> edgeList;
		edgeList = GraphDataFileReader.readDataFile(filename);
		if (edgeList != null && edgeList.size() > 0 ) 
		{
			for ( Edge edge : edgeList ) 
			{
				System.out.println(edge);
			}
		}
		else
		{
			System.out.println("No edge list, possibly bad file name or empty file.");
		}

		Graph graph = new Graph("calCities.txt", false);
		System.out.println(graph.getNumEdges());
		System.out.println("*******************");
		System.out.println(graph.getNumVertices());

	}
}