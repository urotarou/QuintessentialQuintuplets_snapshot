package lab.uro.kitori.quintessentialquintuplets.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> MediatorLiveData<T>.attach(source: LiveData<T>) {
    addSource(source) { postValue(it) }
}
