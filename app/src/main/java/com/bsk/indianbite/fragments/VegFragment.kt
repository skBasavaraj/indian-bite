package com.bsk.indianbite.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bsk.indianbite.R
import com.bsk.indianbite.adapter.VideosListAdapter
import com.bsk.indianbite.model.VideoItem
import org.json.JSONArray
import org.json.JSONObject

class VegFragment : Fragment() {
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mRecycelerView: RecyclerView
    private var mArryList = ArrayList<VideoItem>()
    private lateinit var adapter: VideosListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar!!.title = "Veg Videos"
        actionBar.setBackgroundDrawable(resources.getDrawable(R.drawable.veg))
        val view = inflater.inflate(
            R.layout.fragment_veg, container,
            false
        )
        progressDlog()
        initView(view)
        loadData()
        return view
    }

    private fun progressDlog() {
        progressDialog = ProgressDialog(this.requireContext())
        progressDialog.setMessage("Please wait...")
        progressDialog.show()
        progressDialog.setCancelable(false)
    }

    private fun loadData() {
        val queue = Volley.newRequestQueue(this.requireContext())
        val url = "https://f28c6641-9165-43df-b115-b1549b540475.mock.pstmn.io/devbytes"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            extractJsonData(response)
            progressDialog.dismiss()
        }, {
        })
        queue.add(stringRequest)
    }

    private fun initView(view: View) {
        mRecycelerView = view.findViewById(R.id.recyclerView)
        adapter = VideosListAdapter(mArryList,this.requireContext())
        mRecycelerView.layoutManager = LinearLayoutManager(this.requireContext())
        mRecycelerView.setHasFixedSize(true)
        mRecycelerView.adapter = adapter
    }

    private fun extractJsonData(jsonResponse: String) {
        var videosDataArray = JSONArray(jsonResponse)
        var singleVideoJsonObject: JSONObject
        var singleVideoItem: VideoItem
        var i = 0
        var size = videosDataArray.length()
        while (i < size) {
            singleVideoJsonObject = videosDataArray.getJSONObject(i)
            singleVideoItem =
                VideoItem(
                    singleVideoJsonObject.getString("video_id"),
                    singleVideoJsonObject.getString("video_name"),
                    singleVideoJsonObject.getString("video_desc")
                )
            mArryList.add(singleVideoItem)
             i++
        }
        adapter.notifyDataSetChanged()
    }
}