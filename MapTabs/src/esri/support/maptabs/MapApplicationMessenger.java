package esri.support.maptabs;

import com.esri.core.geometry.Polygon;

public interface MapApplicationMessenger {
	public void persistExtentOnPause(Polygon lastExtent);
}
