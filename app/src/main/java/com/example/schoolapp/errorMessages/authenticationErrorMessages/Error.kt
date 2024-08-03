package com.example.schoolapp.errorMessages.authenticationErrorMessages

// Errors.kt

// Errors.kt


sealed class AppError {
    // Network Errors
    object NetworkError : AppError()
    object NoInternetConnectionError : AppError()
    object ServerUnreachableError : AppError()
    object RequestTimeoutError : AppError()
    object ConnectionTimeoutError : AppError()

    // Database Errors
    object DatabaseError : AppError()
    data class DataFormatError(val message: String) : AppError()
    data class DataIntegrityError(val message: String) : AppError()

    // Authentication Errors
    data class AuthenticationError(val message: String) : AppError()
    object UserAlreadyExistsError : AppError()
    object InvalidCredentialsError : AppError()
    object UserNotVerifiedError : AppError()

    // Validation Errors
    object InvalidEmailError : AppError()
    object InvalidPasswordError : AppError()
    data class EmptyFieldError(val fieldName: String) : AppError()

    // Permission Errors
    object UnauthorizedAccessError : AppError()
    object ForbiddenAccessError : AppError()

    // Resource Errors
    data class ResourceNotFoundError(val resourceName: String) : AppError()
    data class ResourceUnavailableError(val resourceName: String) : AppError()

    // API Errors
    data class ApiError(val statusCode: Int, val message: String) : AppError()

    // File Errors
    data class FileNotFoundError(val fileName: String) : AppError()
    data class FileReadError(val fileName: String) : AppError()
    data class FileWriteError(val fileName: String) : AppError()

    // Business Logic Errors
    object InsufficientFundsError : AppError()
    object QuotaExceededError : AppError()
    object OperationNotAllowedError : AppError()

    // General Errors
    data class UnknownError(val message: String) : AppError()
}
