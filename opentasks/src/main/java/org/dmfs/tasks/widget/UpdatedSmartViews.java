/*
 * Copyright 2017 dmfs GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.tasks.widget;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import org.dmfs.iterables.decorators.DelegatingIterable;
import org.dmfs.iterables.decorators.Mapped;
import org.dmfs.iterators.Function;


/**
 * {@link Iterable} of {@link View}s that are inflated from the given layout id and updated with the given data.
 * The root of the layout has to be a {@link SmartView} with the matching <code>D</code> data type.
 *
 * @author Gabor Keszthelyi
 */
public final class UpdatedSmartViews<D, V extends View & SmartView<D>> extends DelegatingIterable<V>
{
    public UpdatedSmartViews(Iterable<D> data, final LayoutInflater inflater, @LayoutRes final int layout)
    {
        super(new Mapped<>(data, new Function<D, V>()
        {
            @Override
            public V apply(D data)
            {
                //noinspection unchecked
                V view = (V) inflater.inflate(layout, null);
                view.update(data);
                return view;
            }
        }));
    }
}
