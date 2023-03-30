package com.example.themoviedatabase.common.loader

import com.example.themoviedatabase.common.loader.image.GlideImageLoaderImpl
import com.example.themoviedatabase.common.loader.image.IImageLoader

object LoaderFactory {
    private val imageLoader = GlideImageLoaderImpl()

    fun glide(): IImageLoader {
        return imageLoader
    }
}
