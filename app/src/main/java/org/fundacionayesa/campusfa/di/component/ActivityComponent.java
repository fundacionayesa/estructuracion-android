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
package org.fundacionayesa.campusfa.di.component;

import org.fundacionayesa.campusfa.di.module.ActivityModule;
import org.fundacionayesa.campusfa.di.module.UseCaseModule;
import org.fundacionayesa.campusfa.di.scope.ActivityContext;
import org.fundacionayesa.campusfa.presenter.ShowListPresenterImpl;
import org.fundacionayesa.campusfa.view.ShowListActivity;

import dagger.Component;

/**
 * Componente Dagger que servirá para registrar los objetos (activities, presenters etc.)
 * donde luego queramos inyectar dependencias.
 *
 * @ActivityContext indica que el componente solamente existirá en el contexto y ámbito
 * de una Activity, y morirá cuando se destruya. Cada activity, por tanto, tendrá un ActivityComponent
 * diferente e independiente.
 * <p/>
 * <p/>
 * El atributo dependencies establece una dependencia a ApplicationComponent, singleton en la aplicación.
 * <p/>
 * El atributo modules establece la relación del componente con el módulo ActivityModule, quién nos proveerá
 * de las instancias que inyectemos.
 * <p/>
 * <p/>
 * El método inject es el que permitirá a los tipos pasados por parámetros (por ejemplo, ShowListActivity)
 * inyectar dependencias en sus atributos.
 */

@ActivityContext
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class, UseCaseModule.class})
public interface ActivityComponent {
    void inject(ShowListActivity showListActivity);

    void inject(ShowListPresenterImpl presenter);
}
