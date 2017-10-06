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

package org.fundacionayesa.campusfa.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.fundacionayesa.campusfa.R;
import org.fundacionayesa.campusfa.config.APIConfig;
import org.fundacionayesa.campusfa.model.vo.TVShow;

import java.util.List;

public class ShowListAdapter extends RecyclerView.Adapter<TVShowViewHolder> {

    private Context context;
    private List<TVShow> tvShows;

    public ShowListAdapter(Context context, List<TVShow> tvShows) {
        this.context = context;
        this.tvShows = tvShows;
    }

    @Override
    public TVShowViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View rowView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tv_show_item, viewGroup, false);

        return new TVShowViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final TVShowViewHolder holder, final int position) {

        //Obtenemos el TVShow y rellenamos su celda dentro de nuestro grid.
        final TVShow currentTVShow = tvShows.get(position);

        holder.titleTextView.setText(currentTVShow.getName());

        //Utilizamos Picasso para cargar la imagen en la ImageView a partir de la URL.
        //Recomendamos encarecidamente el uso de esta librería puesto que contiene automatización de cachés, placeholders etc.
        Picasso.with(context).load(APIConfig.BASE_IMAGE_URL + currentTVShow.getFeaturedImage()).fit().centerInside().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }
}

class TVShowViewHolder extends RecyclerView.ViewHolder {

    View container;
    TextView titleTextView;
    ImageView imageView;

    public TVShowViewHolder(View itemView) {

        super(itemView);
        this.container = itemView.findViewById(R.id.container);
        this.titleTextView = (TextView) itemView.findViewById(R.id.tv_show_name);
        this.imageView = (ImageView) itemView.findViewById(R.id.tv_show_image);
    }
}


