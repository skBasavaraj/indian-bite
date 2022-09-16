package com.bsk.indianbite.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bsk.indianbite.MainActivity
import com.bsk.indianbite.R
import com.bsk.indianbite.VideoPlayActivity
import com.bsk.indianbite.model.VideoItem
import com.squareup.picasso.Picasso
import java.util.ArrayList

class VideosListAdapter(var videoList: ArrayList<VideoItem>, var context: Context) :
    RecyclerView.Adapter<VideosListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideosListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideosListAdapter.ViewHolder, position: Int) {
        val singleVideoItem = videoList.get(position)
        holder.videoTitle.text = singleVideoItem.videoTitle
        holder.videoDescription.text = singleVideoItem.videoDesc
             Picasso.with(context)
            .load("https://i.ytimg.com/vi/" + singleVideoItem.videoId + "/sddefault.jpg")
            .resize(640, 360)//640x360   *initially 320x180
            .centerCrop()
            .into(holder.videoThumb)

        holder.itemLinearLayout.setOnClickListener {
             val playVideoIntent = Intent(context, VideoPlayActivity::class.java).apply {
                    putExtra("VIDEO_ID", "" + singleVideoItem.videoId)
                 //   putExtra("VIDEO_TITLE", "" + singleVideoItem.videoTitle)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            context.startActivity(playVideoIntent)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoThumb: ImageView = itemView.findViewById(R.id.videoThumb)
        val videoTitle: TextView = itemView.findViewById(R.id.videoTitle)
        val videoDescription: TextView = itemView.findViewById(R.id.videoDescription)
        val itemLinearLayout: LinearLayout = itemView.findViewById(R.id.itemLinearLayout)
    }
}