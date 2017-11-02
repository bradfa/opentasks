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
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.opentaskspal.readdata.DueTime;
import org.dmfs.opentaskspal.readdata.PercentComplete;
import org.dmfs.opentaskspal.readdata.TaskColor;
import org.dmfs.opentaskspal.readdata.TaskTitle;
import org.dmfs.optional.Optional;
import org.dmfs.rfc5545.DateTime;
import org.dmfs.tasks.R;
import org.dmfs.tasks.contract.TaskContract.Tasks;
import org.dmfs.tasks.databinding.OpentasksViewItemTaskDetailsSubtaskBinding;
import org.dmfs.tasks.utils.DateFormatter;
import org.dmfs.tasks.utils.DateFormatter.DateFormatContext;
import org.dmfs.tasks.utils.SubtaskTaskDetailsUi;


/**
 * {@link View} for showing a subtask on the details screen.
 *
 * @author Gabor Keszthelyi
 */
public final class SubtaskView extends FrameLayout implements SmartView<RowDataSnapshot<Tasks>>
{

    /**
     * The projection required to be able to display this view.
     */
    public static final String[] TASKS_PROJECTION = new String[] {
            Tasks._ID,
            Tasks.TITLE,
            Tasks.TASK_COLOR,
            Tasks.LIST_COLOR,
            Tasks.DUE,
            Tasks.TZ,
            Tasks.IS_ALLDAY,
            Tasks.PERCENT_COMPLETE
    };


    public SubtaskView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    public void update(final RowDataSnapshot<Tasks> subtaskData)
    {
        OpentasksViewItemTaskDetailsSubtaskBinding views = DataBindingUtil.bind(this);

        views.opentasksTaskDetailsSubtaskTitle.setText(
                new TaskTitle(subtaskData).value(getContext().getString(R.string.opentasks_task_details_subtask_untitled)));

        views.getRoot().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new SubtaskTaskDetailsUi(subtaskData).show(v.getContext());
            }
        });

        Optional<DateTime> due = new DueTime(subtaskData);
        if (due.isPresent())
        {
            views.opentasksTaskDetailsSubtaskDue.setText(
                    new DateFormatter(getContext()).format(due.value(), DateTime.now(), DateFormatContext.LIST_VIEW));
        }

        views.opentasksTaskDetailsSubtaskListRibbon.setBackgroundColor(new TaskColor(subtaskData).argb());

        new ProgressBackgroundView(views.opentasksTaskDetailsSubtaskProgressBackground)
                .update(new PercentComplete(subtaskData));
    }
}
