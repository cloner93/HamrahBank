package com.pmb.domain.usecae.auth

import com.pmb.core.platform.ThemeMode
import com.pmb.domain.repository.auth.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repo: LoginRepository) {
    operator fun invoke(): Flow<ThemeMode> = flow {}
}