import android.annotation.SuppressLint
import com.pmb.model.ResponseMetaData
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MobileApiResponse<T>(
    val status: Int,
    val metaData: ResponseMetaData?,
    var data: T? = null
)