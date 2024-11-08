package com.example.retrofitexample.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.retrofit.model.MyPost
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _post = MutableLiveData<MyPost>()
    val post: LiveData<MyPost>
        get() = _post

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _hasError = MutableLiveData<Boolean>()
    val hasError: LiveData<Boolean>
        get() = _hasError

    init {
        fetchPost()
    }

    fun fetchPost() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)
        val response =  RetrofitInstance.api.fetchPost()
            if (response.isSuccessful) {
                response.body()?.let { post ->
                    _post.value = post
                    _hasError.value = false
                }?:run {
                    _hasError.value = true
                }
            } else {
                _hasError.value = true

            }
            _isLoading.value = false
        }
    }
}