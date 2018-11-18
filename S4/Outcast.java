package s4;

public class Outcast {

	private WordNet WN;
	
	//constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		WN = wordnet;
	}
	
	//gives an array of wordnet nouns, returns outcast
	public String outcast(String[] nouns) {
		int shortest = Integer.MAX_VALUE;
		String output = "";
		for(String noun: nouns) {
			int distance = 0;
			for(String wn_noun: WN.nouns()) {
				distance += WN.distance(noun, wn_noun);
			}
			if(distance <= shortest) {
				shortest = distance;
				output = noun;
			}
		}
		return output;
	}
}
