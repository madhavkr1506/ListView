package com.example.paging

import android.media.MediaCodec.QueueRequest
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.paging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater);
    }

    private lateinit var fruits : MutableList<String>;
    private lateinit var color : MutableList<String>;
    private lateinit var adapter : CustomAdapter;

    private val fragment : FragmentObject = FragmentObject();
    private val fm : FragmentManager = supportFragmentManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root);

        binding.container.visibility = View.INVISIBLE;
        binding.shimmer.startShimmer();


        fruits = mutableListOf("Apple", "Orange", "Pineapple", "Strawberry", "Watermelon", "Blueberry", "Kiwi", "Cherry", "Peach", "Plum",
            "Pear", "Fig", "Pomegranate", "Coconut", "Lime", "Lychee", "Dragonfruit", "Apricot", "Cranberry", "Blackberry",
            "Raspberry", "Durian", "Jackfruit", "Avocado", "Starfruit", "Mulberry", "Persimmon", "Gooseberry", "Tangerine", "Passionfruit",
            "Nectarine", "Quince", "Date", "Cantaloupe", "Honeydew", "Guarana", "Jujube", "Sapodilla", "Soursop", "Ugli fruit",
            "Elderberry", "Kumquat", "Mandarin", "Boysenberry", "Loganberry", "Yumberry", "Tamarind", "Bilberry", "Breadfruit", "Salak");
        color = mutableListOf("Red", "Orange", "Yellow", "Red", "Green", "Blue", "Green", "Red", "Orange", "Purple",
            "Green", "Brown", "Red", "White", "Green", "Pink", "Pink", "Orange", "Red", "Black",
            "Red", "Yellow", "Green", "Green", "Yellow", "Red", "Orange", "Green", "Orange", "Yellow",
            "Red", "Yellow", "Brown", "Orange", "Green", "Brown", "Red", "Brown", "Green", "Orange",
            "Purple", "Orange", "Orange", "Purple", "Red", "Red", "Brown", "Blue", "Brown", "Brown");



        adapter = CustomAdapter(this,fruits, color);
        binding.listview.adapter = adapter;


        binding.shimmer.postDelayed({
            binding.shimmer.stopShimmer();
            binding.container.visibility = View.VISIBLE;
            binding.shimmer.visibility = View.INVISIBLE;
        }, 2000);


        binding.listview.setOnItemClickListener{adapterView, view, position, id ->
            val selectedContent = "${fruits[position]}\n${color[position]}";
            showFragment(selectedContent);
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{
                    adapter.filter(it);
                };
                return true;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let{
                    adapter.filter(it);
                };
                return true;
            }
        })

    }

    private fun showFragment(content : String){
        fragment.updateContent(content);
        val ft : FragmentTransaction = fm.beginTransaction();
        if(fragment.isAdded){
            ft.replace(R.id.container,fragment);
        }else{
            ft.add(R.id.container,fragment);
        }
        binding.listview.isEnabled = false;
        ft.commit();
    }

    fun hideFragment(){
        val ft : FragmentTransaction = fm.beginTransaction();
        if(fragment.isAdded){
            ft.remove(fragment);
            ft.commitNow();
        }
        binding.listview.isEnabled = true;
    }

}