package com.mn.core.architecture.presentation.models

import androidx.annotation.StringRes

data class FailureData(
    @StringRes val title: Int,
    @StringRes val message: Int,
    val titleString: String? = null,
    val titleMessage: String? = null
)