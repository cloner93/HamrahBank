package com.pmb.profile.presentaion.personal_infos.change_education.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.EducationEntity

data class ChangeEducationViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val educationEntity: EducationEntity = EducationEntity.createEmpty(),
    val educationEntities: List<EducationEntity> = emptyList(),
    val enableButton: Boolean = false
) : BaseViewState
