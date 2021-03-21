package com.example.kakaoemoticonparserdemo.common.inject

import com.example.kakaoemoticonparserdemo.list.view.ListCardContent
import com.example.kakaoemoticonparserdemo.common.remote.EmoticonModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [EmoticonModule::class])
interface EmoticonDetailComponent {
    fun inject(listCardContent: ListCardContent)
}
