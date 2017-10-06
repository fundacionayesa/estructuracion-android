package org.fundacionayesa.campusfa.di;

import android.support.annotation.NonNull;


/**
 * HasComponent
 * by Sven Jacobs at: https://github.com/svenjacobs/dagger2-android-template
 */
public interface HasComponent<C> {

    /**
     * @return Component provided by this class.
     */
    @NonNull
    C getComponent();
}