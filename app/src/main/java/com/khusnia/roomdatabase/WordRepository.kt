package com.khusnia.roomdatabase



import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import android.os.AsyncTask



class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    fun insert(word: Word) {
        wordDao.insert(word)
    }




    fun deleteAll() {
        deleteAllWordsAsyncTask(wordDao).execute()
    }

    private class deleteAllWordsAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            mAsyncTaskDao.deleteAll()
            return null
        }
    }



    fun deleteWord(word: Word) {
        deleteWordAsyncTask(wordDao).execute(word)
    }

    private class deleteWordAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) :
        AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.deleteWord(params[0])
            return null
        }
    }



    fun update(word: Word) {
        updateWordAsyncTask(wordDao).execute(word)
    }

    private class updateWordAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) :
        AsyncTask<Word, Void, Void>() {
        override fun doInBackground(vararg params: Word?): Void? {
            mAsyncTaskDao.update(params[0]!!)
            return null
        }
    }
}