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

package org.fundacionayesa.campusfa.config;

public interface APIConfig {
    //API Key que tenemos que añadir en cada petición para identificarnos en el servicio
    String API_KEY ="d7f97b07c16cda5293eb7ab97dec92a4";

    //URL base para las peticiones al servicio
    String API_HOST = "http://api.themoviedb.org/3/";

    //Por URL base donde van las imágenes que nos provee la API.
    String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";
}
