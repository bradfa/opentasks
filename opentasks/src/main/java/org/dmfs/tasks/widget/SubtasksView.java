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

import android.view.ViewGroup;

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.iterables.ArrayIterable;
import org.dmfs.iterables.decorators.Flattened;
import org.dmfs.iterables.decorators.Mapped;
import org.dmfs.iterators.Function;
import org.dmfs.tasks.R;
import org.dmfs.tasks.contract.TaskContract.Tasks;


/**
 * {@link SmartView} for the subtasks section of the task details screen.
 *
 * @author Gabor Keszthelyi
 */
public final class SubtasksView implements SmartView<Iterable<RowDataSnapshot<Tasks>>>
{
    private final ViewGroup mContentView;


    public SubtasksView(ViewGroup contentView)
    {
        mContentView = contentView;
    }


    @Override
    public void update(Iterable<RowDataSnapshot<Tasks>> subtasks)
    {
        new Populateable(mContentView).update(

                new Flattened<>(

                        new ArrayIterable<ListItem>(
                                new StaticListItem(R.layout.opentasks_view_item_divider),
                                new StaticListItem(R.layout.opentasks_view_item_task_details_subtitles_section_header)
                        ),

                        new Mapped<>(subtasks, new Function<RowDataSnapshot<Tasks>, ListItem>()
                        {
                            @Override
                            public ListItem apply(RowDataSnapshot<Tasks> subtaskData)
                            {
                                return new SubtaskListItem(subtaskData);
                            }
                        })
                )
        );
    }
}
