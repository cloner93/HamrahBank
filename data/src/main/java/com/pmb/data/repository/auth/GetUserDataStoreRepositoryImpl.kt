package com.pmb.data.repository.auth

import com.pmb.data.appManager.AppManager
import com.pmb.domain.model.UserData
import com.pmb.domain.repository.auth.GetUserDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.pmb.core.platform.Result
class GetUserDataStoreRepositoryImpl @Inject constructor(
    private val appManager: AppManager
): GetUserDataStoreRepository {
    override suspend fun getUserDataStore(): Flow<Result<UserData?>> =
        flow {
       emit(Result.Success(appManager.getDataStoreService().getUserDataStore().getUserData()))
    }

}