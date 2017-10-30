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

import android.view.View;


/**
 * {@link ListItem} base class that delegates to another {@link ListItem}.
 *
 * @author Gabor Keszthelyi
 */
public abstract class DelegatingListItem<V extends View> implements ListItem<V>
{
    private final ListItem<V> mDelegate;


    public DelegatingListItem(ListItem<V> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final int layout()
    {
        return mDelegate.layout();
    }


    @Override
    public final void bindDataTo(V view)
    {
        mDelegate.bindDataTo(view);
    }
}
