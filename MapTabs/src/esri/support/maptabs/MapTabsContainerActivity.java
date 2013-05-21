package esri.support.maptabs;

import com.esri.core.geometry.Polygon;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.util.Log;
import android.view.Menu;

public class MapTabsContainerActivity extends Activity implements MapApplicationMessenger {
	
	public Polygon lastMapTabExtent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        Tab t1 = actionBar.newTab()
                .setText("Beijing")
                .setTabListener(new MapTabsListener<Beijing>(
                        this, "beijing", Beijing.class));
        actionBar.addTab(t1);

        Tab t2 = actionBar.newTab()
                .setText("New York")
                .setTabListener(new MapTabsListener<Manhattan>(
                        this, "New York", Manhattan.class));
        actionBar.addTab(t2);
        
        Tab t3 = actionBar.newTab()
                .setText("Paris")
                .setTabListener(new MapTabsListener<Paris>(
                        this, "Paris", Paris.class));
        actionBar.addTab(t3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.container, menu);
        return true;
    }

	@Override
	public void persistExtentOnPause(Polygon lastExtent) {
		lastExtent = lastMapTabExtent;
		Log.i("Message", "lastMapTabExtent fired!"); // Show messaging to console
		
	}
}
