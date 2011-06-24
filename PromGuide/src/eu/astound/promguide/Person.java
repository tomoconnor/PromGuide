package eu.astound.promguide;

public class Person {
	public String name;
	public String dataURL;
	
	
	public Person(String _name, String _dataURL){
		name = _name;
		
		dataURL = _dataURL;
		
	}
	public Person(){
		
	}
	
	public String getName(){return this.name; }
	public void setName(String n){ this.name = n; }
	
	
	public String getDataURL(){return this.dataURL;}
	public void setDataURL(String d){this.dataURL = d;}
		
	
	
	public String toString(){
		return name;
	}
}
