package com.example.kakaoemoticonparserdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.kakaoemoticonparserdemo.common.theme.AppThemeState
import com.example.kakaoemoticonparserdemo.common.util.parseColor
import com.example.kakaoemoticonparserdemo.common.theme.AppTheme
import com.example.kakaoemoticonparserdemo.common.theme.SystemUiController
import com.example.kakaoemoticonparserdemo.list.view.ListContent

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            val appThemeState = remember { mutableStateOf(AppThemeState()) }
            BindView(appThemeState.value, systemUiController) {
                MainContent()
            }
        }
    }

    @Composable
    private fun BindView(
        appThemeState: AppThemeState,
        systemUiController: SystemUiController?,
        content: @Composable () -> Unit
    ) {
        systemUiController?.setStatusBarColor(
            appThemeState.parseColor(),
            appThemeState.darkTheme
        )
        AppTheme(appThemeState) {
            content()
        }
    }

    @ExperimentalComposeUiApi
    @Composable
    private fun MainContent() {
        NavigationFragmentContent()
    }

    @ExperimentalComposeUiApi
    @Composable
    private fun NavigationFragmentContent() {
        Surface(color = MaterialTheme.colors.background) {
            ListContent().Bind()
        }
    }
}
