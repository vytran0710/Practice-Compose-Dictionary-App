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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mypraticecomposedictionary.R
import com.example.mypraticecomposedictionary.model.Data
import com.example.mypraticecomposedictionary.model.UiState
import com.example.mypraticecomposedictionary.viewmodel.MainViewModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SearchResultList(
    modifier: Modifier,
    mainViewModel: MainViewModel,
    onNavigateToView: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val searchResult = remember {
        mutableStateListOf<Data>()
    }
    val uiState = remember {
        mutableStateOf(UiState.Idle)
    }
    LaunchedEffect(key1 = Unit)
    {
        coroutineScope.launch {
            mainViewModel.searchState.collectLatest {
                uiState.value = it.currentState
                if (it.currentState == UiState.Success)
                {
                    searchResult.clear()
                    searchResult.addAll(it.data!!.data)
                }
            }
        }
    }
    val elementPadding = dimensionResource(R.dimen.element_padding)
    if (uiState.value == UiState.Loading) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val searchingString = stringResource(R.string.searching_)
            Text(text = searchingString)
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = modifier)
        {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .padding(top = elementPadding)
                )
            }
            items(searchResult)
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