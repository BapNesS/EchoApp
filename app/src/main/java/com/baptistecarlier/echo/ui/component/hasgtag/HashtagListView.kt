package com.baptistecarlier.echo.ui.component.hasgtag

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.ui.component.common.ErrorView
import com.baptistecarlier.echo.ui.component.common.LoadingView
import com.baptistecarlier.echo.ui.model.HashtagItemSort
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HashtagListView(
    state: HashtagListUiState,
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

                val listState = rememberLazyListState()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val coroutineScope = rememberCoroutineScope()

                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.total, state.nbVideo)
                    )
                    HashtagItemSort.values().forEach { sort ->
                        Button(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            onClick = {
                                sortBy(sort)
                                coroutineScope.launch {
                                    delay(200)
                                    listState.animateScrollToItem(0)
                                }
                            }
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
                    state = listState
                ) {
                    items(state.list, key = { it.label }) { hashtagRatioItem ->
                        HashtagItemView(
                            Modifier.animateItemPlacement(),
                            8.dp,
                            hashtagRatioItem
                        )
                    }
                }
            }
        }
    }
}
