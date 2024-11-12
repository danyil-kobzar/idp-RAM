package ua.polodarb.ram.data.preference.impl.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import ua.polodarb.ram.data.preference.model.CellsCountProtoModel
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class CellsCountSerializer @Inject constructor() : Serializer<CellsCountProtoModel> {

    /**
     * Reads [StartConfigureProtoModel] from the provided input stream.
     *
     * @param input The input stream to read from.
     * @return The deserialized [StartConfigureProtoModel].
     * @throws CorruptionException If the input stream cannot be read.
     */
    override suspend fun readFrom(input: InputStream): CellsCountProtoModel {
        try {
            return CellsCountProtoModel.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    /**
     * Writes the provided [StartConfigureProtoModel] to the output stream.
     *
     * @param t The [StartConfigureProtoModel] to write.
     * @param output The output stream to write to.
     */
    override suspend fun writeTo(t: CellsCountProtoModel, output: OutputStream) {
        t.writeTo(output)
    }

    /**
     * The default instance of [StartConfigureProtoModel].
     */
    override val defaultValue: CellsCountProtoModel = CellsCountProtoModel.getDefaultInstance()
}
