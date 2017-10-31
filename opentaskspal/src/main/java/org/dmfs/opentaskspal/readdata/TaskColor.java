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

import org.dmfs.android.bolts.color.Color;
import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.tasks.contract.TaskContract;


/**
 * {@link Color} for a task. Uses {@link TaskContract.Tasks#TASK_COLOR} if available,
 * fall backs to the mandatory {@link TaskContract.Tasks#LIST_COLOR} if not.
 *
 * @author Gabor Keszthelyi
 */
public final class TaskColor implements Color
{
    private final RowDataSnapshot<TaskContract.Tasks> mRowDataSnapshot;


    public TaskColor(RowDataSnapshot<TaskContract.Tasks> rowDataSnapshot)
    {
        mRowDataSnapshot = rowDataSnapshot;
    }


    @Override
    public int argb()
    {
        CharSequence colorCs = mRowDataSnapshot.charData(TaskContract.Tasks.TASK_COLOR)
                .value(mRowDataSnapshot.charData(TaskContract.Tasks.LIST_COLOR).value());

        return Integer.valueOf(colorCs.toString());
    }
}
