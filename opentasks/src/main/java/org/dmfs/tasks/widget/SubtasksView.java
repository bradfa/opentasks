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
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.opentaskspal.readdata.TaskTitle;
import org.dmfs.tasks.R;
import org.dmfs.tasks.contract.TaskContract;
import org.dmfs.tasks.contract.TaskContract.Tasks;
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

        boolean first = true;
        for (final RowDataSnapshot<Tasks> rowDataSnapshot : subtasks)
        {
            TextView itemView = (TextView) inflater.inflate(R.layout.view_task_detail_subtask, null, false);
            itemView.setText(new TaskTitle(rowDataSnapshot).value());

            if (first)
            {
                itemView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_detail_list, 0, 0, 0);
                first = false;
            }
            else
            {
                Drawable originalDrawable = ContextCompat.getDrawable(mHolder.getContext(), R.drawable.ic_detail_list);
                Drawable wrappedDrawable = DrawableCompat.wrap(originalDrawable).mutate();
                DrawableCompat.setTintList(wrappedDrawable, ColorStateList.valueOf(Color.TRANSPARENT));
                itemView.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);
            }

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Context context = v.getContext();
                    context.startActivity(new Intent("android.intent.action.VIEW", new TaskUri(context, rowDataSnapshot).value()));
                }
            });

            mHolder.addView(itemView);
        }
        mHolder.requestLayout();
    }

}
