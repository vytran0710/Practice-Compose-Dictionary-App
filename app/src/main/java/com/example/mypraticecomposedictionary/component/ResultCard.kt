package com.example.mypraticecomposedictionary.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypraticecomposedictionary.R
import com.example.mypraticecomposedictionary.model.Data

@Composable
fun ResultCard(modifier: Modifier, data: Data) {
    Box(modifier = Modifier.border(BorderStroke(2.dp, color = Color.Black))) {
        val elementPadding = dimensionResource(R.dimen.element_padding)
        Column(
            modifier = modifier.padding(
                elementPadding
            )
        ) {
            Row {
                val subheadSize = dimensionResource(R.dimen.subhead_size).value.sp
                Text(
                    text = data.slug,
                    fontWeight = FontWeight.Bold,
                    fontSize = subheadSize,
                    modifier = Modifier.weight(1F)
                )
                Column(modifier = Modifier.weight(1F)) {
                    if (data.isCommon) {
                        val isCommonString = stringResource(R.string.common_word)
                        Text(text = isCommonString)
                    }
                    for (jlpt in data.jlpt) {
                        Text(text = jlpt)
                    }
                }
            }
            val definitionString = stringResource(R.string.definitions_)
            Text(text = definitionString)
            for (sense in data.senses)
            {
                Text(text = "\t-\t${sense.englishDefinitions.joinToString(", ")} (${sense.partsOfSpeech.joinToString(", ")})")
            }
            val formString = stringResource(R.string.forms_)
            Text(text = "$formString ${data.japanese.joinToString(", ") { japanese -> "${japanese.word} (${japanese.reading})" }}")
        }
    }
}