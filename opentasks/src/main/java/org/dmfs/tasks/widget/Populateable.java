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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A {@link ViewGroup} decorator {@link SmartView} that can add child views
 * for the {@link ListItem}s provided in {@link SmartView#update(Object)}.
 *
 * @author Gabor Keszthelyi
 */
public final class Populateable implements SmartView<Iterable<ListItem>>
{
    private final ViewGroup mViewGroup;


    public Populateable(ViewGroup viewGroup)
    {
        mViewGroup = viewGroup;
    }


    @Override
    public void update(Iterable<ListItem> listItems)
    {
        LayoutInflater inflater = LayoutInflater.from(mViewGroup.getContext());
        for (ListItem listItem : listItems)
        {
            View view = inflater.inflate(listItem.layout(), null, false);
            //noinspection unchecked
            listItem.bindDataTo(view);
            mViewGroup.addView(view);
        }
    }
}
