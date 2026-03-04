package com.example.usertr.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.usertr.feature.auth.presentation.retry.RetryScreen
import com.example.usertr.feature.auth.presentation.splash.SplashScreen
import com.example.usertr.feature.auth.presentation.splash.SplashViewModel
import com.example.usertr.feature.auth.presentation.userList.UserListScreen
import com.example.usertr.feature.auth.presentation.userList.UserListViewModel
import com.example.usertr.feature.home.presentation.favorite.FavoritesScreen
import com.example.usertr.feature.home.presentation.favorite.FavoritesViewModel
import com.example.usertr.feature.home.presentation.post_detail.PostDetailScreen
import com.example.usertr.feature.home.presentation.post_detail.PostDetailViewModel
import com.example.usertr.feature.home.presentation.post_list.PostListScreen
import com.example.usertr.feature.home.presentation.post_list.PostListViewModel
import com.example.usertr.feature.multi_language.presentation.LanguageViewModel
import com.example.usertr.feature.multi_language.presentation.LocalAppLocale
import com.example.usertr.navigation.screens.Screens
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    val lanVm = koinViewModel<LanguageViewModel>()
    val language by lanVm.languageCode.collectAsStateWithLifecycle()

    CompositionLocalProvider(LocalAppLocale provides language) {
        NavHost(
            navController = navController,
            startDestination = Screens.Splash
        ) {
            composable<Screens.Splash> {
                val vm = koinViewModel<SplashViewModel>()
                val destination by vm.destination.collectAsStateWithLifecycle()
                SplashScreen(
                    destination = destination,
                    onNavigateToUserList = {
                        navController.navigate(Screens.Splash.UserList) {
                            popUpTo(Screens.Splash) { inclusive = true }
                        }
                    },
                    onNavigateToRetry = {
                        navController.navigate(Screens.Splash.Retry) {
                            popUpTo(Screens.Splash) { inclusive = true }
                        }
                    }
                )
            }

            composable<Screens.Splash.Retry> {
                RetryScreen(
                    errorMessage = "Could not connect to the server.",
                    onRetry = {
                        navController.navigate(Screens.Splash) {
                            popUpTo(Screens.Splash.Retry) { inclusive = true }
                        }
                    }
                )
            }

            composable<Screens.Splash.UserList> {
                val vm = koinViewModel<UserListViewModel>()
                val uiState by vm.uiState.collectAsStateWithLifecycle()

                UserListScreen(
                    uiState = uiState,
                    onUserClick = { user ->
                        navController.navigate(
                            Screens.PostList(
                                userId = user.id,
                                userName = user.name
                            )
                        )
                    },
                    onRetry = { vm.loadUsers() },
                    onNavigateToFavorite = {
                        navController.navigate(Screens.Favorites)
                    },
                    onSwitchLanguage = {
                        language?.let { languageCode -> lanVm.switchLanguage(languageCode) }
                    }
                )
            }
            composable<Screens.PostList> { backStackEntry ->
                val route = backStackEntry.toRoute<Screens.PostList>()
                val vm = koinViewModel<PostListViewModel> {
                    parametersOf(route.userId, route.userName)
                }
                val uiState by vm.uiState.collectAsStateWithLifecycle()

                PostListScreen(
                    uiState = uiState,
                    onPostClick = { post ->
                        navController.navigate(Screens.PostDetail(postId = post.id))
                    },
                    onFavoriteToggle = { post -> vm.toggleFavorite(post) },
                    onRetry = { vm.loadPosts() },
                    onBack = { navController.navigateUp() },
                    onRefresh = { vm.refresh() },
                )
            }
            composable<Screens.PostDetail> { backStackEntry ->
                val route = backStackEntry.toRoute<Screens.PostDetail>()
                val vm = koinViewModel<PostDetailViewModel> {
                    parametersOf(route.postId)
                }
                val uiState by vm.uiState.collectAsState()

                PostDetailScreen(
                    uiState = uiState,
                    onRetry = { vm.loadDetail() },
                    onBack = { navController.navigateUp() }
                )
            }

            composable<Screens.Favorites> {
                val vm = koinViewModel<FavoritesViewModel>()
                val uiState by vm.uiState.collectAsState()

                FavoritesScreen(
                    uiState = uiState,
                    onPostClick = { post ->
                        navController.navigate(Screens.PostDetail(postId = post.id))
                    },
                    onBack = { navController.navigateUp() },
                    onFavoriteToggle = { post -> vm.toggleFavorite(post) }
                )
            }
        }
    }
}