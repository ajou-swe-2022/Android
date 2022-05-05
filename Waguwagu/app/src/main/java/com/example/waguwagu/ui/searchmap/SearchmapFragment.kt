package com.example.waguwagu.ui.searchmap

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waguwagu.MainActivity
import com.example.waguwagu.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchmapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchmapFragment : Fragment(), OnMapReadyCallback {

    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity) {
            mContext = context
        }
    }
    private lateinit var mView: MapView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_searchmap, container, false)
        mView = rootView.findViewById(R.id.mapView)
        mView.onCreate(savedInstanceState)
        mView.getMapAsync(this)
        return rootView
    }
    override fun onMapReady(googleMap: GoogleMap) {
        // 주소:임시로 아주대학교
        val sydney = LatLng(37.282753,127.044999)
        googleMap.addMarker(
            MarkerOptions().
            position(sydney)
                .title("TestMaker")
        )
        //지도 초기 확대.
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15.7f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

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
