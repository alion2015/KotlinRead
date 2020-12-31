package com.alion.fiction.bean

import java.util.*

/**
 * [{"name": "天道图书馆",
    "link": "http://www.xbiquge.la/14/14203/",
    "author": "横扫天涯",
    "lastTime": "11-16"},
    {"name": "我有个天道图书馆系统",
    "link": "http://www.xbiquge.la/52/52666/",
    "author": "三石挥毫",
    "lastTime": "02-11"}]
 */

data class FictionSearch(
    var name:String,
    var link:String,
    var author:String,
    var lastTime:String
) {
}