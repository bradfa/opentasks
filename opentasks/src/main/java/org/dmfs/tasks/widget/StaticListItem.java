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
import android.view.View;


/**
 * {@link ListItem} that simply points to a layout with static content.
 *
 * @author Gabor Keszthelyi
 */
public final class StaticListItem implements ListItem<View>
{
    private final int mLayout;


    public StaticListItem(@LayoutRes int layout)
    {
        mLayout = layout;
    }


    @Override
    public int layout()
    {
        return mLayout;
    }


    @Override
    public void bindDataTo(View view)
    {

    }
}
