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

package org.dmfs.tasks.data;

import android.text.format.Time;

import org.dmfs.jems.single.Single;


/**
 * {@link Single} for creating a {@link Time} that represents the current time.
 *
 * @author Gabor Keszthelyi
 */
public final class AndroidTimeNow implements Single<Time>
{
    @Override
    public Time value()
    {
        Time now = new Time();
        now.setToNow();

        // TODO What else? Do we need these?:
        // now.clear(TimeZone.getDefault().getID()
        // now.normalize(true)
        return now;
    }
}
