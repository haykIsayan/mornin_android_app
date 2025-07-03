package com.example.project_mornin.features.mornin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_mornin.domain.entity.MorninEntity
import com.example.project_mornin.domain.usecase.GetMorninFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MorninViewModel @Inject constructor(
    private val getMorninFeed: GetMorninFeed
): ViewModel() {

    private val _uiState = MutableStateFlow<MorninUiState>(MorninUiState.Loading)
    val uiState: StateFlow<MorninUiState> = _uiState

    init {
        fetchMorninFeed()
    }

    fun fetchMorninFeed() {
        viewModelScope.launch {
            _uiState.value = MorninUiState.Loading
            try {
                val data = getMorninFeed.execute()
                _uiState.value = MorninUiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = MorninUiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class MorninUiState {
    data object Loading : MorninUiState()
    data class Success(val data: List<MorninEntity>) : MorninUiState()
    data class Error(val message: String) : MorninUiState()
}