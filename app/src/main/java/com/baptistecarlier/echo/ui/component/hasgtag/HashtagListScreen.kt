package com.baptistecarlier.echo.ui.component.hasgtag

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.ui.component.common.ErrorView
import com.baptistecarlier.echo.ui.component.common.LoadingView
import com.baptistecarlier.echo.ui.state.HashtagListScreenState

enum class HashtagItemSort(@StringRes val label: Int) {
    Name(R.string.sort_by_name),
    Ratio(R.string.sort_by_ratio)
}

@Composable
fun HashtagListScreen(
    state: HashtagListScreenState,
    onBack: () -> Unit,
    onRefresh: () -> Unit,
    sortBy: (HashtagItemSort) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                title = { Text(stringResource(id = R.string.screen_hashtaglist)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, stringResource(id = R.string.back))
                    }
                },
                elevation = 0.dp
            )
        }
    ) { innerPadding ->

        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            if (state.isLoading) {
                LoadingView()
            } else if (state.isError) {
                ErrorView(onRefresh)
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.total, state.nbVideo)
                    )
                    HashtagItemSort.values().forEach { sort ->
                        Button(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            onClick = { sortBy(sort) }
                        ) {
                            Text(stringResource(id = sort.label))
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    ),
                ) {
                    items(state.list) { hashtagRatioItem ->
                        HashtagItemView(8.dp, hashtagRatioItem, state.nbVideo)
                    }
                }
            }
        }
    }
}
