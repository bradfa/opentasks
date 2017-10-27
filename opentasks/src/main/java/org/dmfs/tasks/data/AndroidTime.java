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
import org.dmfs.rfc5545.DateTime;


/**
 * The deprecated {@link Time} class is 'legacy' in the project, this {@link Single} can adapt the replacement {@link DateTime} to it,
 * for places where {@link Time} is still needed in the transition period.
 *
 * @author Gabor Keszthelyi
 */
public final class AndroidTime implements Single<Time>
{
    private final DateTime mDateTime;


    public AndroidTime(DateTime dateTime)
    {
        mDateTime = dateTime;
    }


    @Override
    public Time value()
    {
        Time time = new Time();
        time.set(mDateTime.getTimestamp());
        // TODO all-day, timezone, etc
        return time;
    }
}
