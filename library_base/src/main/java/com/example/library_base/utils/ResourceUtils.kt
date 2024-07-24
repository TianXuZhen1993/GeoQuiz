package com.example.library_base.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.library_base.core.CoreUtils

@Deprecated("调试阶段")
object ResourceUtils {

    private val context = CoreUtils.getApp()

    private fun getContext(): Context {
        return context
    }

    fun getDrawable(@DrawableRes id: Int): Drawable? {
        return ContextCompat.getDrawable(getContext(), id)
    }

    fun getStringById(@StringRes strRes: Int): String {
        return getContext().resources.getString(strRes)
    }

    fun getStringById(@StringRes strRes: Int, vararg any: Any): String {
        return getContext().resources.getString(strRes, any)
    }


    /**
     * 根据Name 获取Id的资源id
     *
     * @param name
     * @return
     */
    fun getIdByName(name: String): Int {
        return getContext().resources.getIdentifier(name, "id", getContext().packageName)
    }

    /**
     * 根据Name 获取资源String 的ID
     *
     * @param name
     * @return
     */
    fun getStringIdByName(name: String): Int {
        return getContext().resources.getIdentifier(name, "string", getContext().packageName)
    }

    /**
     * Return the color identifier by name.
     *
     * @param name The name of color.
     * @return the color identifier by name
     */
    fun getColorIdByName(name: String): Int {
        return getContext().resources.getIdentifier(name, "color", getContext().packageName)
    }

    /**
     * Return the dimen identifier by name.
     *
     * @param name The name of dimen.
     * @return the dimen identifier by name
     */
    fun getDimenIdByName(name: String?): Int {
        return getContext().resources.getIdentifier(name, "dimen", getContext().packageName)
    }

    /**
     * Return the drawable identifier by name.
     *
     * @param name The name of drawable.
     * @return the drawable identifier by name
     */
    fun getDrawableIdByName(name: String?): Int {
        return getContext().resources.getIdentifier(name, "drawable", getContext().packageName)
    }

    /**
     * Return the mipmap identifier by name.
     *
     * @param name The name of mipmap.
     * @return the mipmap identifier by name
     */
    fun getMipmapIdByName(name: String): Int {
        return getContext().resources.getIdentifier(name, "mipmap", getContext().packageName)
    }

    /**
     * Return the layout identifier by name.
     *
     * @param name The name of layout.
     * @return the layout identifier by name
     */
    fun getLayoutIdByName(name: String?): Int {
        return getContext().resources.getIdentifier(name, "layout", getContext().packageName)
    }

    /**
     * Return the style identifier by name.
     *
     * @param name The name of style.
     * @return the style identifier by name
     */
    fun getStyleIdByName(name: String?): Int {
        return getContext().resources.getIdentifier(name, "style", getContext().packageName)
    }

    /**
     * Return the anim identifier by name.
     *
     * @param name The name of anim.
     * @return the anim identifier by name
     */
    fun getAnimIdByName(name: String?): Int {
        return getContext().resources.getIdentifier(name, "anim", getContext().packageName)
    }

    /**
     * Return the menu identifier by name.
     *
     * @param name The name of menu.
     * @return the menu identifier by name
     */
    fun getMenuIdByName(name: String?): Int {
        return getContext().resources.getIdentifier(name, "menu", getContext().packageName)
    }

}

