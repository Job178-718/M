package com.sun.m.base

import android.content.Intent
import android.os.IBinder
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.sun.m.R
import com.sun.m.utils.LifecycleUtils
import io.legado.app.help.coroutine.Coroutine
import io.legado.app.lib.permission.Permissions
import io.legado.app.lib.permission.PermissionsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext

abstract class BaseService : LifecycleService() {

    fun <T> execute(
        scope: CoroutineScope = lifecycleScope,
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        executeContext: CoroutineContext = Dispatchers.Main,
        block: suspend CoroutineScope.() -> T
    ) = Coroutine.async(scope, context, start, executeContext, block)

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        LifecycleUtils.onServiceCreate(this)
        checkNotificationPermission()
    }

    @CallSuper
    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        LifecycleUtils.onServiceDestroy(this)
    }

    /**
     * 检测通知权限
     */
    private fun checkNotificationPermission() {
        PermissionsCompat.Builder()
            .addPermissions(Permissions.POST_NOTIFICATIONS)
            .rationale(R.string.notification_permission_rationale)
            .onGranted {
                if (lifecycleScope.isActive) {

                }
            }
            .request()
    }
}