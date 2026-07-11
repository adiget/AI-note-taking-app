package com.example.smartmeetingai.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartmeetingai.data.model.Note

@Composable
fun NoteCard(
    note: Note,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onDelete: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                onSelect()
            }
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface
            ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    note.title,
                    style = MaterialTheme.typography.titleMedium,
                )
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(note.content)

            if (note.summary != null || note.keywords.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                note.summary?.let { summary ->
                    Text(
                        text = "📌 $summary",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                if (note.keywords.isNotEmpty()) {
                    Text(
                        text = "🏷️ ${note.keywords.joinToString(", ")}",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                if (note.actionItems.isNotEmpty()) {
                    Text(
                        text = "✅ ${note.actionItems.joinToString(", ")}",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }

        }
    }
}