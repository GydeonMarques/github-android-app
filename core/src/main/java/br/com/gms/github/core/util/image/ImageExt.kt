package br.com.gms.github.core.util.image

import android.widget.ImageView
import androidx.annotation.DrawableRes
import br.com.gms.github.R
import coil.load

fun ImageView.loadImageByUrl(url: String, @DrawableRes fallback: Int = R.drawable.ic_user) {
    load(url) {
        crossfade(true)
        error(fallback)
        fallback(fallback)
        placeholder(fallback)
    }
}