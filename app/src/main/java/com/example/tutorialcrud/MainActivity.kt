package com.example.tutorialcrud

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.tutorialcrud.adapter.UserAdapter
import com.example.tutorialcrud.data.AppDatabase
import com.example.tutorialcrud.data.EditorActivity
import com.example.tutorialcrud.data.entity.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.floatingbutton)

        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object: UserAdapter.Dialog {
            override fun onClick(position: Int) {
                //Membuat dialog view
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(list[position].fullName)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog, which ->
                    if (which== 0){
                        //Coding Untuk Mengubah Item
                        val intent = Intent(this@MainActivity, EditorActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    }else if(which ==1){
                        //Coding Untuk Menghapus Item
                        database.userDataAccessObject().delete(list[position])
                        getData()
                    }else {
                        dialog.dismiss()
                    }
                })
                //Menampilkan Dialog
                val dialogView = dialog.create()
                dialogView.show()
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this, EditorActivity::class.java))
        }
    }
    override fun onResume(){
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData() {
        list.clear()
        list.addAll(database.userDataAccessObject().getAll())
        adapter.notifyDataSetChanged()
    }
}