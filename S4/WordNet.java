package s4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {

	//private member variables
	private SAP sap;
	private RedBlackBST<String, Integer> nountree;
	private String[] nounarr;
	
	//Constructor takes the names of the two input files
	public WordNet(String synsets, String hypernyms) {
		In synsetfile = new In(synsets);
		In hypernymfile = new In(hypernyms);
		
		fillWords(synsetfile);
		fillSAP(hypernymfile);
	}
	
	private void fillWords(In synsetfile) {
		String line = "";
		nountree = new RedBlackBST<String, Integer>();
		Bag<String[]> stringbag = new Bag<String[]>();
		while(synsetfile.hasNextLine()) {
			line = synsetfile.readLine();
			String[] splitstring = line.split(",");
			String[] splitnouns = splitstring[1].split(" ");
			stringbag.add(splitstring);
			for(String str: splitnouns) {
				nountree.put(str, Integer.parseInt(splitstring[0]));
			}
		}
		nounarr = new String[stringbag.size()];
		for(String[] str: stringbag) {
			int id = Integer.parseInt(str[0]);
			nounarr[id] = str[1];
		}
	}
	
	private void fillSAP(In hypernymfile) {
		Bag<Queue<String>> edges = new Bag<Queue<String>>();
		String line = "";
		while(hypernymfile.hasNextLine()) {
			line = hypernymfile.readLine();
			String[] splitstring = line.split(",");
			Queue<String> newEdges = new Queue<String>();
			for(String s: splitstring) {
				newEdges.enqueue(s);
			}
			edges.add(newEdges);
		}
		
		Digraph d = new Digraph(nounarr.length);
		for(Queue<String> q: edges) {
			int connectedFrom = Integer.parseInt(q.dequeue());
			for(String s: q) {
				d.addEdge(connectedFrom, Integer.parseInt(s));
			}
		}
		sap = new SAP(d);
	}
	
	//Returns all WordNet nouns
	public Iterable<String> nouns(){
		return nountree.keys();
	}
	
	//Is the word a WordNet noun?
	public boolean isNoun(String word) {
		return (nountree.get(word) != null);
	}
	
	//Distance between Noun A and Noun B (defined below)
	public int distance(String nounA, String nounB) {
		int indexA = nountree.get(nounA);
		int indexB = nountree.get(nounB);
		
		if(nountree.get(nounA) == null || nountree.get(nounB) == null) {
			throw new IllegalArgumentException();
		}
		
		return sap.length(indexA, indexB);
	}
	
	//A synset (second field of synsets.txt) that is the shortest common ancestor
	//of Noun A and Noun B
	public String sap(String nounA, String nounB) {
		int indexA = nountree.get(nounA);
		int indexB = nountree.get(nounB);
		
		if(nountree.get(nounA) == null || nountree.get(nounB) == null) {
			throw new IllegalArgumentException();
		}
		
		int ancestorid = sap.ancestor(indexA, indexB);
		
		return nounarr[ancestorid];
	}
	
	//Do unit testing of this class
	public static void main(String[] args) {
		String firstfile = StdIn.readLine();
		String secondfile = StdIn.readLine();
		int N = StdIn.readInt();
		String[] inputs = new String[N];
		for(int i = 0; i < N; i++) {
			inputs[i] = StdIn.readLine();
		}
		
		StdOut.println("synsetfile=" + firstfile);
		StdOut.println("hypernymfile=" + secondfile);
		
		StdOut.println("1. Testing WordNet.nouns...");
		WordNet WN = new WordNet(args[0], args[1]);
		
		Iterable<String> allNouns = WN.nouns();
		StdOut.println("Passed!");
		
		StdOut.println("2. Testing WordNet.isNoun...");
		for(String i: inputs) {
			StdOut.println("isNoun(" + i + ")=" + WN.isNoun(i)); 
		}
		
	}
}
