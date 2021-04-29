package com.pratclot.kmm1.android.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratclot.kmm1.SafeStorage
import com.pratclot.kmm1.android.di.JwtInterceptor
import com.pratclot.kmm1.android.repo.RepoAbstraction
import com.pratclot.kmm1.data.Temps
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    var jwtInterceptor: JwtInterceptor,
    private val repoAbstraction: RepoAbstraction
) : ViewModel() {

    companion object {
        const val TAG = "SlideshowViewModel"
        const val tickerInterval = 1_000L
    }

    private val _temps = MutableLiveData<Temps>()
    val temps: LiveData<Temps> = _temps

    private var _lastUpdate = MutableLiveData<Int>()
    val lastUpdate: LiveData<Int> = _lastUpdate

    fun connect() {
        readJwtToken()
        initTicker()
        viewModelScope.launch(Dispatchers.IO) {
            repoAbstraction.readTemperatures(::updateTemps)
        }
    }

    private fun readJwtToken() {
        SafeStorage.getJwtToken()?.let {
            jwtInterceptor.token = it
        }
    }

    private fun updateTemps(obj: Temps) {
        _lastUpdate.postValue(0)
        _temps.postValue(obj)
    }

    private fun initTicker() {
        viewModelScope.launch(Dispatchers.IO) {
            val tickerChannel = ticker(tickerInterval)
            tickerChannel.consumeEach {
                _lastUpdate.apply {
                    postValue(value?.plus(tickerInterval / 1_000)?.toInt())
                }
            }
        }
    }
}
