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

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;

import org.dmfs.android.contentpal.RowSet;
import org.dmfs.opentaskspal.rowsets.Subtasks;
import org.dmfs.tasks.contract.TaskContract;

import static org.dmfs.provider.tasks.AuthorityUtil.taskAuthority;


/**
 * @author Gabor Keszthelyi
 */
public final class SubtasksCpQuery implements CpQuery<TaskContract.Tasks>
{
    private final Uri mTaskUri;
    private final String[] mProjection;


    public SubtasksCpQuery(Uri taskUri, String... projection)
    {
        mTaskUri = taskUri;
        mProjection = projection;
    }


    @Override
    public RowSet<TaskContract.Tasks> query(ContentProviderClient client, Context appContext)
    {
        return new Subtasks(taskAuthority(appContext), client, mTaskUri, mProjection);
    }
}
