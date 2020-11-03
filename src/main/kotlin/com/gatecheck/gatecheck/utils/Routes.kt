package com.gatecheck.gatecheck.utils

object Routes {
    const val VERSION = 1
    const val BASE = "/api/v$VERSION"

    object User {
        const val BASE = "${Routes.BASE}/user"
        const val SINGLE_USER_SELECT = "$BASE/{userId}"

        override fun toString(): String {
            return BASE
        }
    }

    object Auth {
        const val BASE = "${Routes.BASE}/auth"
        const val LOGIN = "$BASE/login"
        const val REGISTER = "$BASE/register"

        override fun toString(): String {
            return BASE
        }
    }

    object Request {
        const val BASE = "${Routes.BASE}/request"
        const val SINGLE_REQUEST_SELECT = "$BASE/{requestId}"
        const val MESSAGE = "$SINGLE_REQUEST_SELECT/message"
        const val STATUS = "$SINGLE_REQUEST_SELECT/status/{statusType}"

        override fun toString(): String {
            return BASE
        }
    }

    object Assets {
        const val BASE = "${Routes.BASE}/assets"
        const val LANGUAGE = "$BASE/language"
        const val SINGLE_LANGUAGE_SELECT = "$LANGUAGE/{language_code}"

        override fun toString(): String {
            return BASE
        }
    }
}