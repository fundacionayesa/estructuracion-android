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

import org.fundacionayesa.campusfa.model.vo.TVShow;

import java.util.ArrayList;
import java.util.List;

public class MockFactory {
    /**
     * @return Listado de 15 TVShow iguales.
     */
    public static List<TVShow> getMockedTVShows() {
        List<TVShow> tvShows = new ArrayList<>();
        TVShow mockTVShow = new TVShow("Campus Fundacion Ayesa", "https://cursos.fundacionayesa.org/android/campus_fa_android.png");

        for (int i = 0; i < 15; i++) {
            tvShows.add(mockTVShow);
        }

        return tvShows;
    }
}
