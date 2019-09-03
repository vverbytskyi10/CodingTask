package com.vverbytskyi.codingtask.testutils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withTimeout
import org.assertj.core.api.Assertions

private const val DEFAULT_TIMEOUT = 3000L

/**
 * Represents a list of capture values from a LiveData.
 *
 * This class is not threadsafe and must be used from the main thread.
 */
class LiveDataValueCapture<T> {

    private val _values = mutableListOf<T?>()
    private val values: List<T?>
        get() = _values

    private val channel = Channel<T?>(Channel.UNLIMITED)

    @Synchronized
    fun addValue(value: T?) {
        _values += value
        channel.offer(value)
    }

    suspend fun assertSendsValues(vararg expected: T?, timeout: Long = DEFAULT_TIMEOUT) {
        val expectedList = expected.asList()
        if (values == expectedList) {
            return
        }
        try {
            withTimeout(timeout) {
                for (value in channel) {
                    if (values == expectedList) {
                        return@withTimeout
                    }
                }
            }
        } catch (ex: TimeoutCancellationException) {
            Assertions
                .assertThat(values)
                .`as`("actual data or sequence of data from LiveData object is equal to expectedList data/sequence of data")
                .isEqualTo(expectedList)
        }
    }
}

/**
 * Extension function to capture all values that are emitted to a LiveData<T> during the execution of
 * `captureBlock`.
 *
 * @param block a lambda that will
 */
inline fun <T> LiveData<T>.captureValues(block: LiveDataValueCapture<T>.() -> Unit) {
    val capture = LiveDataValueCapture<T>()
    val observer = Observer<T> {
        capture.addValue(it)
    }
    observeForever(observer)
    capture.block()
    removeObserver(observer)
}
