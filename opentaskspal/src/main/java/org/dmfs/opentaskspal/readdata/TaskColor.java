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
import org.dmfs.jems.single.Single;
import org.dmfs.tasks.contract.TaskContract;


/**
 * @author Gabor Keszthelyi
 */
// TODO Is it optional?
// TODO Use Color from Bolts lib
public final class TaskColor implements Single<Integer>
{
    private final RowDataSnapshot<TaskContract.Tasks> mRowDataSnapshot;


    public TaskColor(RowDataSnapshot<TaskContract.Tasks> rowDataSnapshot)
    {
        mRowDataSnapshot = rowDataSnapshot;
    }


    @Override
    public Integer value()
    {
        // TODO Is it correct?
        return Integer.valueOf(mRowDataSnapshot.charData(TaskContract.Tasks.TASK_COLOR)
                .value(mRowDataSnapshot.charData(TaskContract.Tasks.LIST_COLOR).value()).toString());
    }
}
