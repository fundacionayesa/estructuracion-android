/*
 *  Copyright (c) 2016. Fundación Ayesa
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  @author Juan Aguilar
 */
package org.fundacionayesa.campusfa.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.fundacionayesa.campusfa.di.scope.ActivityContext;
import org.fundacionayesa.campusfa.view.TVShowDetailActivity;

public class Navigator {
    public static final String TV_SHOW_DETAIL_KEY = "TV_SHOW_DETAIL_KEY";

    public void goTVShowDetail(@ActivityContext Context context, long tvShowId) {
        Bundle extras = new Bundle();
        extras.putLong(TV_SHOW_DETAIL_KEY, tvShowId);
        Intent intent = new Intent(context, TVShowDetailActivity.class);
        intent.putExtras(extras);
        context.startActivity(intent);

    }
}
