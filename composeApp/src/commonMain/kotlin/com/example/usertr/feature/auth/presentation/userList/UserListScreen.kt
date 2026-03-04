package com.example.usertr.feature.auth.presentation.userList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.usertr.core.components.Loading
import com.example.usertr.feature.auth.presentation.retry.RetryScreen
import com.example.usertr.feature.auth.presentation.userList.components.CustomTopBar
import com.example.usertr.feature.auth.presentation.userList.components.UserItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    uiState: UserListUiState,
    onUserClick: (User) -> Unit,
    onRetry: () -> Unit,
    onNavigateToFavorite: () -> Unit,
    onSwitchLanguage: (String) -> Unit
) {
    Scaffold(
        topBar = { CustomTopBar(onNavigateToFavorite, onSwitchLanguage) }
    ) { innerPadding ->
        when (uiState) {
            is UserListUiState.Loading -> Loading(innerPadding)

            is UserListUiState.Error -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    RetryScreen(errorMessage = uiState.message, onRetry = onRetry)
                }
            }

            is UserListUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(uiState.users, key = { it.id }) { user ->
                        UserItem(user = user, onClick = { onUserClick(user) })
                        HorizontalDivider(modifier = Modifier.padding(start = 72.dp))
                    }
                }
            }
        }
    }
}