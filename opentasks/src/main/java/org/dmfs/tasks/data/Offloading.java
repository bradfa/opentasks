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

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


/**
 * {@link SingleSource} decorator/adapter that sets the threading so that the work is on {@link Schedulers#io()}
 * and delivery is on UI thread.
 *
 * @author Gabor Keszthelyi
 */
// TODO Okay to use rx.Single and rx.SingleSource 'interchangeably' while decorating, delegating?
public final class Offloading<T> extends Single<T>
{
    private final SingleSource<T> mDelegate;


    public Offloading(SingleSource<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    protected void subscribeActual(@NonNull SingleObserver<? super T> observer)
    {
        Single.wrap(mDelegate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
