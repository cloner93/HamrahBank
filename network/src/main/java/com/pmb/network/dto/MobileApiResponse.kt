import android.annotation.SuppressLint
import com.pmb.network.dto.ResponseMetaData
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MobileApiResponse<T>(
    val status: Int = 0,
    val metaData: ResponseMetaData?,
    var data: T? = null
)