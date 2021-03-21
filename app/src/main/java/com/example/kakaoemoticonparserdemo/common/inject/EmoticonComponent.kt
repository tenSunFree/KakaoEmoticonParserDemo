package com.example.kakaoemoticonparserdemo.common.inject

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.kakaoemoticonparserdemo.common.remote.EmoticonModule
import com.example.kakaoemoticonparserdemo.list.view.ListContent
import dagger.Component
import javax.inject.Singleton

@Singleton
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Component(modules = [EmoticonModule::class])
interface EmoticonComponent {
    fun inject(listContent: ListContent)
}
