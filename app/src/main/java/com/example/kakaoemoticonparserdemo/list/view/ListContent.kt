package com.example.kakaoemoticonparserdemo.list.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.kakaoemoticonparserdemo.*
import com.example.kakaoemoticonparserdemo.common.theme.AppThemeState
import com.example.kakaoemoticonparserdemo.common.inject.DaggerEmoticonComponent
import com.example.kakaoemoticonparserdemo.common.remote.EmoticonInterface
import com.example.kakaoemoticonparserdemo.list.model.ListResponse
import com.example.kakaoemoticonparserdemo.list.model.ListResponseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import me.sungbin.androidutils.util.toastutil.ToastUtil
import javax.inject.Inject
import retrofit2.Retrofit

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
class ListContent {

    @Inject
    lateinit var client: Retrofit

    lateinit var context: Context
    lateinit var alert: AlertDialog
    private val emoticonContent by lazy { ListCardContent() }

    private var errorMessage = ""
    private val emoticonItemLists: MutableList<ListResponse.ListResponseItem> = mutableListOf()

    init {
        DaggerEmoticonComponent.builder()
            .build()
            .inject(this)
    }

    @Composable
    fun Bind() {
        context = LocalContext.current
        val searchState = rememberSaveable { mutableStateOf(ListResponseState.HOME) }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = stringResource(R.string.app_name),
                                style = typography.body1
                            )
                        }
                    },
                    elevation = dimensionResource(R.dimen.margin_half)
                )
            },
            content = { BindSearchContent(searchState) }
        )
    }

    @Composable
    private fun BindSearchContent(
        searchState: MutableState<ListResponseState>
    ) {
        var isGetListResponse by remember { mutableStateOf(false) }
        if (!isGetListResponse) {
            getListResponse(searchState)
            isGetListResponse = true
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Crossfade(searchState.value) { state ->
                when (state) {
                    ListResponseState.RESULT -> SearchResultContent()
                }
            }
        }
    }

    @Composable
    private fun SearchResultContent() {
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
        emoticonContent.setOnEmoticonClickListener {
            ToastUtil.show(context = context, title)
        }
        BottomSheetScaffold(
            sheetShape = shapes.large,
            sheetElevation = dimensionResource(R.dimen.margin_twice_half),
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 0.dp,
            sheetContent = {}
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = dimensionResource(R.dimen.margin_half),
                        top = dimensionResource(R.dimen.margin_twice_and_half)
                    )
            ) {
                items(
                    items = emoticonItemLists,
                    itemContent = { item ->
                        Box(
                            Modifier.padding(
                                start = dimensionResource(R.dimen.margin_default),
                                end = dimensionResource(R.dimen.margin_default),
                                bottom = dimensionResource(R.dimen.margin_half),
                                top = dimensionResource(R.dimen.margin_half)
                            )
                        ) {
                            emoticonContent.Bind(item)
                        }
                    }
                )
            }
        }
    }

    private fun getListResponse(searchState: MutableState<ListResponseState>) {
        client.create(EmoticonInterface::class.java).run {
            showLoadingDialog()
            emoticonItemLists.clear()
            getListResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        emoticonItemLists.addAll(response)
                    },
                    {
                        searchState.value = ListResponseState.ERROR
                        errorMessage = it.message.toString()
                        closeLoadingDialog()
                    },
                    {
                        searchState.value = if (emoticonItemLists.isEmpty()) {
                            ListResponseState.NULL
                        } else {
                            ListResponseState.RESULT
                        }
                        closeLoadingDialog()
                    }
                )
        }
    }

    private fun showLoadingDialog() {
        val dialog = AlertDialog.Builder(context)
        dialog.setView(
            LottieAnimationView(context).apply {
                setAnimation(R.raw.loading2)
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
            }
        )
        dialog.setCancelable(false)
        alert = dialog.create()
        alert.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        alert.show()
    }

    private fun closeLoadingDialog() {
        alert.cancel()
    }
}
