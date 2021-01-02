package com.alion.fiction

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alion.fiction.bean.FictionDetail
import com.alion.fiction.bean.FictionList
import com.alion.fiction.bean.FictionSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetViewModel : ViewModel() {
    var name_link=MutableLiveData<List<FictionSearch>>()
    var link_list=MutableLiveData<FictionList>() /*= MutableLiveData<List<FictionSearch>>()*/
    var url_str=MutableLiveData<FictionDetail>() /*= MutableLiveData<List<FictionSearch>>()*/
    open fun get_name_link(name:String) {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitFactory.instance.getService(ApiService::class.java)
                        .getSearch(name)
                }
                name_link.value = data
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("get_name_link请求失败", "${e.message}")
            }
        }
    }
    open fun get_link_list(link:String) {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitFactory.instance.getService(ApiService::class.java)
                        .getList(link)
                }
                link_list.value = data
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("get_link_list请求失败", "${e.message}")
            }
        }
    }
    open fun get_url_str(url:String) {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitFactory.instance.getService(ApiService::class.java)
                        .getDetail(url)
                }
                url_str.value = data
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("get_url_str请求失败", "${e.message}")
            }
        }
    }


}