package com.taehee.yuencard.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Word
import com.taehee.domain.usecase.word.AddWordUseCase
import com.taehee.domain.usecase.word.GetWordsUseCase
import com.taehee.domain.usecase.word.RemoveWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    getWordUseCase: GetWordsUseCase,
    private val addWordUseCase: AddWordUseCase,
    private val removeWordUseCase: RemoveWordUseCase
) : ViewModel() {

    val words: LiveData<List<Word>> = getWordUseCase().asLiveData()

    fun addWord(text: String) {
        if (text.isNotEmpty())
            addWordUseCase(Word(text, System.currentTimeMillis(), null), viewModelScope)
    }

    fun deleteWord(word: Word) {
        removeWordUseCase(word, viewModelScope)
    }
}