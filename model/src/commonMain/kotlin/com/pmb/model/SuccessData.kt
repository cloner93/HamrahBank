package com.pmb.model

data class SuccessData<T>(
    val data: T?,
    val metaData: ResponseMetaData?
)

