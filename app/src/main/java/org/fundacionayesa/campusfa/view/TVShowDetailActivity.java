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

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.fundacionayesa.campusfa.R;
import org.fundacionayesa.campusfa.config.APIConfig;
import org.fundacionayesa.campusfa.di.component.ActivityComponent;
import org.fundacionayesa.campusfa.model.vo.DetailTVShow;
import org.fundacionayesa.campusfa.presenter.TVShowDetailActivityPresenter;
import org.fundacionayesa.campusfa.presenter.TVShowDetailActivityPresenterImpl;
import org.fundacionayesa.campusfa.utils.Navigator;

import javax.inject.Inject;

import butterknife.BindView;

public class TVShowDetailActivity extends BaseActivity implements TVShowDetailActivityPresenter.View, Palette.PaletteAsyncListener {

    @BindView(R.id.container)
    View container;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_image)
    ImageView tvShowImage;

    @BindView(R.id.loading)
    ProgressBar loading;

    @BindView(R.id.content)
    View content;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.homepage_label)
    TextView homepageLabel;

    @BindView(R.id.homepage)
    TextView homepage;

    @BindView(R.id.overview_label)
    TextView overviewLabel;

    @BindView(R.id.overview)
    TextView overview;


    @Inject
    TVShowDetailActivityPresenter presenter;

    @Override
    public void onComponentCreated(@NonNull final ActivityComponent component) {
        super.onComponentCreated(component);
        component.inject(this);
    }

    @Override
    public void onPostComponentCreated() {

        //Inicialización de la vista del presenter.
        getComponent().inject((TVShowDetailActivityPresenterImpl) this.presenter);
        this.presenter.setView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init(R.layout.detail_tv_show_layout);

        if (savedInstanceState == null) {
            //El id será pasado por Bundle cuando lleguemos a esta activity, y dependerá
            //del TVShow pulsado.
            long tvShowId = getIntent().getExtras().getLong(Navigator.TV_SHOW_DETAIL_KEY);
            presenter.init(tvShowId);
        }
    }

    @Override
    public void renderTVShow(DetailTVShow tvShow) {
        setToolbar(APIConfig.BASE_IMAGE_URL + tvShow.getFeaturedImage());
        setContent(tvShow);
    }

    @Override
    public void showLoading(boolean visible) {
        //Mostramos el contenido si no se está cargando, y viceversa
        loading.setVisibility(visible ? View.VISIBLE : View.GONE);
        content.setVisibility(!visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorGettingDetailTVShow() {
        Toast.makeText(this, R.string.error_getting_detail_show, Toast.LENGTH_SHORT).show();
    }

    protected static final String TV_SHOW_KEY = "TV_SHOW_KEY";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TV_SHOW_KEY, presenter.getTVShow());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            DetailTVShow detailTvShow = (DetailTVShow) savedInstanceState.getSerializable(TV_SHOW_KEY);
            presenter.updatePresenterWithTVShow(detailTvShow);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        this.presenter.onStop();
    }

    /**
     * Solamente con fines estéticos. Dará un estilo a la activity acorde con los tonos de la imagen.
     * @param palette
     */

    @Override
    public void onGenerated(Palette palette) {
        if (palette != null) {

            Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
            Palette.Swatch lightMutedSwatch = palette.getDarkMutedSwatch();


            final Palette.Swatch lightSwatch = lightVibrantSwatch != null? lightVibrantSwatch:lightMutedSwatch;

            //BackgroundColor
            if (lightSwatch != null) {
                container.setBackgroundColor(lightSwatch.getRgb());

                title.setTextColor(lightSwatch.getRgb());
                title.setBackgroundColor(lightSwatch.getTitleTextColor());
                homepage.setTextColor(lightSwatch.getBodyTextColor());
                overview.setTextColor(lightSwatch.getBodyTextColor());

                homepageLabel.setTextColor(lightSwatch.getTitleTextColor());
                overviewLabel.setTextColor(lightSwatch.getTitleTextColor());
            }
        }
    }

    private void setToolbar(String urlImage) {
        toolbar.setTitle("");
        //Solamente con fines estéticos
        //Listener para capturar el evento de carga de la imagen.
        //Una vez cargada, se saca la paleta de colores de la imagen
        //para aplicarle el estilo acorde con los tonos que contiene.
        //Esto se hará de manera asíncrona en el método onGenerate
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (bitmap != null && (bitmap.getHeight() > 0 || bitmap.getWidth() > 0)) {
                    tvShowImage.setImageBitmap(bitmap);
                    Palette.Builder builder = new Palette.Builder(bitmap);
                    builder.generate(TVShowDetailActivity.this);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Picasso.with(this).load(urlImage).into(target);
        setSupportActionBar(toolbar);
    }

    private void setContent(DetailTVShow detailTVShow) {
        title.setText(detailTVShow.getName());
        homepage.setText(detailTVShow.getHomepage());
        overview.setText(detailTVShow.getOverview());
    }
}
