package net.homeunix.barker.cornellmap;

import android.graphics.Canvas; 
import android.graphics.Point;
import android.graphics.Rect;
//import android.graphics.Paint; 
//import android.graphics.RectF; 
import android.graphics.drawable.BitmapDrawable; 
import android.graphics.drawable.Drawable; 
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.MotionEvent; 

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay; 
import com.google.android.maps.GeoPoint; 
public class MapOverlay extends Overlay implements LocationListener { 
      BitmapDrawable bmp, dconBmp; 
      static int w, h; 
      GeoPoint p, tl, tr, bl, br;
      Point tlCoords, brCoords, locCoords;
      public int cmAlpha;
      MapView calc;
      Canvas canvas;
      public MapOverlay(GeoPoint droidLoc, Drawable droidIcon, Drawable cornellMap, MapView mv) { 
              bmp = (BitmapDrawable) cornellMap; 
              
              dconBmp = (BitmapDrawable) droidIcon;
              //this.p = mv.getProjection().fromPixels(bmp.getIntrinsicWidth()/2, bmp.getIntrinsicHeight()/2);
              this.p = droidLoc;
              w = bmp.getIntrinsicWidth(); 
              h = bmp.getIntrinsicHeight(); 
              
              tl = new GeoPoint(42458400, -76492503);
              tr = new GeoPoint(42458400, -76460750);
              bl = new GeoPoint(42439646, -76492503);
              br = new GeoPoint(42439646, -76460750);
      } 
      public void draw(Canvas canvas, MapView calculator, boolean 
shadow) { 
    	  	this.canvas = canvas;
    	  	this.calc = calculator;
    	  	tlCoords = new Point();
    	  	brCoords = new Point();
    	  	locCoords = new Point();
    	  	calculator.getProjection().toPixels(tl, tlCoords);
    	  	calculator.getProjection().toPixels(br, brCoords);
    	  	calculator.getProjection().toPixels(p, locCoords);
    	  	bmp.setBounds(tlCoords.x, tlCoords.y, brCoords.x, brCoords.y);
    	  	dconBmp.setBounds(locCoords.x-9, locCoords.y-10, locCoords.x+9, locCoords.y+10);
    	  	dconBmp.setAlpha(187);
    	  	
    	  	/*
            Point sXYCoords = new Point();
			calculator.getProjection().toPixels(p, sXYCoords);
            bmp.setBounds(sXYCoords.x - w / 2, sXYCoords.y - h, 
                              sXYCoords.x + w / 2, sXYCoords.y); */
    	  	cmAlpha=255;
            bmp.setAlpha(cmAlpha); 
            bmp.draw(this.canvas); 
            dconBmp.draw(this.canvas);
              /* 
              RectF oval = new RectF(xyCoords[0], xyCoords[1], 
                              xyCoords[0] + 5, xyCoords[1] + 5); 
              Paint paint = new Paint(); 
              paint.setARGB(200, 255, 0, 0); 
              canvas.drawOval(oval, paint); 
              */ 
      } 
      public GeoPoint getCenter() { 
              return p; 
      } 
      public boolean dispatchMotionEvent(MotionEvent ev) { 
              return false; 
      }
	public void onLocationChanged(Location location) {
		p = new GeoPoint(
                (int) (location.getLatitude() * 1E6), 
                (int) (location.getLongitude() * 1E6));
		calc.getProjection().toPixels(p, locCoords);
		dconBmp.setBounds(locCoords.x-9, locCoords.y-10, locCoords.x+9, locCoords.y+10);
		dconBmp.draw(canvas);
	}
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	} 
} 
