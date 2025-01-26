package com.example.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context : Context, private var fruits : MutableList<String>, private var color : MutableList<String> )  : ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,fruits) {

    private var fruitQuery : MutableList<String> = ArrayList(fruits);
    private var colorQuery : MutableList<String> = ArrayList(color);

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context);
        val rowView = convertView ?: inflater.inflate(R.layout.list_item,parent,false);

        val item = rowView.findViewById<TextView>(R.id.item);
        val description = rowView.findViewById<TextView>(R.id.description);

        item.text = fruitQuery[position];
        description.text = colorQuery[position];

        return rowView;
    }

    override fun getCount(): Int {
        return fruitQuery.size;
    }
    fun filter(query : String){
        if(query.isEmpty()){
            fruitQuery = ArrayList(fruits);
            colorQuery = ArrayList(color);
        }
        else{
            fruitQuery = ArrayList();
            colorQuery = ArrayList();

            for(i in fruits.indices){
                if(fruits[i].lowercase().contains(query.lowercase(),true)){
                    fruitQuery.add(fruits[i]);
                    colorQuery.add(color[i]);
                }
            }
        }
        notifyDataSetChanged();
    }
}