package br.com.ada.droidchat.util.di

import br.com.ada.droidchat.util.image.ImageCompressor
import br.com.ada.droidchat.util.image.ImageCompressorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ImageCompressorModule {

    @Binds
    fun bindImageCompressor(compressor: ImageCompressorImpl): ImageCompressor
}