package com.pmb.auth.domain.register.job_information.entity

data class JobInformationEntity(
    val isSuccess:Boolean,
    val annualIncomingPrediction: List<AnnualIncomingPrediction>
)
data class AnnualIncomingPrediction(
    val id: Int,
    val income: String
)

data class JobInformationParam(
    val annualIncomeId:Int,
    val jobId:Int
)