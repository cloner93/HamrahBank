package com.pmb.auth.data.register.search_opening_branch

import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranch
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchEntity
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchParams
import com.pmb.auth.domain.register.search_opening_branch.repository.OpeningBranchRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OpeningBranchRepositoryImpl @Inject constructor() : OpeningBranchRepository {
    override suspend fun getOpeningBranch(params: OpeningBranchParams): Flow<Result<OpeningBranchEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            if (params.id == 0 || params.id == 1 || params.id == 2) {
                emit(
                    Result.Success(
                        OpeningBranchEntity(
                            isSuccess = true,
                            openingBranch = when (params.id) {
                                0 -> {
                                    listOf(
                                        OpeningBranch(
                                            id = 0,
                                            openingBranch = "مهرشهر، ۶۷۸۵۹",
                                            openingBranchAddress = "مهرشهر، بلوار ارم، نبش خیابان ۱۰۰ غربی",
                                        ),
                                        OpeningBranch(
                                            id = 1,
                                            openingBranch = "گلشهر، ۹۰۴۷۵",
                                            openingBranchAddress = "خیابان دکتر بهشتی، بین ۴۵ متری گلشهر و میان جاده..."
                                        ),
                                        OpeningBranch(
                                            id = 2,
                                            openingBranch = "باغستان،۳۴۵۶۷",
                                            openingBranchAddress = "گلستان دوازدهم، پلاک ۴۹۱"
                                        )
                                    )
                                }

                                1 -> {
                                    listOf(
                                        OpeningBranch(
                                            id = 0,
                                            openingBranch = "چهار باغ، ۶۷۸۵۹",
                                            openingBranchAddress = "چهارباغ، بلوار ارم، نبش خیابان ۱۰۰ غربی"
                                        ),
                                        OpeningBranch(
                                            id = 1,
                                            openingBranch = "دروازه شیراز، ۹۰۴۷۵",
                                            openingBranchAddress = "دروازه شیراز، بین ۴۵ متری گلشهر و میان جاده..."
                                        ),
                                        OpeningBranch(
                                            id = 2,
                                            openingBranch = "کوه صفه،۳۴۵۶۷",
                                            openingBranchAddress = "کوه صفه، پلاک ۴۹۱"
                                        )
                                    )
                                }

                                2 -> {
                                    listOf(
                                        OpeningBranch(
                                            id = 0,
                                            openingBranch = "قزوین، ۶۷۸۵۹",
                                            openingBranchAddress = "قزوین، بلوار ارم، نبش خیابان ۱۰۰ غربی"
                                        ),
                                        OpeningBranch(
                                            id = 1,
                                            openingBranch = "قزوین، ۹۰۴۷۵",
                                            openingBranchAddress = "قزوین، بین ۴۵ متری گلشهر و میان جاده..."
                                        ),
                                        OpeningBranch(
                                            id = 2,
                                            openingBranch = "قزوین،۳۴۵۶۷",
                                            openingBranchAddress = "قزوین، پلاک ۴۹۱"
                                        )
                                    )
                                }

                                else -> {
                                    emptyList<OpeningBranch>()
                                }
                            }
                        )
                    )
                )
            } else {
                Result.Error(
                    message = "Your choice is not valid"
                )
            }
        }
}