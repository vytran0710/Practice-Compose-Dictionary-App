package com.example.mypraticecomposedictionary.controller

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mypraticecomposedictionary.R
import com.example.mypraticecomposedictionary.component.SearchResultList
import com.example.mypraticecomposedictionary.component.SearchTextField
import com.example.mypraticecomposedictionary.component.ViewWord
import com.example.mypraticecomposedictionary.model.Data
import com.example.mypraticecomposedictionary.viewmodel.MainViewModel
import com.google.gson.Gson

@Composable
fun MainController(modifier: Modifier, mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(modifier = modifier, navController = navController, startDestination = "search") {
        composable("search",
            arguments = listOf(navArgument("dataJson") { type = NavType.StringType })
        ) {
            val componentPadding = dimensionResource(R.dimen.component_padding)
            Column {
                SearchTextField(modifier = Modifier
                    .padding(
                        componentPadding, componentPadding, componentPadding, 0.dp
                    )
                    .fillMaxWidth(1F),
                    onTextChange = { },
                    onSearch = { mainViewModel.searchWord(it) })
                SearchResultList(modifier = Modifier
                    .padding(
                        componentPadding, 0.dp, componentPadding, 0.dp
                    )
                    .fillMaxSize(1F), mainViewModel = mainViewModel
                ) {
                    val json = Uri.encode(it)
                    navController.navigate("view/$json") }
            }
        }
        composable("view/{dataJson}") { backStackEntry ->
            val data = Gson().fromJson(backStackEntry.arguments?.getString("dataJson"), Data::class.java)
            ViewWord(modifier = modifier, data = data)
        }
    }
}