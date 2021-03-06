package eu.astound.promguide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
public class PromGuideActivity extends ListActivity{
	//public ArrayList promList;
    public PromList promList;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.main);
	  InputStream is = null;
	try {
		is = getAssets().open("proms.json");
		  InputStreamReader isr = new InputStreamReader(is);
	      this.promList = this.fetchPromGuideAsProm(isr);

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      final Button btnPerformers = (Button) findViewById(R.id.btnPerformers);
      final Button btnComposers = (Button) findViewById(R.id.btnComposers);

	  setListAdapter(new ArrayAdapter(this,R.layout.list_item,this.promList));
	  btnPerformers.setText("View Performers");
	  btnComposers.setText("View Composers");
	  btnPerformers.setOnClickListener(new View.OnClickListener(){
		  public void onClick(View v){
			  Intent i = new Intent(v.getContext(),PromPersonGuideActivity.class);
			  i.putExtra("eu.astound.promguide.PromPersonGuideActivity.PERSON_GUIDE_ID", "Performer");
			  startActivityForResult(i,0);
		  }
	  });
	  btnComposers.setOnClickListener(new View.OnClickListener(){
		  public void onClick(View v){
			  Intent i = new Intent(v.getContext(),PromPersonGuideActivity.class);
			  i.putExtra("eu.astound.promguide.PromPersonGuideActivity.PERSON_GUIDE_ID", "Composer");
			  startActivityForResult(i,0);
		  }
	  });
	  
	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);
	  
	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	    	promClick(view, position, id);
	        }
	  });
     
      
	}
	public void promClick(View view, int position, long id){
		Intent i = new Intent(view.getContext(),PromGuideDetailActivity.class);
		Bundle b = new Bundle();
		b.putParcelable("promslist", this.promList); //Insert list in a Bundle object
 
		i.putExtra("eu.astound.promguide.PromGuideDetailActivity.PROM_ID", position);
        i.putExtra("eu.astound.promguide.PromGuideDetailActivity.PROM_LIST", b);
		i.putExtras(b); //Insert the Bundle object in the Intent' Extras

		startActivityForResult(i,0);
	}
	
	
	
	public PromList fetchPromGuideAsProm(InputStreamReader isr){
		PromList listItems = new PromList();
		try {
				//URL guideURL = new URL("http://www.twinhelix.org/media/proms.json");
				//URLConnection pg = guideURL.openConnection();
				BufferedReader in = new BufferedReader(isr);//new InputStreamReader(pg.getInputStream()));
				
					
				String jsonblock = "";
				
				String line;
				while ((line = in.readLine())!= null){
					jsonblock += line;
				}
				JSONTokener jtok = new JSONTokener(jsonblock);
				JSONObject jo = new JSONObject(jtok);
				JSONArray july = (JSONArray) jo.get("july");
				JSONArray august = (JSONArray) jo.get("august");
				JSONArray september = (JSONArray) jo.get("september");
				for (int i=0; i < july.length(); i++){
					JSONObject xprom = (JSONObject) july.get(i);
					Prom p = new Prom(xprom.getString("name"),xprom.getString("month"),xprom.getString("day"),xprom.getString("year"),xprom.getString("img"),xprom.getString("url"),xprom.getString("description"));
					listItems.add(p);
				}
				for (int i=0; i < august.length(); i++){
					JSONObject xprom = (JSONObject) august.get(i);
					Prom p = new Prom(xprom.getString("name"),xprom.getString("month"),xprom.getString("day"),xprom.getString("year"),xprom.getString("img"),xprom.getString("url"),xprom.getString("description"));
					listItems.add(p);
				}
				for (int i=0; i < september.length(); i++){
					JSONObject xprom = (JSONObject) september.get(i);
					Prom p = new Prom(xprom.getString("name"),xprom.getString("month"),xprom.getString("day"),xprom.getString("year"),xprom.getString("img"),xprom.getString("url"),xprom.getString("description"));
					listItems.add(p);
				}
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return listItems;
	}
}
