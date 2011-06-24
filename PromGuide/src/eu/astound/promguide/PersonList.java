package eu.astound.promguide;
import java.util.ArrayList;
import eu.astound.promguide.Person;


import android.os.Parcel;
 
import android.os.Parcelable;
 
public class PersonList extends ArrayList<Person> implements Parcelable {
    private static final long serialVersionUID = 663585476779666096L;

    public PersonList(){
    	
    }
    
    public PersonList(Parcel in){
    	readFromParcel(in);
    }

    @SuppressWarnings({ "rawtypes" })
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public PersonList createFromParcel(Parcel in) {
                    return new PersonList(in);
            }

            public Object[] newArray(int arg0) {
                    return null;
            }
    };

    private void readFromParcel(Parcel in) {
            this.clear();
            //First we have to read the list size
            int size = in.readInt();
          
          //Name;DataURL
            for (int i = 0; i < size; i++) {
            		Person p = new Person();
                    p.setName(in.readString());
                    p.setDataURL(in.readString());
                    this.add(p);
            }
    }

    public int describeContents() {
            return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
            int size = this.size();
            //We have to write the list size, we need him recreating the list
            dest.writeInt(size);
            //Name;DataURL;
            for (int i = 0; i < size; i++) {
                    Person c = this.get(i);
                    dest.writeString(c.getName());
                    dest.writeString(c.getDataURL());
            }

    }
}
