package com.accenture.composegenerics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.accenture.composegenerics.ui.theme.ComposeGenericsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeGenericsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    val viewModel = viewModel<SearchViewModel>()
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var countryList = viewModel.getSearchResults()

    DockedSearchBar(
        modifier = Modifier
            .wrapContentHeight(),
        query = query ?: "",
        onQueryChange = {
            query = it
        },
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text(text = "Search Country") },
        trailingIcon = {
            if (!query.isNullOrEmpty()) {
                IconButton(onClick = {
                    query = ""
                    active = false
                }) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            } else {
                IconButton(onClick = { /*TODO*/ }) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        },
        shape = RoundedCornerShape(0.dp),
        colors = SearchBarDefaults.colors(containerColor = Color.Transparent, dividerColor = Color.LightGray)
    ) {
        var isEmpty by remember { mutableStateOf(false) }
        if (!query.isNullOrEmpty()) {

//            val p5 = filterCountry(countryList,query,Country::name)


            val (p1, p2) = countryList.partition { it.name.startsWith(query, true) }
            val p3 = p2.let { it.filter { country -> country.name.contains(query, true) } }
            if (p1.isEmpty() && p3.isEmpty() && active) {
                isEmpty = !isEmpty
            }
            AnimatedVisibility(visible = isEmpty) {
                Text(
                    text = "No elements found",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            LazyColumn {
                items(p1.plus(p3)) { item ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                active = false
                                query = item.name
                            },
                        text = item.name
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeGenericsTheme {
        Greeting()
    }
}

// Ref Links
//https://stackoverflow.com/questions/74881817/kotlin-generic-object-sort-how-would-i-sort-by-an-object-list-parameters-count

