package com.newsappkotlin.dtos

data class CountryDto(val countryName: String, val countryCode: String)

public fun provideCountryList(): ArrayList<CountryDto> {
    var arrayList = ArrayList<CountryDto>()
    arrayList.add(CountryDto("Australia", "au"))
    arrayList.add(CountryDto("Canada", "ca"))
    arrayList.add(CountryDto("China", "cn"))
    arrayList.add(CountryDto("France", "fr"))
    arrayList.add(CountryDto("Germany", "de"))
    arrayList.add(CountryDto("India", "in"))
    arrayList.add(CountryDto("Italy", "it"))
    arrayList.add(CountryDto("Japan", "jp"))
    arrayList.add(CountryDto("New Zealand", "nz"))
    arrayList.add(CountryDto("Russia", "ru"))
    arrayList.add(CountryDto("Saudi Arabia", "sa"))
    arrayList.add(CountryDto("South Africa", "za"))
    arrayList.add(CountryDto("UAE", "ae"))
    arrayList.add(CountryDto("United Kingdom", "uk"))
    arrayList.add(CountryDto("United States", "us"))

    return arrayList
}

