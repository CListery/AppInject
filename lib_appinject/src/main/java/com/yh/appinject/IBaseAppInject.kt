/*
 * Copyright 2019 FangStar, Inc.
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

package com.yh.appinject

import android.app.Application
import androidx.annotation.DrawableRes

/**
 * Created by CYH on 2019-08-01 16:30
 */
interface IBaseAppInject {
    
    fun getApplication(): Application
    fun showTipMsg(errorMsg: String)
    @DrawableRes
    fun getNotificationIcon(): Int
}