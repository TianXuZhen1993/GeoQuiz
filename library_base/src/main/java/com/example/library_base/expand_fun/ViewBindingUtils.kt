package com.example.library_base.expand_fun

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/**
 * @Function ViewBinding 扩展函数，以下方法是lazy懒加载,适用kotlin
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/1/4 17:03
 */


/**
 *  不使用反射的代码如下,将inflate函数作为参数传递
 *
 * @param VB
 * @param inflate
 * 例如：private val viewBinding by binding(ActivityMainBinding::inflate)
 */
fun <VB : ViewBinding> AppCompatActivity.viewBinding(inflate: (LayoutInflater) -> VB) = lazy {
    inflate(layoutInflater).also { binding ->
        if (binding is ViewDataBinding) binding.lifecycleOwner = this
    }
}

/**
 *  使用反射获取inflate方法
 *
 * @param VB
 */
inline fun <reified VB : ViewBinding> AppCompatActivity.inflateBinding() = lazy {
    inflateBinding<VB>(layoutInflater).also { binding ->
        if (binding is ViewDataBinding) binding.lifecycleOwner = this
    }
}


/**
 *  获取 inflate 方法的反射，然后塞入Activity 的 layoutInflater
 *
 * @param VB
 * @param layoutInflater
 */
inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB




inline fun <reified VB : ViewBinding> Fragment.viewBinding() = FragmentBindingProperty(VB::class.java)


class FragmentBindingProperty<VB : ViewBinding>(private val clazz: Class<VB>) :
    ReadOnlyProperty<Fragment, VB> {

    private var binding: VB? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (binding == null) {
            try {
                //反射获取View的bind方法
                binding = (clazz.getMethod("binding", View::class.java)
                    .invoke(null, thisRef.requireView()) as VB)
                    .also { binding ->
                        if (binding is ViewDataBinding) binding.lifecycleOwner =
                            thisRef.viewLifecycleOwner
                    }
            } catch (e: IllegalStateException) {
                throw IllegalStateException("The property of ${property.name} has been destroyed.")
            }
            //Fragment摧毁时将binding置位null避免内存泄露
            thisRef.viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    binding = null
                }
            })
        }
        return binding!!
    }
}

