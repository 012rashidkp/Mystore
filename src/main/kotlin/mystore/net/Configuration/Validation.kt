package mystore.net.Configuration

import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import mystore.net.Requests.CreateCategoryparams

fun RequestValidationConfig.racketValidation() {
    validate<CreateCategoryparams> { items ->
        if (items.categoryName.isBlank() || items.categoryName.length < 2) {
            ValidationResult.Invalid("Category must be at least 3 characters long")
        } else if (items.categoryImage!!.isBlank() || items.categoryImage.isEmpty()) {
            ValidationResult.Invalid("Category Image must not blank")

        } else {
            // Everything is ok!
            ValidationResult.Valid
        }
    }
}