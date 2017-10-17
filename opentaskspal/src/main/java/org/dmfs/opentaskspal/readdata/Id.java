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

package org.dmfs.opentaskspal.readdata;

import android.provider.BaseColumns;

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.android.contentpal.RowSnapshot;
import org.dmfs.jems.single.Single;


/**
 * @author Gabor Keszthelyi
 */
public final class Id implements Single<Long>
{
    private final RowDataSnapshot<?> mRowDataSnapshot;


    public Id(RowDataSnapshot<?> rowDataSnapshot)
    {
        mRowDataSnapshot = rowDataSnapshot;
    }


    public Id(RowSnapshot<?> rowSnapshot)
    {
        this(rowSnapshot.values());
    }


    @Override
    public Long value()
    {
        return Long.valueOf(mRowDataSnapshot.charData(BaseColumns._ID).value().toString());
    }
}
