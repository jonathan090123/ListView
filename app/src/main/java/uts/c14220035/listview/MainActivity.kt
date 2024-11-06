package uts.c14220035.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var data = mutableListOf<String>()
    var filteredData = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        data.addAll(listOf("1", "2", "3", "4", "5"))
        filteredData.addAll(data)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lvAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredData)
        val _lv1: ListView = findViewById(R.id.lvl1)
        _lv1.adapter = lvAdapter

        val _searchView: SearchView = findViewById(R.id.searchView)
        _searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText?.lowercase() ?: ""
                filteredData.clear()

                if (searchText.isNotEmpty()) {
                    val filtered = data.filter { it.lowercase().contains(searchText) }
                    filteredData.addAll(filtered)
                } else {
                    filteredData.addAll(data)
                }

                lvAdapter.notifyDataSetChanged()
                return true
            }
        })

        val _btnTambah: Button = findViewById(R.id.btnTambah)
        _btnTambah.setOnClickListener {
            val dtAkhir = Integer.parseInt(data[data.size - 1]) + 1
            data.add(dtAkhir.toString())
            filteredData.add(dtAkhir.toString())
            lvAdapter.notifyDataSetChanged()
        }

        _lv1.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "${filteredData[position]}", Toast.LENGTH_SHORT).show()
        }

        val _btnHapus: Button = findViewById(R.id.btnHapus)
        _btnHapus.setOnClickListener {
            if (data.isNotEmpty()) {
                data.removeFirst()
                filteredData.removeFirst()
                lvAdapter.notifyDataSetChanged()
            }
        }
    }
}
