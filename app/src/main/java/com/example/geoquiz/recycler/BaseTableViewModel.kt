package com.example.geoquiz.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/6/10 15:57
 */
class BaseTableViewModel : ViewModel() {

    val customBaseLiveData: MutableLiveData<MutableList<CustomBaseTableBean>> =
        MutableLiveData()


    fun getCustomTableList(page: Int) {
        val beans = mutableListOf<CustomBaseTableBean>()
        for (i in 1..10) {
            val customBaseTableBean = CustomBaseTableBean(
                "调试" + page,
                "主键id " + (page + i).toString(),
                "本次基本表得分：" + page
            )
            beans.add(customBaseTableBean)
        }
        customBaseLiveData.postValue(beans)
    }
}