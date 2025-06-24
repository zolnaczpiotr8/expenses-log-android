package zolnaczpiotr8.com.github.expenses.log.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.CancellationException
import zolnaczpiotr8.com.github.expenses.log.datastore.proto.SettingsProto
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class SettingsSerializer @Inject constructor() : Serializer<SettingsProto> {

    override val defaultValue: SettingsProto
        get() = SettingsProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SettingsProto = try {
        SettingsProto.parseFrom(input)
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (
        exception: InvalidProtocolBufferException,
    ) {
        throw CorruptionException("Cannot read proto.", exception)
    }

    override suspend fun writeTo(
        t: SettingsProto,
        output: OutputStream,
    ) {
        t.writeTo(output)
    }
}
