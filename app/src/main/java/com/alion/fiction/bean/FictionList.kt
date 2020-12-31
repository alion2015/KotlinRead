package com.alion.fiction.bean

/**
 * {
    "img": "http://www.xbiquge.la/files/article/image/14/14203/14203s.jpg",
    "desc": "    张悬穿越异界，成了一名光荣的教师，。",
    "list": [
    {
    "name": "第一章 骗子",
    "link": "/14/14203/6137473.html"
    },
    {
    "name": "第二章 不要脸",
    "link": "/14/14203/6137474.html"
    }]
    }
 */
data class FictionList(
    var img:String,
    var desc:String,
    var list:List<FictionSearchList>
) {
    data class FictionSearchList(var name:String,var link:String){

    }
}