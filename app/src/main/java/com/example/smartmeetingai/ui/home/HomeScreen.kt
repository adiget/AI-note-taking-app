package com.example.smartmeetingai.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartmeetingai.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(vm: HomeViewModel = hiltViewModel()) {
    val uiState by vm.uiState.collectAsState()
    val notes = uiState.notes
    val lazyListState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    vm.addNote(title, content)
                    title = ""
                    content = ""
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Note")
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            state = lazyListState,
        ) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = content,
                        onValueChange = { content = it },
                        label = { Text("Content") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 4,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { vm.analyzeSelectedNote() },
                        enabled = uiState.selectedNote != null && !uiState.isLoading,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(if (uiState.isLoading) "Analyzing..." else "Analyze Selected Note")
                    }

                    uiState.analysis?.let { analysis ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Summary: ${analysis.summary}")
                        Text("Keywords: ${analysis.keywords.joinToString(", ")}")
                        Text("Action Items: ${analysis.actionItems.joinToString(", ")}")
                    }

                    uiState.error?.let { error ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            }

            items(notes) { note ->
                NoteCard(
                    note = note,
                    isSelected = uiState.selectedNote?.id == note.id,
                    onSelect = { vm.selectNote(note) },
                    onDelete = {
                        vm.deleteNote(note)
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Note deleted",
                                actionLabel = "Undo",
                                duration = SnackbarDuration.Short,
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                vm.restoreNote(note)
                            }
                        }
                    },
                )
            }
        }
    }
}
