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
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Represents an item in a {@link ViewGroup} that usually have dynamic number of elements.
 * (e.g.: a {@link RecyclerView} or a {@link LinearLayout} populated with multiple {@link View}s.
 *
 * @author Gabor Keszthelyi
 */
public interface ListItem<V extends View>
{
    /**
     * The layout for the View corresponding to this list item. Root of the layout must match &lt;V&gt; type
     */
    @LayoutRes
    int layout();

    /**
     * If applicable, this method binds the data that belongs to this item to the item View, i.e. sets values and registers listeners on the View.
     *
     * @param view
     *         the item view
     */
    void bindDataTo(V view);
}
