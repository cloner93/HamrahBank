package com.pmb.ballon.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSpinner(
    modifier: Modifier = Modifier,
    options: List<String>?,
    labelString: String,
    displayText: String,
    isEnabled: Boolean = false,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (isEnabled)
                expanded = !expanded
        }
    ) {
        AppBaseTextField(
            value = displayText,
            onValueChange = {},
            label = labelString,
            enabled = isEnabled,
            hideCursor = true,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Icon",
                )
            },
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .clickable {
                    if (isEnabled)
                        expanded = !expanded
                }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            containerColor = AppTheme.colorScheme.background1Neutral,
            onDismissRequest = { expanded = false }
        ) {
            options?.forEach { option ->
                DropdownMenuItem(
                    text = {
                        BodyMediumText(
                            modifier = Modifier.padding(bottom = 6.dp),
                            text = option,
                            color = AppTheme.colorScheme.foregroundNeutralDefault
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        isSelected = true
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchSpinner(
    modifier: Modifier = Modifier,
    options: List<String>?,
    labelString: String,
    displayText: String,
    isEnabled: Boolean = false,
    onSearchValue: (String) -> Unit = {},
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val filteredOptions = if (searchQuery.isNotEmpty() && searchQuery.length >= 2) {
        options?.filter { it.contains(searchQuery, ignoreCase = true) } ?: emptyList()
    } else {
        options
    }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (isEnabled)
                expanded = it
        }
    ) {
        AppBaseTextField(
            value = displayText,
            onValueChange = {
                searchQuery = it
                onSearchValue(it)
                expanded = true
            },
            label = labelString,
            enabled = isEnabled,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Icon",
                )
            },
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .clickable {
                    if (isEnabled)
                        expanded = !expanded
                }
        )

        ExposedDropdownMenu(
            modifier = Modifier.height(320.dp),
            expanded = expanded,
            containerColor = AppTheme.colorScheme.background1Neutral,
            onDismissRequest = { expanded = false }
        ) {
            filteredOptions?.forEach { option ->
                DropdownMenuItem(
                    text = {
                        BodyMediumText(
                            modifier = Modifier.padding(bottom = 6.dp),
                            text = option,
                            color = AppTheme.colorScheme.foregroundNeutralDefault
                        )
                    },
                    onClick = {
                        onSearchValue(option)
                        onOptionSelected(option)
                        isSelected = true
                        expanded = false
                    }
                )
            }
        }
    }
}