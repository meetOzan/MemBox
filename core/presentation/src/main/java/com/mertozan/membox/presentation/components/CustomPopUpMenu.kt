package com.mertozan.membox.presentation.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

data class DropDownItem(
    val text: String
)

@Composable
fun CustomPopUpMenu(
    dropdownItems: List<DropDownItem>,
    onItemClick: (DropDownItem) -> Unit
) {

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    DropdownMenu(
        expanded = isContextMenuVisible,
        onDismissRequest = {
            isContextMenuVisible = false
        },
        offset = pressOffset.copy(
            y = pressOffset.y - itemHeight
        )
    ) {
        dropdownItems.forEach {
            DropdownMenuItem(
                onClick = {
                    onItemClick(it)
                    isContextMenuVisible = false
                }, text = {
                    Text(text = it.text)
                })
        }
    }
}

@Preview
@Composable
fun PrevDropDown() {
    CustomPopUpMenu(
        dropdownItems = listOf(
            DropDownItem("Item 1"),
            DropDownItem("Item 2"),
            DropDownItem("Item 3")
        ),
        onItemClick = {

        }
    )
}