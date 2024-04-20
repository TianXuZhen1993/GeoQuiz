package com.example.geoquiz.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.geoquiz.beatbox.bean.Sound

/**
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/4/7 20:59
 */
class SoundViewModel : BaseObservable() {

    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name
}