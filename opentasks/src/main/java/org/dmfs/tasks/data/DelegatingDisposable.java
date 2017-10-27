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

import io.reactivex.disposables.Disposable;


/**
 * @author Gabor Keszthelyi
 */
public abstract class DelegatingDisposable implements Disposable
{
    private final Disposable mDelegate;


    protected DelegatingDisposable(Disposable delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final void dispose()
    {
        mDelegate.dispose();
    }


    @Override
    public final boolean isDisposed()
    {
        return mDelegate.isDisposed();
    }
}