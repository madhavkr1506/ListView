package com.example.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context : Context, private val fruits : List<String>, private val color : List<String> )  : ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,fruits) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context);
        val rowView = convertView ?: inflater.inflate(R.layout.list_item,parent,false);

        val item = rowView.findViewById<TextView>(R.id.item);
        val description = rowView.findViewById<TextView>(R.id.description);

        item.text = fruits[position];
        description.text = color[position];

        return rowView;
    }
}