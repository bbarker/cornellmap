package net.homeunix.barker.cornellmap;
//*****************VERIFY LIFE CYCLE****************************/
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class CornellMap extends MapActivity {
	public MapView mapview;
	public MapController mapController;
	public RelativeLayout cmapapp;
	public View myzoom;
	public LinearLayout llzoom;
	public MapOverlay cmOverlay;
	public LocationManager lm;
    @Override 
    public void onCreate(Bundle icicle){  
    	super.onCreate(icicle);
        mapview = new MapView(this, "0SxXtBUYSQrf-dT4qvKHgUrePx9KTKajcBKjNyg");
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,  LayoutParams.FILL_PARENT);
        mapview.setLayoutParams(params);
        mapview.setEnabled(true);
        mapview.setClickable(true);
        cmapapp = new RelativeLayout(this);
        cmapapp.setLayoutParams(new ViewGroup.LayoutParams
       		   (ViewGroup.LayoutParams.FILL_PARENT,
       		    ViewGroup.LayoutParams.FILL_PARENT));        
       
        llzoom = new LinearLayout(this);
        llzoom.setLayoutParams(new ViewGroup.LayoutParams
       		   (ViewGroup.LayoutParams.FILL_PARENT,
       		    ViewGroup.LayoutParams.FILL_PARENT));
        
        
        Drawable droidIcon = getResources().getDrawable(R.drawable.android_small_red); 
        Drawable mapImage = getResources().getDrawable(R.drawable.large); 
        
        
        mapController = mapview.getController();
        mapController.setZoom(19);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE); 
        Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeoPoint centerCM = new GeoPoint(
                (int) (loc.getLatitude() * 1E6), 
                (int) (loc.getLongitude() * 1E6));
        List<Overlay> l = mapview.getOverlays();
        cmOverlay = new MapOverlay(centerCM, droidIcon, mapImage, mapview);
         
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, cmOverlay);

        
        l.add(cmOverlay);
        mapController.setCenter(centerCM);
        mapview.postInvalidate(); 

        myzoom =  mapview.getZoomControls();
        llzoom.addView(myzoom);
        llzoom.setGravity(Gravity.BOTTOM + Gravity.CENTER_HORIZONTAL);

        mapview.setLayoutParams(new ViewGroup.LayoutParams
      		   (ViewGroup.LayoutParams.FILL_PARENT,
      		    ViewGroup.LayoutParams.FILL_PARENT));

        mapview.displayZoomControls(true);
        cmapapp.addView(mapview);
        cmapapp.addView(llzoom);
        setContentView(cmapapp);
         
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	} 
} 

