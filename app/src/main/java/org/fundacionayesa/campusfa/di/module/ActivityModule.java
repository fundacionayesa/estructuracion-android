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
package org.fundacionayesa.campusfa.di.module;

import android.app.Activity;
import android.content.Context;

import org.fundacionayesa.campusfa.di.scope.ActivityContext;
import org.fundacionayesa.campusfa.presenter.ShowListPresenter;
import org.fundacionayesa.campusfa.presenter.ShowListPresenterImpl;

import dagger.Module;
import dagger.Provides;


/**
 * Módulo Dagger que nos proveerá de las dependencias que vayan a inyectarse en nuestras
 * activities, presenters etc.
 *
 */

@Module
public final class ActivityModule {

    /**
     * Activity actual.
     * La anotación @ActivityContext determina que este módulo dependerá del ciclo de vida de la activity
     * por lo que cada una de las activities tendrá una instancia diferente de este módulo para inyectar.
     */
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Permite inyectar la activity actual, en la zona de atributos, para cualquier instancia que se
     * haya registrado en el componente.
     *
     * Ej.
     *
     * @Inject
     * Context context;
     *
     * @return Activity actual
     */
    @ActivityContext
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    /**
     * Permite inyectar una instancia nueva de ShowListPresenter.
     * En este caso, la implementación es de ShowListPresenterImpl
     *
     * Ej.
     *
     * @Inject
     * ShowListPresenter presenter;
     *
     * @return Nueva instancia de ShowListPresenter
     */
    @ActivityContext
    @Provides
    ShowListPresenter provideShowListPresenter() {
        return new ShowListPresenterImpl();
    }
}
