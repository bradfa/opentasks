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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.opentaskspal.readdata.DueTime;
import org.dmfs.opentaskspal.readdata.PercentComplete;
import org.dmfs.opentaskspal.readdata.TaskTitle;
import org.dmfs.optional.Optional;
import org.dmfs.rfc5545.DateTime;
import org.dmfs.tasks.R;
import org.dmfs.tasks.contract.TaskContract;
import org.dmfs.tasks.data.AndroidTime;
import org.dmfs.tasks.data.AndroidTimeNow;
import org.dmfs.tasks.databinding.OpentasksViewItemTaskDetailsSubtaskBinding;
import org.dmfs.tasks.utils.DateFormatter;
import org.dmfs.tasks.utils.TaskUri;


/**
 * {@link View} for showing a subtask on the details screen.
 *
 * @author Gabor Keszthelyi
 */
public final class SubtaskView extends FrameLayout implements SmartView<RowDataSnapshot<TaskContract.Tasks>>
{
    public SubtaskView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    public void update(final RowDataSnapshot<TaskContract.Tasks> subtaskData)
    {
        OpentasksViewItemTaskDetailsSubtaskBinding views = DataBindingUtil.bind(this);

        CharSequence title = new TaskTitle(subtaskData).value(getContext().getString(R.string.opentasks_task_details_subtask_untitled));
        views.opentasksTaskDetailsSubtaskTitle.setText(title);

        views.getRoot().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = v.getContext();
                context.startActivity(new Intent("android.intent.action.VIEW", new TaskUri(context, subtaskData).value()));
            }
        });

        Optional<DateTime> due = new DueTime(subtaskData).value();
        if (due.isPresent())
        {
            String formattedDue = new DateFormatter(getContext()).format(
                    new AndroidTime(due.value()).value(),
                    new AndroidTimeNow().value(),
                    DateFormatter.DateFormatContext.LIST_VIEW);
            views.opentasksTaskDetailsSubtaskDue.setText(formattedDue);
        }

        new ProgressBackgroundView(views.opentasksTaskDetailsSubtaskProgressBackground)
                .update(new PercentComplete(subtaskData));
    }
}
