package com.example.mypraticecomposedictionary.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mypraticecomposedictionary.R
import com.example.mypraticecomposedictionary.model.UiState
import com.example.mypraticecomposedictionary.viewmodel.MainViewModel
import com.google.gson.Gson

@Composable
fun SearchResultList(
    modifier: Modifier,
    mainViewModel: MainViewModel,
    onNavigateToView: (String) -> Unit
) {
    val elementPadding = dimensionResource(R.dimen.element_padding)
    val currentState = mainViewModel.searchState.collectAsState()
    if (currentState.value.currentState == UiState.Loading) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val searchingString = stringResource(R.string.searching_)
            Text(text = searchingString)
            CircularProgressIndicator()
        }
    } else if (currentState.value.currentState == UiState.Success) {
        LazyColumn(modifier = modifier)
        {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .padding(top = elementPadding)
                )
            }
            items(currentState.value.data?.data ?: return@LazyColumn)
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .padding(top = elementPadding)
                )
                ResultCard(
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .clickable { onNavigateToView.invoke(Gson().toJson(it)) }, data = it
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .padding(top = elementPadding)
                )
            }
        }
    }
}