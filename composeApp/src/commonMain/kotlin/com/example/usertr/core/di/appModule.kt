package com.example.usertr.core.di

import com.example.usertr.createHttpClient
import com.example.usertr.feature.auth.data.UserDataSourceImpl
import com.example.usertr.feature.auth.data.UserLocalDataSource
import com.example.usertr.feature.auth.domain.UserDataSource
import com.example.usertr.feature.home.data.CommentDataSourceImpl
import com.example.usertr.feature.home.data.PostDataSourceImpl
import com.example.usertr.feature.home.data.PostLocalDataSource
import com.example.usertr.feature.home.domain.CommentDataSource
import com.example.usertr.feature.home.domain.PostDataSource
import com.example.usertr.feature.home.presentation.favorite.FavoritesViewModel
import com.example.usertr.feature.home.presentation.post_detail.PostDetailViewModel
import com.example.usertr.feature.home.presentation.post_list.PostListViewModel
import com.example.usertr.feature.multi_language.presentation.LanguageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { createHttpClient() }

    single { UserLocalDataSource(get()) }
    single { PostLocalDataSource(get()) }

    single<CommentDataSource> { CommentDataSourceImpl(get()) }
    single<UserDataSource> { UserDataSourceImpl(get()) }
    single<PostDataSource> { PostDataSourceImpl(get()) }

    viewModel { params ->
        PostListViewModel(
            userId = params.get(),
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    viewModel {
        PostDetailViewModel(
            postId = get(),
            localDataSource = get(),
            commentDataSource = get()
        )
    }

    viewModel { FavoritesViewModel(get()) }

    viewModel { LanguageViewModel(get()) }
}