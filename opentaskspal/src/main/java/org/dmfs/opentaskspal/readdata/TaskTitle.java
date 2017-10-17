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

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.android.contentpal.RowSnapshot;
import org.dmfs.jems.single.Single;
import org.dmfs.tasks.contract.TaskContract.Tasks;


/**
 * @author Gabor Keszthelyi
 */
public final class TaskTitle implements Single<CharSequence>
{
    private final RowDataSnapshot<Tasks> mRowDataSnapshot;


    public TaskTitle(RowDataSnapshot<Tasks> rowDataSnapshot)
    {
        mRowDataSnapshot = rowDataSnapshot;
    }


    public TaskTitle(RowSnapshot<Tasks> rowSnapshot)
    {
        this(rowSnapshot.values());
    }


    @Override
    public CharSequence value()
    {
        // TODO Can a task not have title? If yes, move "Untitled" to string resource.
        return mRowDataSnapshot.charData(Tasks.TITLE).value("Untitled");
    }
}
