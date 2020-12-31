package com.alion.fiction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var netViewModel: NetViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*创建viewmodel*/
        netViewModel = ViewModelProviders.of(this).get(NetViewModel::class.java)

        btn.setOnClickListener {
            /*请求数据*/
            netViewModel.getFictions()
        }

        /*数据发生变化时更新ui*/
        netViewModel.fictions.observe(this, Observer {
            tv.text = it.str
        })
    }
}
