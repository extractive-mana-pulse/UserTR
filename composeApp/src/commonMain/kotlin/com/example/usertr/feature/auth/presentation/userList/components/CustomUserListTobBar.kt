package com.example.usertr.feature.auth.presentation.userList.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.stringResource
import usertr.composeapp.generated.resources.Res
import usertr.composeapp.generated.resources.select_language
import usertr.composeapp.generated.resources.users

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CustomTopBar(
    onNavigateToFavorite: () -> Unit,
    onLanguageSelected: (String) -> Unit
) {
    var dropDownVisible by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.users),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = onNavigateToFavorite) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Box {
                IconButton(onClick = { dropDownVisible = !dropDownVisible }) {
                    Icon(
                        imageVector = Icons.Filled.Language,
                        contentDescription = stringResource(Res.string.select_language),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                DropdownMenu(
                    expanded = dropDownVisible,
                    onDismissRequest = { dropDownVisible = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(Res.string.uzbek),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onLanguageSelected("uz")
                            dropDownVisible = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(Res.string.russian),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onLanguageSelected("ru")
                            dropDownVisible = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(Res.string.english),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onLanguageSelected("en")
                            dropDownVisible = false
                        }
                    )
                }
            }
        }
    )
}