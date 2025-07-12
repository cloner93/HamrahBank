package com.pmb.home.data.repository

import com.pmb.core.platform.Result
import com.pmb.home.R
import com.pmb.home.domain.home.entity.HomeEntity
import com.pmb.home.domain.home.entity.HomeItemType
import com.pmb.home.domain.home.entity.HomeItems
import com.pmb.home.domain.home.repository.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getHomeData(): Flow<Result<HomeEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(
                HomeEntity(
                    isSuccess = true,
                    sliderImage = listOf(
                        R.drawable.img_slider,
                        R.drawable.img_slider,
                        R.drawable.img_slider,
                        R.drawable.img_slider,
                    ),
                    homeItems = listOf(
                        HomeItems(
                            title = "وام و اقساط",
                            id = 0,
                            img = R.drawable.img_loan,
                            type = HomeItemType.LOAN

                        ),
                        HomeItems(
                            title = "شارژ",
                            id = 1,
                            img = R.drawable.img_charge,
                            type = HomeItemType.CHARGE
                        ),
                        HomeItems(
                            title = "قبض",
                            id = 2,
                            img = R.drawable.img_bill,
                            type = HomeItemType.BILL
                        ),
                        HomeItems(
                            title = "اینترنت",
                            id = 3,
                            img = R.drawable.img_internet,
                            type = HomeItemType.INTERNET
                        ),
                        HomeItems(
                            title = "چک",
                            id = 4,
                            img = R.drawable.img_cheque,
                            type = HomeItemType.INTERNET
                        ),
                        HomeItems(
                            title = "شعب بانک",
                            id = 5,
                            img = R.drawable.img_branches,
                            type = HomeItemType.INTERNET
                        ),
                        HomeItems(
                            title = "جشنواره",
                            id = 5,
                            img = R.drawable.img_festival,
                            type = HomeItemType.INTERNET
                        ),
                        HomeItems(
                            title = "خیریه",
                            id = 5,
                            img = R.drawable.img_charity,
                            type = HomeItemType.INTERNET
                        ),
                    )
                )
            )
        )
    }
}