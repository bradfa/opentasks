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

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.opentaskspal.readdata.DueTime;
import org.dmfs.opentaskspal.readdata.TaskTitle;
import org.dmfs.optional.Optional;
import org.dmfs.rfc5545.DateTime;
import org.dmfs.tasks.R;
import org.dmfs.tasks.contract.TaskContract;
import org.dmfs.tasks.contract.TaskContract.Tasks;
import org.dmfs.tasks.databinding.OtViewTaskDetailSubtaskBinding;
import org.dmfs.tasks.utils.DateFormatter;
import org.dmfs.tasks.utils.DateFormatter.DateFormatContext;
import org.dmfs.tasks.utils.TaskUri;


/**
 * @author Gabor Keszthelyi
 */
public final class SubtasksView implements SmartView<Iterable<RowDataSnapshot<Tasks>>>
{

    private final ViewGroup mHolder;


    public SubtasksView(ViewGroup holder)
    {
        mHolder = holder;
    }


    @Override
    public void update(Iterable<RowDataSnapshot<TaskContract.Tasks>> subtasks)
    {
        if (!subtasks.iterator().hasNext())
        {
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(mHolder.getContext());

        for (final RowDataSnapshot<Tasks> subtask : subtasks)
        {
            OtViewTaskDetailSubtaskBinding views = DataBindingUtil.inflate(inflater, R.layout.ot_view_task_detail_subtask, null, false);
            views.otTaskTitle.setText(new TaskTitle(subtask).value());
            views.getRoot().setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Context context = v.getContext();
                    context.startActivity(new Intent("android.intent.action.VIEW", new TaskUri(context, subtask).value()));
                }
            });

            Optional<DateTime> due = new DueTime(subtask).value();
            if (due.isPresent())
            {
                // TODO Review this (depends on whether we use Time or DateTime):
                Time dueTime = new Time();
                dueTime.set(due.value().getTimestamp());

                Time now = new Time();
                now.setToNow();

                views.otTaskDueDate.setText(new DateFormatter(mHolder.getContext()).format(dueTime, now, DateFormatContext.LIST_VIEW));

            }

            mHolder.addView(views.getRoot());
        }
        mHolder.requestLayout();
    }

}
