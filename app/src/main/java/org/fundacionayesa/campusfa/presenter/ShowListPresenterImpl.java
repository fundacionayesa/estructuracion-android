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

import android.content.Context;

import org.fundacionayesa.campusfa.model.event.ShowListReceivedErrorEvent;
import org.fundacionayesa.campusfa.model.event.ShowListReceivedEvent;
import org.fundacionayesa.campusfa.model.vo.TVShow;
import org.fundacionayesa.campusfa.uc.TVShowUC;
import org.fundacionayesa.campusfa.utils.Navigator;
import org.fundacionayesa.campusfa.view.listener.TVShowClickedListener;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShowListPresenterImpl implements ShowListPresenter, TVShowClickedListener {

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

    @Inject
    TVShowUC tvShowUC;

    @Inject
    EventBus bus;

    @Inject
    Navigator navigator;

    /**
     * Contexto inyectado, del tipo Activity
     */
    @Inject
    Context context;


    /**
     * Método que se ejecutará siempre que alguien ponga en el bus un evento
     * de tipo ShowListReceivedEvent y el presenter se encuentre registrado en el bus.
     *
     * @param event
     */
    @Subscribe
    public void onTVShowsReceived(ShowListReceivedEvent event) {
        this.tvShows = event.getShows();
        view.populateTVShows(this.tvShows);
        view.showLoading(false);
    }

    /**
     * Método que se ejecutará siempre que alguien ponga en el bus un evento
     * de tipo ShowListReceivedErrorEvent y el presenter se encuentre registrado en el bus.
     *
     * @param event
     */

    @Subscribe
    public void onTVShowsReceivedError(ShowListReceivedErrorEvent event) {
        view.showErrorGettingShows();
        view.showLoading(false);
    }


    @Override
    public void init() {
        view.showLoading(true);
        tvShowUC.getLatestTVShows();
    }

    @Override
    public void setView(View view) {
        //Inicializamos la vista al crear una instancia del presenter
        this.view = view;
    }

    @Override
    public List<TVShow> getTVShows() {
        return this.tvShows;
    }


    @Override
    public void onStart() {
        //Registramos la instancia presenter en el bus para que pueda
        //escuchar los eventos
        bus.register(this);
    }

    @Override
    public void onStop() {
        //Como el presenter se ha parado, ya no queremos que siga escuchando
        //eventos para evitar efectos no deseados, y lo borramos del registro.
        bus.unregister(this);
    }

    @Override
    public void restorePresenterWithSavedStatus(ArrayList<TVShow> tvShows) {
        if (this.tvShows != null) {
            this.tvShows = tvShows;
            view.populateTVShows(this.tvShows);
        }
    }

    @Override
    public void tvShowClicked(TVShow tvShow) {
        //Mostramos la activity de detalle.
        navigator.goTVShowDetail(context, tvShow.getId());
    }
}
