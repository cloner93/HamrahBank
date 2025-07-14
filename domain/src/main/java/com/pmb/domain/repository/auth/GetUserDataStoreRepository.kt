package com.pmb.domain.repository.auth

import com.pmb.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import com.pmb.core.platform.Result
interface GetUserDataStoreRepository {
  suspend  fun getUserDataStore(): Flow<Result<UserData?>>
}