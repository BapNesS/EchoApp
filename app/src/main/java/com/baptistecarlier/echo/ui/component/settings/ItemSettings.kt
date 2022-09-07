package com.baptistecarlier.echo.ui.component.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

    var firstItemState by remember { mutableStateOf(firstItemValue) }
    var secondItemState by remember { mutableStateOf(secondItemValue) }

    Text(
        style = MaterialTheme.typography.subtitle1,
        text = stringResource(overlineLabel)
    )
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(firstItemLabel)) },
        value = firstItemState,
        onValueChange = { firstItemState = it }
    )
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(secondItemLabel)) },
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