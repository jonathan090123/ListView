package uts.c14220035.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var data = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        data.addAll(listOf("1","2","3","4","5"))

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val lvAdapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        val _lv1 : ListView = findViewById(R.id.lvl1)
        _lv1.adapter = lvAdapter
        val _btnTambah : Button = findViewById(R.id.btnTambah)
        _btnTambah.setOnClickListener {
            var dtAkhir = Integer.parseInt(data.get(data.size-1)) + 1
            data.add(dtAkhir.toString())
        }
    }
}