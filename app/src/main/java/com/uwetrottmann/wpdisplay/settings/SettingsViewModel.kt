/*
 * Copyright 2018 Uwe Trottmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uwetrottmann.wpdisplay.settings

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.uwetrottmann.wpdisplay.model.DisplayItem
import com.uwetrottmann.wpdisplay.model.DisplayItems

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    val availableItems = MutableLiveData<List<DisplayItem>>()

    init {
        object : AsyncTask<Void?, Void?, List<DisplayItem>>() {
            override fun doInBackground(vararg params: Void?): List<DisplayItem> {
                DisplayItems.readDisabledStateFromPreferences(application)
                return DisplayItems.all // no copy
            }

            override fun onPostExecute(result: List<DisplayItem>) {
                availableItems.value = result
            }

        }.execute()
    }

}