
import java.util.ArrayList;

public class P1DGTesterD 
{
	public static void main (String [] args)
	{
		ArrayList<String> path;
		double distance;
		boolean connected;

		System.out.println("\n=== PROJECT 1 : _DIRECTED_ GRAPH TESTER for BLOCK D ===");
		System.out.println("Loading mediumDG...");
		Graph graph = new Graph("mediumDG.txt", true);

		// test getNumVertices and getNumEdges
		System.out.println("Testing getNumVertices and getNumEdges...");
		int numVertices = graph.getNumVertices();
		int numEdges = graph.getNumEdges();
		//		System.out.println("Vertices = "+numVertices+" Edges = "+numEdges);
		final int NVERT = 50;
		final int NEDGE = 147;
		if ( (numVertices != NVERT && numVertices != (NVERT+2)) || numEdges != NEDGE)
		{
			System.out.println("   FAILED: Wrong number of edges and/or vertices.");
			System.out.println("      Expected " + NVERT + " vertices, got: " + numVertices);
			System.out.println("      Expected " + NEDGE + " edges, got: "+numEdges);
		}

		// testing forward path
		System.out.println("Testing areConnected 19->21...");
		if (!graph.areConnected("19","21"))
			System.out.println("    FAILED: found no connection between 19 and 21, yet they are connected.");
		System.out.println("Testing shortest path 19->21...");
		path = graph.getShortestPath("19","21");
		if ( path != null )
		{
			if (path.size() != 5)
			{
				System.out.println("   FAILED! path steps = " + path.size() + ", not 5.");
			}
			// 19 15 8 48 21
			if (path.size()>=4 && (!path.get(1).equals("15") || !path.get(2).equals("8") || !path.get(3).equals("48")))
			{
				System.out.println("   FAILED! wrong path.");
				System.out.println("      Expected: 19->15->8->48->21");
				System.out.print("      Got: ");

				for (String s : path)
				{
					System.out.print(s + " ");
				}
				System.out.println();
			}
			distance = graph.getShortestPathLength("19","21");
			if (distance != 4.0)
			{
				System.out.println("   FAILED! path length = " + distance + ", not 4.");
			}	
		} else {
			System.out.println("   FAILED! No path found.");
		}
		// testing reverse path	
		System.out.println("Testing areConnected 21->19...");
		if (graph.areConnected("21","19"))
			System.out.println("    FAILED: found a connection between 21 and 19, yet they are NOT connected.");
		System.out.println("Testing shortest path 21->19...");
		path = graph.getShortestPath("21","19");
		if ( path != null && path.size() > 0)
		{
			System.out.println("   FAILED! Path found between 21 and 19, but none exist.");
		}

		// testing alternate path that uses none of the previous vertices
		System.out.println("Testing shortest paths from 9->19...");
		path = graph.getShortestPath("9","19");
		if ( path != null )
		{
			if( path.size() != 10 )
			{
				System.out.println("   FAILED: Wrong path.");
				System.out.println("      Expected 10 steps, got: " + path.size());
				System.out.println("      Expected: 9 -> 30 -> 23 -> 22 -> 6 -> 28 -> 29 -> 49 -> 34 -> 19");
				System.out.print("           Got: ");
				for (String s : path)
				{
					System.out.print(s + " -> ");
				}
				System.out.println();
			}
		} else {
			System.out.println("   FAILED: Found no path.");
		}	
		System.out.println("Testing shortest paths from 19->9...");
		path = graph.getShortestPath("19","9");
		if ( path != null )
		{
			if( path.size() != 6 )
			{
				System.out.println("   FAILED: Wrong path.");
				System.out.println("      Expected 6 steps, got: " + path.size());
				System.out.println("      Expected: 19 -> 15 -> 8 -> 48 -> 12 -> 9");
				System.out.print("      Got: ");

				for (String s : path)
				{
					System.out.print(s + " -> ");
				}
				System.out.println();
			}
		} else {
			System.out.println("   FAILED: Found no path.");
		}	


		// test removing a vertex
		System.out.println("Testing removal of the vertex named 28...");
		graph.remove("28");		
		int numVertices1 = graph.getNumVertices();
		numEdges = graph.getNumEdges();
		//		System.out.println("Vertices = "+nVr+" Edges = "+nEr);
		if (numVertices1 != (numVertices-1) || (numEdges != (NEDGE-8) && numEdges != (NEDGE-11)))
		{
			System.out.println("   FAILED: Removal of a vertex modified wrong number of items.");
			System.out.println("      Expected "+(numVertices-1)+" vertices, got: " + numVertices1);
			System.out.println("      Expected "+(NEDGE-8)+" edges, got: "+numEdges);
		}
		connected = graph.areConnected("9","19");
		if (connected)
		{
			System.out.println("   FAILED: 9 and 19 are still connected, despite removing vertex 28.");
		}		
		path = graph.getShortestPath("9","19");
		if ( path != null ) 
		{
			if ( path.size() > 0 )
			{
				System.out.println("   FAILED: Found path between 9 and 19, when a critical vertex has been removed.");
				System.out.println("      Expected 0 steps, got: " + path.size());
				System.out.print("      Got: ");

				for (String s : path)
				{
					System.out.print(s + " -> ");
				}
				System.out.println();
			}
		} 

		// test adding an edge
		System.out.println("Testing addition an edge between 22 and 29...");
		graph.insert("22","29",1.0);
		int numVertices2 = graph.getNumVertices();
		int numEdges2 = graph.getNumEdges();
		//		System.out.println("Vertices = "+nVrr+" Edges = "+nErr);
		if (numVertices1 != numVertices2 || (numEdges+1) != numEdges2 )
		{
			System.out.println("   FAILED: Addition of an edge modified wrong number of items.");
			System.out.println("      Expected "+numVertices1+" vertices, got: " + numVertices2);
			System.out.println("      Expected "+(numEdges+1)+" edges, got: "+numEdges2);
		}
		connected = graph.areConnected("9","19");
		if (!connected)
		{
			System.out.println("   FAILED: 9 and 19 are NOT connected, despite reconnecting them with new edge.");
		}				
		path = graph.getShortestPath("9","19");
		if ( path != null ) 
		{
			if ( path.size() != 8 )
			{
				System.out.println("   FAILED: New path is wrong number size.");
				System.out.println("      Expected 8 steps, got: " + path.size());
				System.out.println("      Expected: 9 -> 30 -> 23 -> 22 -> 29 -> 49 -> 34 -> 19");
				System.out.print("      Got: ");

				for (String s : path)
				{
					System.out.print(s + " -> ");
				}
				System.out.println();
			}
		} else {
			System.out.println("   FAILED: Found no path.");
		}	

		// adding a backwards edge to the graph
		System.out.println("Testing addition of an edge from 49 to 23...");
		graph.insert("49","23",1.0);
		int numVertices3 = graph.getNumVertices();
		int numEdges3 = graph.getNumEdges();
		//		System.out.println("Vertices = "+nVrr+" Edges = "+nErr);
		if (numVertices3 != numVertices2 || (numEdges2+1) != numEdges3 )
		{
			System.out.println("   FAILED: Addition of an edge modified wrong number of items.");
			System.out.println("      Expected "+numVertices2+" vertices, got: " + numVertices3);
			System.out.println("      Expected "+(numEdges2+1)+" edges, got: "+numEdges3);
		}
		path = graph.getShortestPath("9","19");
		if ( path != null ) 
		{
			if ( path.size() != 8 )
			{
				System.out.println("   FAILED: New edge should not have changed path, but it did.");
				System.out.println("      Expected 8 steps, got: " + path.size());
			}
			if ( path.size()>=7 && (!path.get(1).equals("30") || !path.get(2).equals("23") || !path.get(4).equals("29") || !path.get(6).equals("34")))
			{
				System.out.println("    FAILED: Wrong path.");
				System.out.println("      Expected: 9 -> 30 -> 23 -> 22 -> 29 -> 49 -> 34 -> 19");
				System.out.print("      Got: ");

				for (String s : path)
				{
					System.out.print(s + " -> ");
				}
				System.out.println();
			}
		} else {
			System.out.println("   FAILED: Found no path.");
		}	

		System.out.println("=======================================================");
	}

}