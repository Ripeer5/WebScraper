package com.example.webscraper

import android.icu.util.ValueIterator
import org.jsoup.Jsoup
import org.jsoup.nodes.*

val url =
    "https://www.seloger.com/list.htm?projects=2,5&types=2,1&natures=1,2,4&places=[{%22inseeCodes%22:[920044]}]&price=NaN/150000&mandatorycommodities=0&enterprise=0&qsVersion=1.0&m=search_refine-redirection-search_results"

fun main() {
    val doc = Jsoup.connect(
        url
    )
        .get()

    val list = mutableListOf<Appart>()

    doc.select(
        "div.Page__WrapMain-st6q56-3.dHrhZe"
    ).select("div[data-test=sl.card-container]").forEach {
        val description = it.select("ul.ContentZone__TagsLine-wghbmy-6.fCXpjq").text()
        val price = it.select("div.Price__Label-sc-1g9fitq-2.jtuVxc").text()
        val lieu = it.select("div.ContentZone__Address-wghbmy-0.bZvSwz").text()
        val appart = Appart(price, description, lieu)
        if (appart.prix.isNotBlank()) {
            list.add(appart)
            println("${appart} \n")
        }

    }

    /*val appartsSize = apparts.size



    for (i in 0 until appartsSize) {
        val description = apparts.select("ul.ContentZone__TagsLine-wghbmy-6.fCXpjq").text()
        val price = apparts.select("div.Price__Label-sc-1g9fitq-2.jtuVxc").text()
        val lieu = apparts.select("div.ContentZone__Address-wghbmy-0.bZvSwz").text()
        val appart = Appart(price, description, lieu)

        list.add(appart)
    }*/
    println(list)
    println(list.size)
}