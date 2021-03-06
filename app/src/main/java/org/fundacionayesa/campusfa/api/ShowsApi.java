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
package org.fundacionayesa.campusfa.api;

import org.fundacionayesa.campusfa.model.dto.TVShowDTO;
import org.fundacionayesa.campusfa.model.vo.DetailTVShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interfaz para la implementación de Retrofit.
 * <p>
 * Ej: Al llamar al método getLatestTVShows, se ejecutará una petición
 * de tipo GET, al endpoint BASE_URL+"tv/popular.
 */

public interface ShowsApi {

    @GET("tv/popular")
    Call<TVShowDTO> getLatestTVShows();

    @GET("tv/{show_id}")
    Call<DetailTVShow> getTVShowDetails(@Path("show_id") long tvShowId);

}
