package com.example.waguwagu.ui.searchmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable


import com.example.waguwagu.MainActivity
import com.example.waguwagu.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [SearchmapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchmapFragment : Fragment(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener,GoogleMap.OnMapClickListener {
    private val ajou=LatLng(37.282753,127.044999)
    private var listres=ArrayList<Marker>()
    private var markercurrent: Marker? = null
    private var prev_marker:Marker?=null
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)


        if (context is MainActivity) {
            mContext = context
        }


    }
    private lateinit var mView: MapView
    private lateinit var cardview: CardView
    private lateinit var rootView:View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_searchmap, container, false)
        cardview=rootView.findViewById(R.id.card_view);
        mView = rootView.findViewById(R.id.mapView)
        mView.onCreate(savedInstanceState)
        mView.getMapAsync(this)
        return rootView
    }
    override fun onMapReady(googleMap: GoogleMap) {
        // 초기 지도가 생성될 때 작동되는 함수.
        // 주소:임시로 아주대학교

        //자기 위치 tag=0으로 표시
        googleMap.addMarker(
            MarkerOptions()
                .position(ajou)
                .title("내 위치")
                .snippet("예시용 내위치")

        )?.tag=0
        //음식점 임시 주소 tag를 통해 String 객체를 지정할 수 있음


        for(i in 1..10) {
            var temp=LatLng(37.282753,127.044999+(i*0.0005))
            googleMap.addMarker(
                MarkerOptions()
                    .position(temp)
                    .title("check${i}")
                    .snippet("test restraint $i")
                    .icon(bitmapDescriptorFromVector(mContext,R.drawable.marker_unclicked))

            )?.tag=i;
        }
        //지도 초기 확대.
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15.7f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ajou))
        googleMap.setOnMarkerClickListener (this);
    }

    override fun onMarkerClick(p0: Marker) : Boolean {

        cardview.visibility = View.GONE
        prev_marker?.setIcon(bitmapDescriptorFromVector(mContext,R.drawable.marker_unclicked))
        if(p0.tag==0) {
            Toast.makeText(mContext, "현재 위치입니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        else {
            //tag에 담긴 data 통해 등장할 cardview내의 정보값을 변경
                p0.setIcon(bitmapDescriptorFromVector(mContext,R.drawable.marker_clicked))
            var resimg=rootView.findViewById<ImageView>(R.id.rest_img)
            var restag=rootView.findViewById<TextView>(R.id.rest_tag)
            var resadmit=rootView.findViewById<TextView>(R.id.rest_admit)
            var username=rootView.findViewById<TextView>(R.id.rest_name)
            var resseat_num=rootView.findViewById<TextView>(R.id.rest_seat_num)
            var res_reserve_time=rootView.findViewById<TextView>(R.id.reserve_time)
            restag.setText("테스트 음식점 ${p0.tag}")
            resadmit.setText("${p0.tag}")
            resseat_num.setText("12")
            username.setText("테스트 사람 ${p0.tag}")
            res_reserve_time.setText("40");
            cardview.visibility=View.VISIBLE
            prev_marker=p0
        };

        return false;

    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    override fun onMapClick(latLng: LatLng) {
        prev_marker?.setIcon(bitmapDescriptorFromVector(mContext,R.drawable.marker_unclicked))
        prev_marker=null;
        cardview.visibility = View.GONE
    }


    override fun onStart() {
        super.onStart()
        mView.onStart()
    }
    override fun onStop() {
        super.onStop()
        mView.onStop()
    }
    override fun onResume() {
        super.onResume()
        mView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mView.onPause()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mView.onLowMemory()
    }
    override fun onDestroy() {
        mView.onDestroy()
        super.onDestroy()
    }

}
