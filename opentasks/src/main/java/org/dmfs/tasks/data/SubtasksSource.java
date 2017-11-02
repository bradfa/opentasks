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

package org.dmfs.tasks.data;

import android.content.Context;
import android.net.Uri;

import org.dmfs.android.contentpal.RowDataSnapshot;
import org.dmfs.tasks.contract.TaskContract;
import org.dmfs.tasks.contract.TaskContract.Tasks;
import org.dmfs.tasks.widget.SubtaskView;

import io.reactivex.Single;
import io.reactivex.SingleObserver;


/**
 * {@link Single} for getting the given tasks's subtasks as {@link RowDataSnapshot}s.
 *
 * @author Gabor Keszthelyi
 */
public final class SubtasksSource extends Single<Iterable<RowDataSnapshot<TaskContract.Tasks>>>
{
    private final Context mAppContext;
    private final Uri mTaskUri;
    private final String[] mProjection;


    public SubtasksSource(Context appContext, Uri taskUri, String... projection)
    {
        mAppContext = appContext;
        mTaskUri = taskUri;
        mProjection = projection;
    }


    public SubtasksSource(Context appContext, Uri taskUri)
    {
        this(appContext, taskUri, SubtaskView.TASKS_PROJECTION);
    }


    @Override
    protected void subscribeActual(SingleObserver<? super Iterable<RowDataSnapshot<Tasks>>> observer)
    {
        new Offloading<>(
                new CpQuerySource<>(mAppContext, new SubtasksCpQuery(mTaskUri, mProjection))
        ).subscribe(observer);
    }
}
