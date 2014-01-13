public class Combo {
	private ArrayList<Card> combo;
	public static String identifier(ArrayList<Card> input){
		if(input.size() == 0){
			return "Invalid";
		}
		else if (input.size() == 1){
			return "Single";
		}
		else if (input.size() == 2){
			if(samevalue(input)){
				return "Double";
			}
			else{
				return "Invalid";
			}
		}
		else if (input.size() == 3){
			if(samevalue(input)){
				return "Triple";
			}
			else{
				return "Invalid";
			}
		}
		else if (input.size() == 4){
			return "Invalid";
		}
		else{

		}
	} 
	private static boolean samesuit(ArrayList<Card> input){
		boolean ret = true;
		for(int x = 1;x<input.size();x++){
			if(!(input.get(x -1).equalS(input.get(x)))){
				ret = false;
			}
		}
		return ret;
	} 
	private static boolean samevalue(ArrayList<Card> input){
		boolean ret = true;
		for(int x = 1;x<input.size();x++){
			if(!(input.get(x -1).equalV(input.get(x)))){
				ret = false;
			}
		}
		return ret;
	}
	private static boolean straight(ArrayList<Card> input){
		boolean ret = false;
		for(int x = 1;x < 13;x++){
			int count = 0;
			while(count != 5){
				
				count++;
			}

		}
	}
	private static boolean full


}
