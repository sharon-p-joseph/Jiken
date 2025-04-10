package com.sharon.jiken.features.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharon.jiken.features.main.domain.usecase.repo.AnimeRepoUseCases
import com.sharon.jiken.features.main.presentation.activity.state.AnimeListState
import com.sharon.jiken.features.main.presentation.activity.state.AnimeListStatus
import com.sharon.jiken.features.main.presentation.intents.AnimeListIntents
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class AnimeViewModel @Inject constructor(private val animeRepoUseCases: AnimeRepoUseCases) :
    ViewModel() {
    private val _state = MutableLiveData(AnimeListState())
    val state:LiveData<AnimeListState> = _state



    fun onIntent(intent: AnimeListIntents) {
        when (intent) {
            is AnimeListIntents.GetAnimeList -> {
                _state.value = _state.value?.copy(
                    status = AnimeListStatus.LOADING,
                )
                viewModelScope.launch {
                    animeRepoUseCases.getAnimeList()?.let {

                        if (it.data.isNotEmpty()) {
                            _state.value = _state.value?.copy(
                                status = AnimeListStatus.SUCCESS,
                                animeList = it.data,
                            )
                        } else {
                            _state.value = _state.value?.copy(
                                status = AnimeListStatus.ERROR,
                                animeList = emptyList(),
                                errorMessage = it.msg ?: "Unknown Error"
                            )
                        }
                    }
                }


            }
        }
    }


}