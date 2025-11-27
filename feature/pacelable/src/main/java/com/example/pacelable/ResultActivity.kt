package com.example.pacelable

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.model.UserParcelable
import com.example.model.UserSerializable

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = intent.getStringExtra("type") ?: ""

        val userParcelable =
            if (type == "Parcelable") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra("user", UserParcelable::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra("user")
                }
            } else {
                null
            }

        val userSerializable =
            if (type == "Serializable") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getSerializableExtra("user", UserSerializable::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getSerializableExtra("user") as? UserSerializable
                }
            } else {
                null
            }

        setContent {
            MaterialTheme {
                ResultScreen(
                    type = type,
                    userParcelable = userParcelable,
                    userSerializable = userSerializable,
                    onBackClick = { finish() },
                )
            }
        }
    }
}
