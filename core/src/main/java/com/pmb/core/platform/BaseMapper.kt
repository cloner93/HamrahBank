package com.pmb.core.platform

interface BaseMapper<DATA : DataModel, DOMAIN : DomainModel> {
    fun map(dataModel: DATA): DOMAIN
}

interface DataModel

interface DomainModel