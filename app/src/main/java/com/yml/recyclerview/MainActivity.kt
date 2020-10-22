package com.yml.recyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), WordListAdapter.OnItemClickListener {

    private var mWordList = LinkedList<String>()
    private lateinit var recyclerView: RecyclerView
    private val adapter = WordListAdapter(mWordList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        for (i in 0..19) {
            mWordList.addLast("Word $i")
        }



        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val wordListSize = mWordList.size
            mWordList.addLast("+ Word $wordListSize")
            adapter.notifyItemInserted(wordListSize)
            recyclerView.smoothScrollToPosition(wordListSize)
        }
    }

    override fun onItemClick(position: Int) {
        val mPosition: Int = position
        val element = mWordList[mPosition]
        mWordList[mPosition] = "Clicked! $element"
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}