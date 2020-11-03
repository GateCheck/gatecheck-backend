package com.gatecheck.gatecheck.utils

object Routes {
    const val VERSION = 1
    const val BASE = "/api/v$VERSION"

    object User {
        const val BASE = "/user"
        const val SINGLE_USER_SELECT = "/{userId}"

        override fun toString(): String {
            return BASE
        }
    }

    object Auth {
        const val BASE = "/auth"
        const val LOGIN = "/login"
        const val REGISTER = "/register"

        override fun toString(): String {
            return BASE
        }
    }

    object Request {
        const val BASE = "/request"
        const val SINGLE_REQUEST_SELECT = "/{requestId}"
        const val MESSAGE = "/message"
        const val STATUS = "/status/{statusType}"

        override fun toString(): String {
            return BASE
        }
    }

    object Assets {
        const val BASE = "/assets"
        const val LANGUAGE = "/language"
        const val SINGLE_LANGUAGE_SELECT = "/{language_code}"

        override fun toString(): String {
            return BASE
        }
    }
}