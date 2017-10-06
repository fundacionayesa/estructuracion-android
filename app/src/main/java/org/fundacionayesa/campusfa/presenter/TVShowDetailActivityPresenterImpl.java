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

import org.fundacionayesa.campusfa.model.event.DetailTVShowReceivedEvent;
import org.fundacionayesa.campusfa.model.event.ShowListReceivedErrorEvent;
import org.fundacionayesa.campusfa.model.vo.DetailTVShow;
import org.fundacionayesa.campusfa.uc.TVShowUC;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class TVShowDetailActivityPresenterImpl implements TVShowDetailActivityPresenter {

    private View view;
    private DetailTVShow detailTVShow;


    @Inject
    TVShowUC tvShowUC;

    @Inject
    EventBus bus;


    @Subscribe
    public void onDetailTVShowReceived(DetailTVShowReceivedEvent event) {
        this.detailTVShow = event.getShow();
        view.renderTVShow(this.detailTVShow);
        view.showLoading(false);
    }

    @Subscribe
    public void onDetailTVShowReceivedError(ShowListReceivedErrorEvent event) {
        view.showErrorGettingDetailTVShow();
        view.showLoading(false);
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public DetailTVShow getTVShow() {
        return detailTVShow;
    }

    @Override
    public void init(long tvShowId) {
        view.showLoading(true);
        tvShowUC.getDetailTVShow(tvShowId);
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
    public void updatePresenterWithTVShow(DetailTVShow detailTvShow) {
        this.detailTVShow = detailTvShow;
        view.renderTVShow(detailTvShow);
    }
}
