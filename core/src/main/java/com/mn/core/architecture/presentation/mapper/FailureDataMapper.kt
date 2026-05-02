package com.mn.core.architecture.presentation.mapper

import com.mn.core.R
import com.mn.core.architecture.domain.mapper.Mapper
import com.mn.core.architecture.domain.models.FailureResult
import com.mn.core.architecture.presentation.models.FailureData

class FailureDataMapper : Mapper<FailureResult, FailureData> {
    override fun invoke(from: FailureResult): FailureData = when (from) {
        FailureResult.NoDataFound -> {
            FailureData(R.string.error_no_data_title, R.string.error_no_data_message)
        }

        FailureResult.UnknownError -> {
            FailureData(R.string.error_unknown_title, R.string.error_unknown_message)
        }

        FailureResult.NoInternet -> {
            FailureData(R.string.error_no_internet_title, R.string.error_no_internet_message)
        }

        is FailureResult.ServerError -> {
            if (from.code.isNullOrEmpty()) {
                FailureData(R.string.error_server_title, R.string.error_server_message)
            } else {
                FailureData(
                    R.string.error_server_title,
                    R.string.error_server_message,
                    from.code,
                    from.code
                )
            }

        }
    }
}