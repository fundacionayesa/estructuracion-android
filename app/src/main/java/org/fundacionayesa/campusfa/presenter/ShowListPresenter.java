/*
 *  Copyright (c) 2016. Licensed under the Apache License, Version 2.0 (the "License");
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

package org.fundacionayesa.campusfa.presenter;

import org.fundacionayesa.campusfa.model.vo.TVShow;

import java.util.ArrayList;
import java.util.List;

public interface ShowListPresenter {

    /**
     * Método que se ejecuta para iniciar el presenter
     */
    void init();

    /**
     * Método para establecer la vista del presenter.
     * En vez de asignarla por constructor, la configuramos a través del método set
     * después de haber inyectado el presenter en nuestra activity
     * @param view
     */
    void setView(View view);


    /**
     * @return Listado de TVShows para mostrar
     */
    List<TVShow> getTVShows();

    /**
     * Método que ejecutaremos en el onStart de la activity para adaptar el presenter
     * su ciclo de vida
     */
    void onStart();

    /**
     * Método que ejecutaremos en el onStop de la activity para adaptar el presenter
     * su ciclo de vida
     */
    void onStop();

    /**
     * Método para restablecer el estado del presenter.
     * Como decisión de diseño, tenemos el listado de TVShows en el presenter.
     * Podríamos tenerlo en la activity, pero el presenter es mucho más adecuado,
     * entre otras cosas, por razones de testeabilidad.
     * @param tvShows
     */
    void restorePresenterWithSavedStatus(ArrayList<TVShow> tvShows);


    /**
     * Interfaz que implementa la activity, lo que le da la capacidad de comportarse como una vista del presenter.
     * De este modo, el desacoplamiento es total, puesto que solamente dependemos de esta interfaz y no de la implementación
     * de la activity
     */
    interface View {
        void populateTVShows(List<TVShow> tvShows);

        void showLoading(boolean visible);

        void showErrorGettingShows();
    }
}
