package com.alion.fiction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alion.fiction.bean.FictionSearch

class KotlinRecycleAdapter : RecyclerView.Adapter<KotlinRecycleAdapter.MyHolder> {
    private var list: List<FictionSearch>? = null
    private var context: Context? = null
    private var itemClickListener: IKotlinItemClickListener? = null

    constructor (mContext: Context) {
        this.context = mContext
    }


    fun setData(list: List<FictionSearch>?){
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_mainlist, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = (list?.size)?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var position_item = list!![position]
        var str = "name==${position_item.name}\r author==${position_item.author}\n lastTime==${position_item.lastTime}\n"
        holder?.text?.text = str

        // 点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position_item.link)
        }

    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var text: TextView = itemView.findViewById(R.id.item_seearch)
    }

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(link: String)
    }

}