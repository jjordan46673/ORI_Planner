package com.yyttrium.oriplanner.ui.compose.content.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun OriCheckbox(
    checked: Boolean,
    deletable: Boolean = true,
    expanded: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        // TODO add confirmation dialog
        if (deletable && expanded) {
            IconButton(
                onClick = onDelete,
                enabled = checked
            ) {
                Icon(
                    imageVector = Icons.Rounded.DeleteOutline,
                    contentDescription = stringResource(R.string.icon_delete)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCheckboxCollapsedUnchecked() {
    ORIPlannerTheme {
        OriCheckbox(
            checked = false,
            expanded = false,
            onCheckedChange = {},
            onDelete = {}
        )
    }
}

@Preview
@Composable
fun PreviewCheckboxCollapsedChecked() {
    ORIPlannerTheme {
        OriCheckbox(
            checked = true,
            expanded = false,
            onCheckedChange = {},
            onDelete = {}
        )
    }
}

@Preview
@Composable
fun PreviewCheckboxExpandedUnchecked() {
    ORIPlannerTheme {
        OriCheckbox(
            checked = false,
            expanded = true,
            onCheckedChange = {},
            onDelete = {}
        )
    }
}

@Preview
@Composable
fun PreviewCheckboxExpandedChecked() {
    ORIPlannerTheme {
        OriCheckbox(
            checked = true,
            expanded = true,
            onCheckedChange = {},
            onDelete = {}
        )
    }
}