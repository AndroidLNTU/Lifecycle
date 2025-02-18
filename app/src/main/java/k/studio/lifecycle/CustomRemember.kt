package k.studio.lifecycle

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlin.reflect.KProperty

private var state: MutableState<String?> = mutableStateOf(null)

class CustomRememberText(val getInitValue: () -> State<String>) {

    operator fun getValue(nothing: Nothing?, property: KProperty<*>): Any {
        if (state.value == null) {
            state.value = getInitValue().value
        }
        return state.value!!//.apply { "CustomRememberText getValue $this".logD() }
    }

    operator fun setValue(nothing: Nothing?, property: KProperty<*>, newValue: Any) {
        //"CustomRememberText setValue ${property.name} $newValue".logD()
        state.value = newValue as String
    }
}