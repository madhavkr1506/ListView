package com.example.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentObject : Fragment() {
    private var content : String? = null;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_view,container,false);

        val animation : Animation = AnimationUtils.loadAnimation(context,R.anim.zoom_animation);
        val textUpdate = view.findViewById<TextView>(R.id.text);
        textUpdate.text = content.toString();

        textUpdate.startAnimation(animation);


        val closeButton = view.findViewById<Button>(R.id.close);
        closeButton?.setOnClickListener{
            when(val parentActivity = activity){
                is MainActivity -> {
                    parentActivity.hideFragment();
                }
            }
        }

        return view;
    }

    fun updateContent(content : String){
        this.content = content;
    }
}