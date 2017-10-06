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
package org.fundacionayesa.campusfa.presenter;

import org.fundacionayesa.campusfa.model.vo.TVShow;
import org.fundacionayesa.campusfa.utils.MockFactory;

import java.util.ArrayList;
import java.util.List;

public class ShowListPresenterImpl implements ShowListPresenter {

    /**
     * Vista que maneja el presenter.
     */
    private View view;

    /**
     * Listado de elementos que se mostrarán en la vista
     * El presenter es el encargado de mantener esta lista puesto que la Activity tiene, como
     * única responsabilidad, mostrar los elementos.
     */
    private List<TVShow> tvShows;

    @Override
    public void init() {
        view.showLoading(true);
        this.tvShows = MockFactory.getMockedTVShows();
        view.populateTVShows(this.tvShows);
        view.showLoading(false);
    }

    @Override
    public void setView(View view) {
        //Inicializamos la vista al crear una instancia del presenter
        this.view = view;
    }
}