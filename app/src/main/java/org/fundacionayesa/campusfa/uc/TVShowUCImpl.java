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
package org.fundacionayesa.campusfa.uc;

import org.fundacionayesa.campusfa.api.ShowsApi;

public class TVShowUCImpl implements TVShowUC {

    private ShowsApi showsApi;

    public TVShowUCImpl(ShowsApi showsApi) {
        this.showsApi = showsApi;
    }

    @Override
    public void getLatestTVShows() {
        //TODO: lo implementaremos en el siguiente tema
    }
}
