package com.app.hiring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.app.hiring.presentation.hiring_list.composables.HiringListScreen
import com.app.hiring.ui.theme.FetchAmazonHiringTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TAG: String? = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchAmazonHiringTheme {
                MainContent()
            }
        }
    }

    @Composable
    fun MainContent() {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        "Fetch | Hiring List",
                        color = Color.White
                    )
                }, backgroundColor = Color(0xff0f9d58))
            },
            content = { MyContent() }
        )
    }

    @Composable
    fun MyContent() {
        Surface(
            color = MaterialTheme.colors.onPrimary
        ) {
            HiringListScreen()
        }
    }
}