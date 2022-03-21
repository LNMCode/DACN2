package com.longnp.lnsocial.presentation.main.seeds.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longnp.lnsocial.business.interactors.video.SearchVideoSeeds
import com.longnp.lnsocial.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SeedViewModel
@Inject
constructor(
    private val sessionManager: SessionManager,
    private val searchVideoSeeds: SearchVideoSeeds,

) : ViewModel(){

    private val TAG: String = "AppDebug"

    val state: MutableLiveData<SeedState> = MutableLiveData(SeedState())

    init {
        onTriggerEvent(SeedEvents.NewSearch)
    }

    fun onTriggerEvent(event: SeedEvents) {
        when (event) {
            is SeedEvents.NewSearch -> {
                search()
            }
        }
    }

    private fun search() {
        //resetPage()
        //clearList()
        state.value?.let { state ->
            val params = JSONObject()
            params.put("userid", "ukXlOlDZ0QD12YsSF2Xc")
            params.put("access_token", "l0w0sjek41.21235317272802")
            val body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                params.toString()
            )
            searchVideoSeeds.execute(
                body = body
            ).onEach { dataState ->
                this.state.value = state.copy(isLoading = dataState.isLoading)

                dataState.data?.let { list ->
                    this.state.value = state.copy(videoSeedList = list)
                }

                dataState.stateMessage?.let { stateMessage ->
                    if(stateMessage.response.message?.contains("ErrorHandling.INVALID_PAGE") == true){
                        //onUpdateQueryExhausted(true)
                    }else{
                        //appendToMessageQueue(stateMessage)
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
}