package com.sun.m.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.sun.m.R
import com.sun.m.utils.ScreenUtils
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

open class BaseActivity<T:ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val superclass: Type = javaClass.genericSuperclass
        val aClass: Class<*> = (superclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        try {
            val method: Method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
            binding = method.invoke(null, layoutInflater) as T
            setContentView(binding.root)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }



    private fun fullScreen() {
        ScreenUtils.setNavigationBarColor(this,ContextCompat.getColor(this,R.color.background))
        ScreenUtils.setStatusBarColor(this,ContextCompat.getColor(this,R.color.background))
        ScreenUtils.setStatusBarDarkFont(this,true)
        ScreenUtils.setNavigationBarFont(this,true)
    }
}