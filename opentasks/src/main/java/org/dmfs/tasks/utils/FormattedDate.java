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

package org.dmfs.tasks.utils;

import android.content.Context;

import org.dmfs.jems.single.Single;
import org.dmfs.rfc5545.DateTime;
import org.dmfs.tasks.data.AndroidTime;
import org.dmfs.tasks.data.AndroidTimeNow;


/**
 * {@link Single} for a formatted date {@link CharSequence}.
 *
 * @author Gabor Keszthelyi
 */
public final class FormattedDate implements Single<CharSequence>
{
    private final Context mContext;
    private final DateTime mDateTime;
    private final DateFormatter.DateFormatContext mDateFormatContext;


    public FormattedDate(Context context, DateTime dateTime, DateFormatter.DateFormatContext dateFormatContext)
    {
        mContext = context;
        mDateTime = dateTime;
        mDateFormatContext = dateFormatContext;
    }


    @Override
    public CharSequence value()
    {
        return new DateFormatter(mContext)
                .format(new AndroidTime(mDateTime).value(), new AndroidTimeNow().value(), mDateFormatContext);
    }
}
