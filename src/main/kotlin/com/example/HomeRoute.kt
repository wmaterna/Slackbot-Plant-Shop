package com.example

import com.example.data.model.Product
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val categories = listOf(
    "Rośliny kolekcjonerskie", "Sukulenty i kaktusy", "Alokazje", "Doniczki i osłonki"
)

private val collection_plants = listOf(
    Product("Anthurium clarinervium", "Rośliny kolekcjonerskie"),
    Product("Calathea white fusion", "Rośliny kolekcjonerskie"),
    Product("Philodendron squamiferum", "Rośliny kolekcjonerskie"),
)
private val sukulents = listOf(
    Product("Ceropegia woodii", "Sukulenty i kaktusy"),
    Product("Sansevieria moonshine", "Sukulenty i kaktusy"),
    Product("Sansevieria superba", "Sukulenty i kaktusy"),
)
private val alocasias = listOf(
    Product("Alokazja black velvet", "Alokazje"),
    Product("Alokazja dragon scale", "Alokazje"),
    Product("Alokazja Zebrina", "Alokazje"),
)
private val pots = listOf(
    Product("Ceramiczna", "Doniczki i osłonki"),
    Product("Plastikowa", "Doniczki i osłonki"),
    Product("Metalowa", "Doniczki i osłonki"),
)

private val products = mapOf(categories[0] to collection_plants, categories[1] to sukulents, categories[2] to alocasias, categories[2] to pots)

fun getCategoryDetails(category: String) : String? {
    var response : String = ""
    products[category]?.forEach {
        response = response + "\n" + it.name
    }
    if(response == "") {
        response = "Brak produktów z wybranej kategorii"
    }
    return response
}

fun Routing.homeRoute() {
    post("/categories") {
        val separator = "\n"
        val response = categories.joinToString(separator)
        call.respondText("List of categories: \n $response")
    }
    post("/products") {
        val formParameters = call.receiveParameters()
        println(formParameters["text"])
        formParameters["text"]?.let { it1 -> getCategoryDetails(it1) }?.let { it2 -> call.respondText(it2) }
    }
}

