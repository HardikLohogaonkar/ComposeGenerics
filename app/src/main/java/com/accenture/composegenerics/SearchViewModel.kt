package com.accenture.composegenerics

import androidx.lifecycle.ViewModel


class SearchViewModel : ViewModel() {

    val countryList = listOf(
        Country(
            name = "United States", id = "US", displayName = "", url = ""
        ),
        Country(
            name = "China", id = "CH", url = "", displayName = "",
        ),
        Country(
            name = "India", id = "IN", url = "", displayName = "",
        ),
        Country(
            name = "Brazil", id = "BRZ", url = "", displayName = "",
        ),
        Country(
            name = "Indonesia", id = "IND", url = "", displayName = "",
        ),
        Country(
            name = "Nigeria", id = "NIG", url = "", displayName = "",
        ),
        Country(
            name = "Germany", id = "GER", url = "", displayName = "",
        ),
        Country(
            name = "Thailand", id = "TH", url = "", displayName = "",
        ),
        Country(
            name = "Japan", id = "JPY", url = "", displayName = "",
        ),
        Country(
            name = "Qatar", id = "QTR", url = "", displayName = "",
        ),
    )

    fun getSearchResults(): List<Country> {
        return countryList
    }
}

data class Country(
    val id: String,
    val name: String,
    val url: String,
    val displayName: String,
)
