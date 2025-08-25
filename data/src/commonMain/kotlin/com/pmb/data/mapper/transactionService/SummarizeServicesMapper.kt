package com.pmb.data.mapper.transactionService

import com.pmb.domain.model.SummarizeResponse
import com.pmb.domain.model.transaztion.Summarize

fun List<SummarizeResponse>.toDomain(): List<Summarize> {
    val list = mutableListOf<Summarize>()

    this.forEach { summarizeResponse ->

        list.add(
            Summarize(
                docDesc = summarizeResponse.docDesc,
                totalAmount = summarizeResponse.totalAmount,
                transactionList = summarizeResponse.transactionList.mapToDomain()
            )
        )
    }

    return list
}