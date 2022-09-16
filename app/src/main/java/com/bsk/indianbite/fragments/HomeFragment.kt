package com.bsk.indianbite.fragments

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.Volley
import com.bsk.indianbite.R

class HomeFragment : Fragment() ,View.OnClickListener{
    lateinit var mVegBtn: ImageView
    lateinit var mNonVegBtn: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar!!.title = "Indian Bite"
        actionBar.setBackgroundDrawable(resources.getDrawable(R.drawable.bar_color))
         initView(view)
        // progressDialog = ProgressDialog(this.requireContext())
        return view
    }

    private fun initView(v:View) {
        mVegBtn = v.findViewById(R.id.vegBtn)
        mNonVegBtn  = v.findViewById(R.id.nonVegBtn)
        mVegBtn.setOnClickListener(this)
        mNonVegBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.vegBtn ->findNavController().navigate(R.id.action_homeFragment_to_vegFragment3)
            R.id.nonVegBtn ->findNavController().navigate(R.id.action_homeFragment_to_nonVegFragment)
        }
    }

}