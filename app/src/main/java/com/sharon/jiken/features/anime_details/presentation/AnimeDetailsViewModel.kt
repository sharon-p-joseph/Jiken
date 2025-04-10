package com.sharon.jiken.features.anime_details.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharon.jiken.features.anime_details.domain.repo.repo.AnimeDetailsRepoUseCases
import com.sharon.jiken.features.anime_details.presentation.screens.state.AnimeListDetailsState
import com.sharon.jiken.features.anime_details.presentation.intents.AnimeDetailsIntent
import com.sharon.jiken.features.anime_details.presentation.screens.state.AnimeDetailsStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(private val animeDetailsRepoUseCases: AnimeDetailsRepoUseCases) :
    ViewModel() {
    private val _state = MutableLiveData(AnimeListDetailsState())
    val state: LiveData<AnimeListDetailsState> = _state


    @SuppressLint("MemberExtensionConflict")
    fun onIntent(intent: AnimeDetailsIntent) {
        when (intent) {
            is AnimeDetailsIntent.GetAdminDetails -> {


                viewModelScope.launch {
                    animeDetailsRepoUseCases.getAnimeDetails(intent.adminId.toString()).let {


                        Log.e("llls","Anime Details${it.data}")
                      if(it.result){
                          _state.value = _state.value?.copy(
                              status = AnimeDetailsStatus.SUCCESS,
                              animeList = it.data)
                      }
                        else{
                            _state.value = _state.value?.copy(
                                status = AnimeDetailsStatus.ERROR,
                                animeList = null)

                      }
                    }


                }
            }


        }
    }
}
