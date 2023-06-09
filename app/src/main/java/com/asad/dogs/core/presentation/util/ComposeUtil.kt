package com.asad.dogs.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.StateFlow

object ComposeUtil {

    /**
     * This function prevents collecting a flow when the app goes to
     * the background so there will be no UI updates in the background.
     *
     * This will probably lead to a less usage of cpu and battery.
     *
     * @param stateFlow is the flow that is going to be collected
     * @param lifecycle indicates the current lifecycle state
     * @param minActiveState indicates the lifecycle state in which collecting the [stateFlow] must be resumed
     *
     * */
    @Composable
    fun <T> rememberStateWithLifecycle(
        stateFlow: StateFlow<T>,
        lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    ): State<T> {
        val initialValue = remember(stateFlow) { stateFlow.value }
        return produceState(
            key1 = stateFlow,
            key2 = lifecycle,
            key3 = minActiveState,
            initialValue = initialValue,
        ) {
            lifecycle.repeatOnLifecycle(minActiveState) {
                stateFlow.collect {
                    this@produceState.value = it
                }
            }
        }
    }
}
