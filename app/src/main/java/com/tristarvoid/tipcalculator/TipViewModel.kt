/*
 * Copyright 2023 TriStaRvOiD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tristarvoid.tipcalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TipViewModel: ViewModel() {
    var billAmount by mutableIntStateOf(0)
    var split by mutableIntStateOf(1)
    var tip by mutableIntStateOf(0)
    var percentage by mutableFloatStateOf(0f)

    fun updateTip() {
        tip = ((billAmount * percentage) / 100).toInt()
    }

    fun returnTotal(): Double {
        return tip/split.toDouble()
    }
}