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
import com.example.waguwagu.databinding.FragmentSearchmapBinding
import androidx.core.content.ContextCompat.getDrawable


import com.example.waguwagu.MainActivity
import com.example.waguwagu.R
import com.example.waguwagu.SearchAdapter
import com.example.waguwagu.model.data.SearchData
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


    private lateinit var rootView:View
    val datas = mutableListOf<SearchData>()
    lateinit var binding:FragmentSearchmapBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*
        datas.apply {
            add(SearchData("McDonalds", "Hamburger", 3, 12, 30))
            add(SearchData("롯데리아", "Hamburger", 3, 12, 30))
            add(SearchData("본죽", "한식", 3, 12, 30))
            add(SearchData("푸라닭", "한식", 3, 12, 30))
            add(SearchData("팔달관매점", "호텔", 3, 12, 30))
        } */
        binding=FragmentSearchmapBinding.inflate(inflater,container,false)


        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
        rootView=binding.getRoot()
        System.out.println(datas)
        return rootView;

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
        //음식점 임시 주소 tag를 통해 객체를 지정할 수 있음

        //var k=datas.size
        for(j in 0..(5-1)) {
            var temp=LatLng(37.282753,127.044999+(0.0006*(j+1)))
            googleMap.addMarker(
                MarkerOptions()
                    .position(temp)
                    .title("${0}")
                    .snippet("${1}")
                    .icon(bitmapDescriptorFromVector(mContext,R.drawable.marker_unclicked))

            )?.tag=3;
        }
        //지도 초기 확대.
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15.7f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ajou))
        googleMap.setOnMarkerClickListener (this)
        googleMap.setOnMapClickListener(this)

    }

    override fun onMarkerClick(p0: Marker) : Boolean {

        binding.cardView.visibility = View.GONE
        prev_marker?.setIcon(bitmapDescriptorFromVector(mContext,R.drawable.marker_unclicked))
        if(p0.tag==0) {
            Toast.makeText(mContext, "현재 위치입니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        else {
            //tag에 담긴 data 통해 등장할 cardview내의 정보값을 변경
                p0.setIcon(bitmapDescriptorFromVector(mContext,R.drawable.marker_clicked))

            //binding.searchRecyclerview.adapter=SearchAdapter(mutableListOf<SearchData>(p0.tag as SearchData))
            binding.cardView.visibility=View.VISIBLE
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
        binding.cardView.visibility = View.GONE
        return
    }


    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }
    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }
    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
    override fun onDestroy() {
        binding.mapView.onDestroy()
        super.onDestroy()
    }

}
