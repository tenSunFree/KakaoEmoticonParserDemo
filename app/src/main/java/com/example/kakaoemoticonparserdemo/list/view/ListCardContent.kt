package com.example.kakaoemoticonparserdemo.list.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kakaoemoticonparserdemo.R
import com.example.kakaoemoticonparserdemo.common.inject.DaggerEmoticonDetailComponent
import com.example.kakaoemoticonparserdemo.list.model.ListResponse
import com.skydoves.landscapist.coil.CoilImage
import javax.inject.Inject
import retrofit2.Retrofit

class ListCardContent {

    @Inject
    lateinit var client: Retrofit

    init {
        DaggerEmoticonDetailComponent.builder()
            .build()
            .inject(this)
    }

    private var listener: OnEmoticonClickListener? = null

    interface OnEmoticonClickListener {
        fun onEmoticonClicked(emoticon: ListResponse.ListResponseItem)
    }

    fun setOnEmoticonClickListener(action: ListResponse.ListResponseItem.() -> Unit) {
        listener = object : OnEmoticonClickListener {
            override fun onEmoticonClicked(emoticon: ListResponse.ListResponseItem) {
                action(emoticon)
            }
        }
    }

    @Composable
    fun Bind(item: ListResponse.ListResponseItem) {
        Card(
            shape = shapes.medium,
            modifier = Modifier
                .clickable {
                    listener?.onEmoticonClicked(item)
                }
                .fillMaxWidth(),
            elevation = dimensionResource(R.dimen.margin_twice_half)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                CoilImage(
                    imageModel = item.thumbnailUrl,
                    contentScale = ContentScale.Crop,
                    circularRevealedEnabled = true,
                    modifier = Modifier.size(75.dp)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    textAlign = TextAlign.Center,
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    style = typography.body1
                )
            }
        }
    }
}
