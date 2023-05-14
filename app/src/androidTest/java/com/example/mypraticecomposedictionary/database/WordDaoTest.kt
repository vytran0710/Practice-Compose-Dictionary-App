package com.example.mypraticecomposedictionary.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mypraticecomposedictionary.model.Attribution
import com.example.mypraticecomposedictionary.model.Data
import com.example.mypraticecomposedictionary.model.Japanese
import com.example.mypraticecomposedictionary.model.Links
import com.example.mypraticecomposedictionary.model.Meta
import com.example.mypraticecomposedictionary.model.Senses
import com.example.mypraticecomposedictionary.model.Word
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@kotlinx.coroutines.ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class WordDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: WordDatabase
    private lateinit var dao: WordDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WordDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.wordDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCachedWord() = runTest {
        val meta = Meta(
            200
        )
        val attribution = Attribution(
            dbpedia = "test.dp",
            jmdict = false,
            jmnedict = false
        )
        val japanese = mutableListOf<Japanese>()
        val senses = mutableListOf<Senses>(
            Senses(
                englishDefinitions = arrayListOf("33333"),
                partsOfSpeech = arrayListOf("44444"),
                links = arrayListOf(
                    Links(
                        text = "an",
                        url = "https://www.google.com/#q=metus"
                    )
                ),
                tags = arrayListOf("323123"),
                restrictions = arrayListOf("444444"),
                seeAlso = arrayListOf("77777"),
                antonyms = arrayListOf("77777"),
                source = arrayListOf("3434gghgh"),
                info = arrayListOf("pdowdp")
            )
        )
        val tags = arrayListOf(
            "test33"
        )
        val data = mutableListOf(
            Data(
                attribution = attribution,
                isCommon = true,
                japanese = japanese,
                jlpt = mutableListOf("n1", "n3"),
                senses = senses,
                slug = "test11",
                tags = tags
            )
        )
        val word = Word(null, meta, data, false)
        dao.insertCachedWord(word)

        assertThat(dao.getAllWords().find { it.data.isNotEmpty() && it.data.first().slug == data.first().slug } != null).isTrue()
    }
}