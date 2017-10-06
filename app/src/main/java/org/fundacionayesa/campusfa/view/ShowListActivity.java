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
package org.fundacionayesa.campusfa.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.fundacionayesa.campusfa.R;
import org.fundacionayesa.campusfa.di.component.ActivityComponent;
import org.fundacionayesa.campusfa.model.vo.TVShow;
import org.fundacionayesa.campusfa.presenter.ShowListPresenter;
import org.fundacionayesa.campusfa.presenter.ShowListPresenterImpl;
import org.fundacionayesa.campusfa.utils.RecyclerInsetsDecoration;
import org.fundacionayesa.campusfa.view.adapter.ShowListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ShowListActivity extends BaseActivity implements ShowListPresenter.View {


    //Binding de las variables presentes en el layout XML.
    //Para ello hemos utilizado ButterKnife, para evitar código "boilerplate"
    @BindView(R.id.tv_shows_toolbar)
    Toolbar toolbarView;
    @BindView(R.id.tv_shows)
    RecyclerView tvShowsView;

    @BindView(R.id.loading)
    ProgressBar loading;

    /**
     * Inyectamos nuestro presenter con Dagger.
     */
    @Inject
    ShowListPresenter presenter;


    @Override
    public void onComponentCreated(@NonNull final ActivityComponent component) {
        super.onComponentCreated(component);

        //Registramos esta activity para poder realizar inyecciones
        component.inject(this);
    }

    @Override
    public void onPostComponentCreated() {

        //Inicialización de la vista del presenter.
        this.presenter.setView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init(R.layout.show_list_layout);

        //Zona de inicialización
        initializeToolbar();
        initializeRecycler();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.init();
    }

    private void initializeToolbar() {
        this.toolbarView.setTitle("");
        setSupportActionBar(toolbarView);
    }

    private void initializeRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.columns));
        this.tvShowsView.setLayoutManager(gridLayoutManager);
        this.tvShowsView.addItemDecoration(new RecyclerInsetsDecoration(this));
    }

    @Override
    public void populateTVShows(List<TVShow> tvShows) {
        this.tvShowsView.setAdapter(new ShowListAdapter(this, tvShows));
    }

    @Override
    public void showLoading(boolean visible) {
        loading.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
