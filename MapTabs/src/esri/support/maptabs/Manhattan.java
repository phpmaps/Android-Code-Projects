package esri.support.maptabs;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Polygon;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Manhattan extends Fragment {

	
	public MapApplicationMessenger mCallback;
	public MapView mMapView;
	public Polygon lastExtent;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallback = (MapApplicationMessenger) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement MapApplicationMessenger");
        }
	}
	
	@Override
	public void onPause() {
		super.onPause();
		lastExtent = mMapView.getExtent();
		mCallback.persistExtentOnPause(lastExtent);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Context cntx = getActivity();
		mMapView = new MapView(cntx);
		mMapView.addLayer(new ArcGISTiledMapServiceLayer("http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));
		mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onStatusChanged(Object source, STATUS status) {
				if (lastExtent != null) {
					mMapView.setExtent(lastExtent);
				}else{
					Envelope env = new Envelope(-8242094.01,4967462.52,-8233694.62,4972794.58);
					mMapView.setExtent(env);
				}
			}
		});
		mMapView.setLayoutParams(getMapLayout());
		return mMapView;
	}
	
	private LayoutParams getMapLayout() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		params.bottomMargin = 1;
		params.leftMargin = 1;
		params.rightMargin = 1;
		params.topMargin = 1;
		return params;
	}

}
