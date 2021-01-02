package com.alion.fiction

import android.opengl.Visibility
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alion.fiction.bean.FictionList
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log


class MainActivity : AppCompatActivity(), KotlinRecycleAdapter.IKotlinItemClickListener {
    private lateinit var adapter: KotlinRecycleAdapter
    private var listData: FictionList? = null
    private lateinit var netViewModel: NetViewModel
    private var index:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent()
        obserData()
    }

    private fun obserData() {
        netViewModel.name_link.observe(this, Observer {
            // TODO: 2021/1/2 更新UI显示
            adapter.setData(it)
            showTXT(false)
            adapter.notifyDataSetChanged()
        })
        netViewModel.link_list.observe(this, Observer {
            // TODO: 2021/1/2 更新保存的list
            //listData = it
            Log.d("alionlog", "obserData: it.list[0].link==(${it.list})")
            netViewModel.get_url_str(it.list[index++].link)

        })
        netViewModel.url_str.observe(this, Observer {
            // TODO: 2021/1/2 显示结果
            showTXT(true)
            main_txtview.text=it.str
        })
    }

    private fun showTXT(show:Boolean){
        if (show){
            main_txtview.visibility = View.VISIBLE
            main_recycle.visibility = View.GONE
        }else{
            main_txtview.visibility = View.GONE
            main_recycle.visibility = View.VISIBLE
        }
    }

    private fun initEvent() {
        main_txtview.movementMethod=ScrollingMovementMethod.getInstance()

        /*创建viewmodel*/
        netViewModel = ViewModelProviders.of(this).get(NetViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // layoutManager
        main_recycle.layoutManager = layoutManager

        // itemDecoration
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        main_recycle.addItemDecoration(itemDecoration)

        // animation
        main_recycle.itemAnimator = DefaultItemAnimator()

        // setAdapter
        adapter = KotlinRecycleAdapter(this)
        main_recycle.adapter = adapter
        // itemClick
        adapter!!.setOnKotlinItemClickListener(this)

        /*数据发生变化时更新ui*/
        /*netViewModel.fictions.observe(this, Observer {
            tv.text = it.str
            /*请求数据*/
            /*netViewModel.link_list.*/
        })*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val sv =menu!!.findItem(R.id.menu_search).actionView as SearchView
        //设置监听
        //设置监听
        sv.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // TODO: 2021/1/2 搜索并将结果显示到recycleview
                netViewModel.get_name_link(newText)
                Log.d("alionlog", "onQueryTextChange: newText==$newText")
                return true
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

    //回传点击的link
    override fun onItemClickListener(link: String) {
        // TODO: 2021/1/2 加载具体的list并显示第一章
        netViewModel.get_link_list(link)
        Log.d("alionlog", "onItemClickListener: link==$link")
    }
}
