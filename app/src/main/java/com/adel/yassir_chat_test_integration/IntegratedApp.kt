package com.adel.yassir_chat_test_integration

import android.app.Application
import com.yassir.android.chat.YassirChat
import com.yassir.android.chat.data.model.ChatConnectionConfig
import com.yassir.android.chat.util.YassirUtils


class IntegratedApp : Application() {
    override fun onCreate() {
        super.onCreate()

        YassirChat.Builder()
            .appContext(this)
            .connConfig(ChatConnectionConfig.defaultConnectionConfig(this))
            .deviceId(YassirUtils.getSHA1DeviceId(this))
            .build()

    }
}