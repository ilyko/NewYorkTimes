package com.ilyko.nytimes.model

class BaseResponse<T>(
        var copyright: String,
        var num_results: Int,
        var results: List<T>,
        var status: String
)