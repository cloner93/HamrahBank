package com.pmb.auth.data.login

import com.pmb.auth.domain.login.entity.UserEntity
import com.pmb.auth.domain.login.repository.LoginRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(

) : LoginRepository {
    override suspend fun login(username: String, password: String): Flow<Result<UserEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        //call api to login and save to database , then we use mapper to convert to user entity
        //for now we use mock data
        if (username == "admin" && password == "admin") {
            emit(Result.Success(UserEntity(username=username)))
        }else{
            emit(Result.Error(message = "wrong username and password"))
        }
    }


}