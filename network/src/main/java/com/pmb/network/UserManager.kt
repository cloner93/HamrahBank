package com.pmb.network

import javax.inject.Inject

@Deprecated("This method is temporary. handle this in real way")
class UserSessionImpl @Inject constructor() {

    lateinit var sessionData: SessionData
    fun get(): SessionData? {
        return sessionData
    }

    fun connect(sessionData: SessionData) {
        this.sessionData = sessionData
    }

    fun clear() {
        this.sessionData.accessToken = ""
        this.sessionData.refreshToken = ""
    }
}