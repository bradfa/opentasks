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

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.tasks.R;
import org.dmfs.tasks.contract.TaskContract;


/**
 * {@link ListItem} for {@link SubtaskView}.
 *
 * @author Gabor Keszthelyi
 */
public final class SubtaskListItem extends DelegatingListItem<SubtaskView>
{
    public SubtaskListItem(RowDataSnapshot<TaskContract.Tasks> subtaskData)
    {
        super(new SmartListItem<SubtaskView, RowDataSnapshot<TaskContract.Tasks>>(R.layout.opentasks_view_item_task_detail_subtask, subtaskData));
    }
}
