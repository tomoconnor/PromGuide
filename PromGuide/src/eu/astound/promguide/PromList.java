package eu.astound.promguide;
import java.util.ArrayList;
import eu.astound.promguide.Prom;


import android.os.Parcel;
 
import android.os.Parcelable;
 
public class PromList extends ArrayList<Prom> implements Parcelable {
    private static final long serialVersionUID = 663585476779444096L;

    public PromList(){
    	
    }
    
    public PromList(Parcel in){
    	readFromParcel(in);
    }

    @SuppressWarnings({ "rawtypes" })
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public PromList createFromParcel(Parcel in) {
                    return new PromList(in);
            }

            public Object[] newArray(int arg0) {
                    return null;
            }
    };

    private void readFromParcel(Parcel in) {
            this.clear();
            //First we have to read the list size
            int size = in.readInt();
          
          //Name;Year;Month;Day;ImageUrl;DataURL;description
            for (int i = 0; i < size; i++) {
                    Prom p = new Prom();
                    p.setName(in.readString());
                    p.setYear(in.readString());
                    p.setMonth(in.readString());
                    p.setDay(in.readString());
                    p.setImageURL(in.readString());
                    p.setDataURL(in.readString());
                    p.setDescription(in.readString());
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
            //Name;Year;Month;Day;ImageUrl;DataURL;Description
            for (int i = 0; i < size; i++) {
                    Prom c = this.get(i);
                    dest.writeString(c.getName());
                    dest.writeString(c.getYear());
                    dest.writeString(c.getMonth());
                    dest.writeString(c.getDay());
                    dest.writeString(c.getImageURL());
                    dest.writeString(c.getDataURL());
                    dest.writeString(c.getDescription());
            }

    }
}
