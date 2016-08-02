package org.fundacionayesa.campusfa.di;

import android.support.annotation.NonNull;

/**
 * ComponentLifecycle
 * by Sven Jacobs at: https://github.com/svenjacobs/dagger2-android-template
 */
public interface ComponentLifecycle<C> {

    /**
     * Este método será llamado cuando el componente se cree.
     * <p>
     * En la implementación de este método haremos la inyección del objeto ej: {@code
     * component.inject(this)}.
     * <p>
     * La configuración de los objetos inyectados se hará en {@link #onPostComponentCreated()}
     *
     * @param component Component utilizado para inyectar dependencias
     * @see #onPostComponentCreated()
     */
    void onComponentCreated(@NonNull final C component);

    /**
     * Será llamado después de {@link #onComponentCreated(Object)}.
     * <p>
     * Este método deberá ser implementado/sobrescrito cuando haya dependencias que necesiten algún
     * tipo de configuración.
     * <p>
     * Especialmente útil en clases base (que pueden ser abstractas) donde la inyeccción no se hace realmente.
     * *
     *
     * @see #onComponentCreated(Object)
     */
    void onPostComponentCreated();
}