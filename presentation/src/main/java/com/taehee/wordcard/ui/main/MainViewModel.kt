package com.taehee.wordcard.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import com.taehee.domain.usecase.tts.StopTtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val speakTtsUseCase: SpeakTtsUseCase,
    private val stopTtsUseCase: StopTtsUseCase,
) : ViewModel() {

    private val _completeInit = MutableLiveData<Boolean>()
    val completeInit: LiveData<Boolean> get() = _completeInit

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun wordChanged() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(needRefreshCard = true, needRefreshGame = true)
            }
        }
    }

    fun cardRefreshFinished() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(needRefreshCard = false)
            }
        }
    }

    fun gameRefreshFinished() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(needRefreshGame = false)
            }
        }
    }

    init {
        _completeInit.value = false
        viewModelScope.launch {
            delay(2000)
            _completeInit.value = true
        }
    }

    fun speak(text: String) {
        speakTtsUseCase(text)
    }

    override fun onCleared() {
        stopTtsUseCase()
        super.onCleared()
    }
}