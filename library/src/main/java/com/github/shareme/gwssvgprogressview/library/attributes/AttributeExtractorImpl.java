/*
 * Copyright (C) 2015 Jorge Castillo PÃ©rez
 * Modifications Copyright (C) 2015 Fred Grott(GrottWorkShop)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.shareme.gwssvgprogressview.library.attributes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.github.shareme.gwssvgprogressview.library.R;
import com.github.shareme.gwssvgprogressview.library.clipping.ClippingTransform;
import com.github.shareme.gwssvgprogressview.library.clipping.TransformAbstractFactory;
import com.github.shareme.gwssvgprogressview.library.clipping.TransformFactoryImpl;

import java.lang.ref.WeakReference;

/**
 * AttributteExtractorImpl class
 * Created by fgrott on 8/26/2015.
 */
public class AttributeExtractorImpl implements AttributeExtractor {

    private WeakReference<Context> weakContext;
    private WeakReference<AttributeSet> weakAttrs;
    private WeakReference<TypedArray> weakAttributeArray;
    private TransformAbstractFactory transformFactory;

    private AttributeExtractorImpl(WeakReference<Context> weakContext,
                                   WeakReference<AttributeSet> weakAttrs) {

        this.weakContext = weakContext;
        this.weakAttrs = weakAttrs;
        transformFactory = new TransformFactoryImpl();
    }

    private Context context() {
        return weakContext.get();
    }

    private TypedArray attributeArray() {
        if (weakAttributeArray == null) {
            weakAttributeArray = new WeakReference<>(context().getTheme()
                    .obtainStyledAttributes(weakAttrs.get(), R.styleable.FillableLoader, 0, 0));
        }

        return weakAttributeArray.get();
    }

    @SuppressWarnings("deprecation")
    @Override public int getStrokeColor() {
        //TODO: getColor(int) is depreciated fix
        return attributeArray().getColor(R.styleable.FillableLoader_fl_strokeColor,
                context().getResources().getColor(R.color.strokeColor));
    }

    @SuppressWarnings("deprecation")
    @Override public int getFillColor() {
        //TODO: getColor(int) is depreciated fix
        return attributeArray().getColor(R.styleable.FillableLoader_fl_fillColor,
                context().getResources().getColor(R.color.fillColor));
    }

    @Override public int getStrokeWidth() {
        return attributeArray().getDimensionPixelSize(R.styleable.FillableLoader_fl_strokeWidth, context().
                getResources().getDimensionPixelSize(R.dimen.strokeWidth));
    }

    @Override public int getOriginalWidth() {
        return attributeArray().getInteger(R.styleable.FillableLoader_fl_originalWidth, -1);
    }

    @Override public int getOriginalHeight() {
        return attributeArray().getInteger(R.styleable.FillableLoader_fl_originalHeight, -1);
    }

    @Override public int getStrokeDrawingDuration() {
        return attributeArray().getInteger(R.styleable.FillableLoader_fl_strokeDrawingDuration,
                context().getResources().getInteger(R.integer.strokeDrawingDuration));
    }

    @Override public int getFillDuration() {
        return attributeArray().getInteger(R.styleable.FillableLoader_fl_fillDuration,
                context().getResources().getInteger(R.integer.fillDuration));
    }

    @Override public ClippingTransform getClippingTransform() {
        int value = attributeArray().getInteger(R.styleable.FillableLoader_fl_clippingTransform, 0);
        return transformFactory.getClippingTransformFor(value);
    }

    @Override public void recycleAttributes() {
        if (weakAttributeArray != null) {
            weakAttributeArray.get().recycle();
        }
    }

    @Override public void release() {
        weakAttributeArray = null;
        weakContext = null;
        weakAttrs = null;
    }

    public static class Builder {

        private WeakReference<Context> weakContext;
        private WeakReference<AttributeSet> weakAttrs;

        public Builder with(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null!");
            }
            weakContext = new WeakReference<>(context);
            return this;
        }

        public Builder with(AttributeSet attributeSet) {
            if (attributeSet == null) {
                throw new IllegalArgumentException("Attribute set must nost be null!");
            }
            weakAttrs = new WeakReference<>(attributeSet);
            return this;
        }

        public AttributeExtractorImpl build() {
            return new AttributeExtractorImpl(weakContext, weakAttrs);
        }
    }
}