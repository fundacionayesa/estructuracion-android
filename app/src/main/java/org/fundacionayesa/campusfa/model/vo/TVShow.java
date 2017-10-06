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
package org.fundacionayesa.campusfa.model.vo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TVShow implements Serializable {


    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String featuredImage;

    public TVShow() {
    }

    public TVShow(String name, String featuredImage) {
        this.featuredImage = featuredImage;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    @Override
    public String toString() {
        return getName() + " -> " + getFeaturedImage();
    }

}
