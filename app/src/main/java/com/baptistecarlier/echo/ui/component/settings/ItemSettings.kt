package com.baptistecarlier.echo.ui.component.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.ui.theme.EchoTheme

@Composable
fun ColumnScope.ItemSettings(
    @StringRes overlineLabel: Int,
    @StringRes firstItemLabel: Int,
    firstItemValue: String,
    @StringRes secondItemLabel: Int,
    secondItemValue: String,
    update: (first: String, second: String) -> Unit
) {

    var firstItemState by rememberSaveable { mutableStateOf(firstItemValue) }
    var firstItemVisible by rememberSaveable { mutableStateOf(false) }
    var secondItemState by rememberSaveable { mutableStateOf(secondItemValue) }
    var secondItemVisible by rememberSaveable { mutableStateOf(false) }

    Text(
        style = MaterialTheme.typography.subtitle1,
        text = stringResource(overlineLabel)
    )
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(firstItemLabel)) },
        visualTransformation = if (firstItemVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (firstItemVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            IconButton(onClick = { firstItemVisible = !firstItemVisible }) {
                Icon(imageVector = image, null)
            }
        },
        value = firstItemState,
        onValueChange = { firstItemState = it }
    )
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(secondItemLabel)) },
        visualTransformation = if (secondItemVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (secondItemVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            IconButton(onClick = { secondItemVisible = !secondItemVisible }) {
                Icon(imageVector = image, null)
            }
        },
        value = secondItemState,
        onValueChange = { secondItemState = it }
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.onBackground),
            onClick = {
                firstItemState = firstItemValue
                secondItemState = secondItemValue
            }
        ) {
            Text(stringResource(id = R.string.reset))
        }
        Spacer(Modifier.padding(8.dp))
        Button(onClick = { update(firstItemState, secondItemState) }) {
            Text(stringResource(id = R.string.save))
        }
    }
}

@Preview
@Composable
fun ItemSettingsPreview() {
    EchoTheme {
        Column {
            ItemSettings(
                R.string.settings_overline_linkedin,
                R.string.settings_linkedin_accessid,
                "a",
                R.string.settings_linkedin_linkedinid,
                "b",
            ) { _, _ -> }
        }
    }
}