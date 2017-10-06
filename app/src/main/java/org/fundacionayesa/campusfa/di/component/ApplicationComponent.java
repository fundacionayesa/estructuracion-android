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

import org.fundacionayesa.campusfa.api.ShowsApi;
import org.fundacionayesa.campusfa.di.module.ApplicationModule;
import org.fundacionayesa.campusfa.di.module.NetModule;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Componente Dagger de tipo singleton (habrá uno solamente para todas las activities de la aplicación)
 * <p/>
 * que proveerá de elementos comunes independientes al ciclo de vida de las activities.
 */
@Singleton
@Component(modules = {ApplicationModule.class,NetModule.class})
public interface ApplicationComponent {
    Retrofit retrofit();

    ShowsApi showsApi();

    EventBus eventBus();

}