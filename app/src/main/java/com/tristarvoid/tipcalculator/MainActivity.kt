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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tristarvoid.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                val tipViewModel = TipViewModel()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                                .height(150.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Total per person")
                                Text(text = tipViewModel.returnTotal().toString())
                            }
                        }
                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                Spacer(modifier = Modifier.height(4.dp))
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = if (tipViewModel.billAmount > 0) tipViewModel.billAmount.toString() else "",
                                    onValueChange = {
                                        if (it.isBlank() || it.isEmpty())
                                            tipViewModel.billAmount = 0
                                        try {
                                            tipViewModel.billAmount = it.trim().toInt()
                                        }
                                        catch (_: Exception) {

                                        }
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.rupee),
                                            contentDescription = "Currency icon"
                                        )
                                    },
                                    singleLine = true,
                                    label = { Text(text = "Bill Amount") },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Next
                                    )
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                OutlinedCard {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(text = "Split")
                                        Spacer(modifier = Modifier.width(4.dp))
                                        IconButton(onClick = {
                                            if (tipViewModel.split > 1)
                                                tipViewModel.split--
                                        }) {
                                            Icon(
                                                painter = painterResource(R.drawable.remove),
                                                contentDescription = "Subtract"
                                            )
                                        }
                                        Text(text = tipViewModel.split.toString())
                                        IconButton(onClick = {
                                            if (tipViewModel.split < 100)
                                                tipViewModel.split++
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Add"
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                OutlinedCard {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        tipViewModel.updateTip()
                                        Text(text = "Tip")
                                        Text(text = "₹" + tipViewModel.tip.toString())
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = tipViewModel.percentage.toInt().toString() + " %")
                                    Slider(
                                        value = tipViewModel.percentage,
                                        onValueChange = {
                                            tipViewModel.percentage = it
                                        },
                                        valueRange = 0f..100f
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}