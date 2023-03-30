package com.example.themoviedatabase.base.exception

class APIException : BaseException {
    companion object {
        const val API_FAIL = 2002
        const val BODY_NULL = 710
    }

    constructor(code: Int) : super(code)

    constructor(message: String?) : super(message)

    constructor(code: Int, message: String?) : super(code, message)

    constructor(code: Int, message: String?, throwable: Throwable?) : super(code, message, throwable)

    constructor(code: Int, message: String?, throwable: Throwable?, payload: Any?) : super(code, message, throwable, payload)
}
