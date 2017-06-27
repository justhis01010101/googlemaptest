package kjminn.kr.hs.emirim.googlemaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMap;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) { //map이 다 준비되었을 때
        this.googleMap= googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);//위성지도로 바꾸어주기
        //다른지역으로 바꾸어주기
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.512149,126.833120), 17)); //17==확대 축소 레밸(숫자 클수록 큼)
        googleMap.getUiSettings().setZoomControlsEnabled(true); //확대축소 버튼
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                GroundOverlayOptions loc_mark;
                loc_mark= new GroundOverlayOptions();
                loc_mark.image(BitmapDescriptorFactory.fromResource(R.drawable.loc_icon)).position(latLng,100f,100f);
                googleMap.addGroundOverlay(loc_mark);
            }
        });
    }
    public static final  int ITEM_SATELLITE=1;
    public static final  int ITEM_NOMAL=2;
    public static final  int ITEM_PODO=3;
    public static final  int ITEM_LOTTE=4;
    public static final  int ITEM_MARK_CLEAR=5;


    @Override
    public int hashCode() {
        int result = googleMap != null ? googleMap.hashCode() : 0;
        result = 31 * result + (mapFragment != null ? mapFragment.hashCode() : 0);
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
      super.onCreateOptionsMenu(menu);
      menu.add(0,ITEM_SATELLITE,0,"위성지도");
        menu.add(0,ITEM_NOMAL,0,"일반지도");
        SubMenu hotMenu= menu.addSubMenu("핫 플레이스");
        hotMenu.add(0,ITEM_PODO,0,"포도몰");
        hotMenu.add(0,ITEM_LOTTE,0,"롯데월드");
        menu.add(0,ITEM_MARK_CLEAR,0,"아이콘 삭제");



//        menu.add(0,ITEM_PODO,0,"포도몰");
      return true;

  }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case ITEM_SATELLITE:
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case ITEM_NOMAL:
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case ITEM_PODO:
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.483934, 126.930064),17));
                return true;
            case ITEM_LOTTE:
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.511303, 127.098199),17));
                return true;
            case ITEM_MARK_CLEAR:
                googleMap.clear();
                return true;

        }
        return false; //항목이 선택되었을 때만 id 별로

    }
}
