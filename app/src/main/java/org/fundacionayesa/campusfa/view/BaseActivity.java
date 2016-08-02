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

import org.fundacionayesa.campusfa.TVShowsApplication;
import org.fundacionayesa.campusfa.di.AbstractInjectableActivity;
import org.fundacionayesa.campusfa.di.component.ActivityComponent;
import org.fundacionayesa.campusfa.di.component.DaggerActivityComponent;
import org.fundacionayesa.campusfa.di.module.ActivityModule;

import butterknife.ButterKnife;

/**
 * Activity básica con elementos comunes para la instanciación y ejecución de todas las activities.
 */
public class BaseActivity extends AbstractInjectableActivity<ActivityComponent> {

    /**
     * Método que nos creará los ActivityComponent de cada activity.
     * <p>
     * Se abstrae porque el proceso de construcción es similar para todas las Activities.
     *
     * @return ActivityComponent
     */

    @Override
    protected ActivityComponent createComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(TVShowsApplication.applicationComponent)
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * Método común de inicialización para todas las activities.
     * Indicamos qué layout cargar, e inicializamos el binding de butterknife
     *
     * @param layoutID
     */
    protected void init(int layoutID) {
        setContentView(layoutID);
        //Requerimiento de ButterKnife para ejecutar el binding
        ButterKnife.bind(this);
    }
}
