package com.example.project_mornin.features.mornin.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.entity.MorninTopic
import com.example.project_mornin.domain.usecase.GetInterestsUseCase
import com.example.project_mornin.domain.usecase.GetMorninTopicsUseCase
import com.example.project_mornin.domain.usecase.UpdateInterestsUseCase
import com.example.project_mornin.features.mornin.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias InterestsUiState = UiState<InterestsUiData>

data class InterestsUiData(
    val interests: InterestsEntity,
    val availableTopics: List<MorninTopic> = emptyList()
)

@HiltViewModel
class InterestsViewModel @Inject constructor(
    private val getMorninTopicsUseCase: GetMorninTopicsUseCase,
    private val getInterestsUseCase: GetInterestsUseCase,
    private val updateInterestsUseCase: UpdateInterestsUseCase
): ViewModel() {

    private val state = MutableStateFlow<InterestsUiState>(UiState.Loading)

    fun getState(): StateFlow<InterestsUiState> = state

    init {
        loadInterestsUiState()
    }

    fun updateInterests(topic: MorninTopic) {
        val currentInterestsUiData = (state.value as? UiState.Success)?.data ?: return
        val currentInterests = currentInterestsUiData.interests
        val updatedInterests = updateInterests(currentInterests, topic)
        state.value = UiState.Success(currentInterestsUiData.copy(interests = updatedInterests))
        try {
            viewModelScope.launch {
                val success = updateInterestsUseCase.execute(updatedInterests)
                if (!success) {
                    state.value = UiState.Error("Failed to update interests")
                }
            }
        } catch (e: Exception) {
            state.value = UiState.Error(e.message ?: "An error occurred while updating interests")
        }
    }

    private fun updateInterests(
        currentInterests: InterestsEntity,
        topic: MorninTopic
    ): InterestsEntity =  if (currentInterests.topics.contains(topic)) {
        currentInterests.withTopics(currentInterests.topics - topic)
    } else {
        currentInterests.withTopics(currentInterests.topics + topic)
    }

    private fun addTopicToInterests(topic: MorninTopic) {
        val currentInterestsUiData = (state.value as? UiState.Success)?.data ?: return
        val currentInterests = currentInterestsUiData.interests

    }

    private fun loadInterestsUiState() {
        state.value = UiState.Loading
        try {
            viewModelScope.launch {
                val interests = getInterestsUseCase.execute()
                val topics = getMorninTopicsUseCase.execute()
                val uiData = InterestsUiData(interests, topics)
                state.value = UiState.Success(uiData)
            }
        } catch (e: Exception) {
            state.value = UiState.Error(e.message ?: "An error occurred while loading interests")
        }
    }
}