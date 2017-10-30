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

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.tasks.contract.TaskContract;
import org.dmfs.tasks.data.TaskUri;


/**
 * {@link TaskDetailsUi} for showing the task details for a subtask.
 *
 * @author Gabor Keszthelyi
 */
public final class SubtaskTaskDetailsUi implements TaskDetailsUi
{
    private final RowDataSnapshot<TaskContract.Tasks> mSubtaskData;


    public SubtaskTaskDetailsUi(RowDataSnapshot<TaskContract.Tasks> subtaskData)
    {
        mSubtaskData = subtaskData;
    }


    @Override
    public void show(Context context)
    {
        new BasicTaskDetailsUi(new TaskUri(context, mSubtaskData).value()).show(context);
    }
}
