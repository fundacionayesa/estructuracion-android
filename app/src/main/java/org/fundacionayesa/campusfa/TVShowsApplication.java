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
package org.fundacionayesa.campusfa;

import android.app.Application;

import org.fundacionayesa.campusfa.di.component.ApplicationComponent;
import org.fundacionayesa.campusfa.di.component.DaggerApplicationComponent;
import org.fundacionayesa.campusfa.di.module.ApplicationModule;


/**
 * Clase que redefine a Application por defecto.
 *
 * Esta redefinición nos permitirá instanciar nuestro ApplicationComponent nada más iniciar la aplicación
 * ya que, al tener un scope singleton, lo tendremos disponible en todo nuestro código.
 */
public class TVShowsApplication extends Application {

    public static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
