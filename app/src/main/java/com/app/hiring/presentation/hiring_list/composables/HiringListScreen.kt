package com.app.hiring.presentation.hiring_list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.hiring.presentation.hiring_list.HiringListViewModel

@Composable
fun HiringListScreen(
    viewModel: HiringListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    // Create a list to keep track of the expanded state for each row
    val expandedStates = remember { mutableStateListOf<Boolean>() }
    repeat(state.itemList.size) {
        expandedStates.add(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.itemList.toList()) { item ->
                var index = item.first - 1

                Row(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "List : ${item.first}",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = if (expandedStates[index]) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Expand Icon",
                        modifier = Modifier.clickable {
                            // Toggle the expanded state of the corresponding row
                            expandedStates[index] = !expandedStates[index]
                        }
                    )

                    DropdownMenu(
                        expanded = expandedStates[index],
                        onDismissRequest = { expandedStates[index] = false },
                        modifier = Modifier.width(350.dp).fillParentMaxHeight(0.5f)
                    ) {
                        item.second.forEach { item ->
                            DropdownMenuItem(onClick = { /* TO DO */ }) {
                                Column {
                                    Text(
                                        text = "${item.id} (${item.name})",
                                        style = MaterialTheme.typography.subtitle2,
                                        modifier = Modifier.padding(
                                            vertical = 16.dp,
                                            horizontal = 16.dp
                                        ),
                                        color = Color.White
                                    )
                                    Divider()
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))
                Divider()
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}