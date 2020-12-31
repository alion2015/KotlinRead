package com.alion.fiction

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alion.fiction.bean.FictionDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetViewModel : ViewModel() {
    var fictions=MutableLiveData<FictionDetail>() /*= MutableLiveData<List<FictionSearch>>()*/
    fun getFictions() {
        /*viewModelScope是一个绑定到当前viewModel的作用域  当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题*/
        viewModelScope.launch {
            try {
                /*withContext表示挂起块  配合Retrofit声明的suspend函数执行 该块会挂起直到里面的网络请求完成 最一行就是返回值*/
                val data = withContext(Dispatchers.IO) {

                    /*dataConvert扩展函数可以很方便的解析出我们想要的数据  接口很多的情况下下可以节省不少无用代码*/
                    RetrofitFactory.instance.getService(ApiService::class.java)
                        //.getSearch("勉传")
                        //.getList("http://www.xbiquge.la/25/25547/")
                        .getDetail("/25/25547/12441746.html")
                }

                /*给LiveData赋值  ui会自动更新*/
                fictions.value = data

            } catch (e: Exception) {


                /*请求异常的话在这里处理*/
                e.printStackTrace()

                Log.i("请求失败", "${e.message}")

            }


        }
    }


}