package com.example.mypraticecomposedictionary.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.example.mypraticecomposedictionary.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(modifier: Modifier, onTextChange: (String) -> Unit, onSearch: (String) -> Unit) {
    val (textFieldValue, setTextFieldValue) = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val placeHolderText = stringResource(R.string.shiken)
    val labelText = stringResource(id = R.string.search_words)
    TextField(
        modifier = modifier,
        maxLines = 1,
        value = textFieldValue,
        label = { Text(text = labelText) },
        placeholder = { Text(text = placeHolderText, color = Color.LightGray) },
        onValueChange = { value ->
            setTextFieldValue(value)
            onTextChange.invoke(value.text)
        },
        keyboardActions = KeyboardActions(
            onSearch = {
                if (textFieldValue.text.trim().isNotBlank())
                {
                    onSearch.invoke(textFieldValue.text)
                    keyboardController?.hide()
                }
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        )
    )
}