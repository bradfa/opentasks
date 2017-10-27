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
import org.dmfs.iterators.Function;
import org.dmfs.optional.decorators.DelegatingOptional;
import org.dmfs.optional.decorators.Mapped;
import org.dmfs.tasks.contract.TaskContract.Tasks;


/**
 * @author Gabor Keszthelyi
 */
public final class PercentComplete extends DelegatingOptional<Integer>
{
    public PercentComplete(RowDataSnapshot<Tasks> task)
    {
        super(new Mapped<>(new Function<CharSequence, Integer>()
        {
            @Override
            public Integer apply(CharSequence charSequence)
            {
                // TODO Extract these kind of functions?
                return Integer.valueOf(charSequence.toString());
            }
        }, task.charData(Tasks.PERCENT_COMPLETE)));
    }
}
