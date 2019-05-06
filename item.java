
/**
 * @author kahty
 *	This class represents an item with attributes:
 *	weight, value and density
 */
public class item {
	int weight;
	int value;
	double density;
	
	item(int weight, int value){
		this.weight = weight;
		this.value = value;
		density = (double)value / (double)weight;
	}
	
	int getWeight(){
		return weight;
	}
	
	int getVal(){
		return value;
	}
	
	double getDensity(){
		return density;
	}
}
