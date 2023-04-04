package com.adel.yassir_chat_test_integration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.adel.yassir_chat_test_integration.databinding.ActivityMainBinding
import com.yassir.android.chat.YassirChat
import com.yassir.android.chat.data.model.AssociatedUsedType
import com.yassir.android.chat.data.model.ChatRoomData
import com.yassir.android.chat.data.model.login.LoginListener
import kotlinx.coroutines.launch

const val TAG = "INTEGRATIONTAG"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var chat = YassirChat.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            chat.loginAndConnect("SomeOne", "SomeOne", object : LoginListener {
                override fun onFailure(ex: Exception) {
                    binding.startBtn.isEnabled = false
                    binding.stateTv.text = ex.message.toString()
                    Log.i(TAG, ex.toString())
                }

                override fun onSuccess() {
                   // word around till fix the interface to be thread safe
                    lifecycleScope.launch {
                        binding.startBtn.isEnabled = true
                        binding.stateTv.text = "connected"
                    }
                }
            })
        }
        binding.startBtn.setOnClickListener {
            startChat()

        }

    }

    private fun startChat() {
        val chatRoomData = ChatRoomData(
            "driverID",
            AssociatedUsedType.DRIVER,
            "driverName",
            "adel233"
        )
        YassirChat.getInstance().startChatActivity(this@MainActivity, chatRoomData)
    }
}