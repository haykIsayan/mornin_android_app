package com.example.project_mornin.features.mornin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.entity.MorninEntity
import com.example.project_mornin.domain.usecase.GetMorninFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias MorninUiState = UiState<List<MorninEntity>>

@HiltViewModel
class MorninViewModel @Inject constructor(
    private val getMorninFeedUseCase: GetMorninFeedUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<MorninUiState>(UiState.Loading)
    val uiState: StateFlow<MorninUiState> = _uiState

    init {
        fetchMorninFeed()
    }

    fun fetchMorninFeed(interests: InterestsEntity? = null) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val data = getMorninFeedUseCase.execute(interests)
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}