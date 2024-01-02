package com.sun.m

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.sun.m.base.BaseActivity
import com.sun.m.databinding.ActivityMainBinding
import com.sun.m.receiver.NetworkChangedListener
import com.sun.m.utils.NetworkUtils
import com.sun.m.viewmode.FileViewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: FileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //startService<WebService>()
        viewModel.setIActivity(this)
        viewModel.startServices()
        viewModel.registerReceiver()
//        NetworkChangedListener(this).let {
//            it.register()
//            it.onNetworkChanged = {
//                binding.ip.text = NetworkUtils.getLocalIPAddress().let {
//                    it.toString().replace("/","")
//                }
//            }
//        }
        viewModel.ipLiveData.observe(this) {
            if (it) {
                binding.ip.text= NetworkUtils.getLocalIPAddress().let { it.toString().replace("/", "") }
            } else {
                binding.ip.text = "网络未连接"
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()

    }
}