package com.ilyko.nytimes.model.mappers

interface Mapper<OUT, IN> {
    fun map(input: IN): OUT
}