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

import android.util.Log;

import org.fundacionayesa.campusfa.api.ShowsApi;
import org.fundacionayesa.campusfa.model.dto.TVShowDTO;
import org.fundacionayesa.campusfa.model.event.DetailTVShowReceivedErrorEvent;
import org.fundacionayesa.campusfa.model.event.DetailTVShowReceivedEvent;
import org.fundacionayesa.campusfa.model.event.ShowListReceivedErrorEvent;
import org.fundacionayesa.campusfa.model.event.ShowListReceivedEvent;
import org.fundacionayesa.campusfa.model.vo.DetailTVShow;
import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowUCImpl implements TVShowUC {

    private ShowsApi showsApi;
    private EventBus bus;

    public TVShowUCImpl(EventBus bus, ShowsApi showsApi) {
        this.showsApi = showsApi;
        this.bus = bus;
    }

    @Override
    public void getLatestTVShows() {
        showsApi.getLatestTVShows().enqueue(new Callback<TVShowDTO>() {
            @Override
            public void onResponse(Call<TVShowDTO> call, Response<TVShowDTO> response) {
                if (response.isSuccessful())
                    bus.post(new ShowListReceivedEvent(response.body().getResults()));
                else
                    bus.post(new ShowListReceivedErrorEvent());
            }

            @Override
            public void onFailure(Call<TVShowDTO> call, Throwable t) {
                bus.post(new ShowListReceivedErrorEvent());
            }
        });
    }

    @Override
    public void getDetailTVShow(long tvShowId) {
        showsApi.getTVShowDetails(tvShowId).enqueue(new Callback<DetailTVShow>() {
            @Override
            public void onResponse(Call<DetailTVShow> call, Response<DetailTVShow> response) {
                if (response.isSuccessful())
                    bus.post(new DetailTVShowReceivedEvent(response.body()));
                else
                    bus.post(new DetailTVShowReceivedErrorEvent());
            }

            @Override
            public void onFailure(Call<DetailTVShow> call, Throwable t) {
                bus.post(new DetailTVShowReceivedErrorEvent());
            }
        });
    }
}
