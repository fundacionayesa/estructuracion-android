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

public interface TVShowUC {
    /**
     * Caso de uso: Obtener los TVShows más populares
     * <p>
     * La implementación de este método encapsulará toda la lógica de negocio
     * necesaria para obtener los TVShows más populares.
     * <p>
     * Hará la petición al servidor, procesará la respuesta y colocará en el bus
     * el evento que se comunicará con el presenter.
     */
    void getLatestTVShows();

    /**
     * Caso de uso: Obtener un TVShow a partir de su id
     *
     * La implementación de este método encapsulará toda la lógica de negocio
     * necesaria para obtener un TVShow dado su id.
     *
     * Hará la petición al servidor, procesará la respuesta y colocará en el bus
     * el evento que se comunicará con el presenter.
     */
    void getDetailTVShow(long tvShowId);
}
