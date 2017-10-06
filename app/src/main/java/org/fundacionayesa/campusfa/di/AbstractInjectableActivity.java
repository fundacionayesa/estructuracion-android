package org.fundacionayesa.campusfa.di;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * AbstractInjectableActivity
 * by Sven Jacobs at: https://github.com/svenjacobs/dagger2-android-template
 */
public abstract class AbstractInjectableActivity<C> extends AppCompatActivity
        implements HasComponent<C>, ComponentLifecycle<C> {


    private C mComponent;


    @NonNull
    @Override
    public C getComponent() {
        return mComponent;
    }


    @Override
    public void onComponentCreated(@NonNull final C component) {
    }


    @Override
    public void onPostComponentCreated() {
    }


    /**
     * Creates component instance.
     */
    protected abstract C createComponent();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // Deliberately create the component before the super() call so that it's initialized in
        // Fragment's onAttach()!
        mComponent = createComponent();


        if (mComponent == null) {
            throw new NullPointerException("Component must not be null");
        }


        super.onCreate(savedInstanceState);


        onComponentCreated(mComponent);
        onPostComponentCreated();
    }
}