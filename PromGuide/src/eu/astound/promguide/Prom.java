package eu.astound.promguide;

public class Prom {
	public String name;
	public String month;
	public String day;
	public String year;
	public String imageURL;
	public String dataURL;
	public String description;
	
	public Prom(String _name, String _month, String _day, String _year, String _imageURL, String _dataURL, String _description){
		name = _name;
		month = _month;
		day =_day;
		year = _year;
		imageURL = _imageURL;
		dataURL = _dataURL;
		description = _description;
	}
	public Prom(){
		
	}
	
	public String getName(){return this.name; }
	public void setName(String n){ this.name = n; }
	
	public String getMonth(){ return this.month; }
	public void setMonth(String m){ this.month = m; }
	
	public String getDay(){return this.day; }
	public void setDay(String d){this.day = d; }
	
	public String getYear(){return this.year;}
	public void setYear(String y){this.year = y;}
	
	public String getImageURL(){return this.imageURL;}
	public void setImageURL(String i){this.imageURL = i;}
	
	public String getDataURL(){return this.dataURL;}
	public void setDataURL(String d){this.dataURL = d;}
	
	public String getDescription(){return this.description;}
	public void setDescription(String d){this.description = d;}
	
	
	
	public String toString(){
		return name;
	}
}
