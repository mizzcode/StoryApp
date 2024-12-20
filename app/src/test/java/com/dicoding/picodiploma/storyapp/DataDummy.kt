package com.dicoding.picodiploma.storyapp

import com.dicoding.picodiploma.storyapp.data.local.database.ListStoryItem
import java.util.Random
import java.util.UUID

object DataDummy {

    fun generateDummyStoryResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 1..100) {
            val story = ListStoryItem(
                photoUrl = "https://picsum.photos/200/300?random=$i",
                createdAt = "2024-12-${i % 31 + 1}T00:00:00Z",
                name = "Story $i",
                description = "Description Story $i",
                lon = (100.0..140.0).random(),
                id = UUID.randomUUID().toString(),
                lat = (-10.0..10.0).random()
            )
            items.add(story)
        }
        return items
    }

    private fun ClosedRange<Double>.random() =
        Random().nextDouble() * (endInclusive - start) + start
}

