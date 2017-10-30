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

import org.dmfs.provider.tasks.AuthorityUtil;
import org.dmfs.tasks.contract.TaskContract;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;


/**
 * {@link SingleSource} for accessing a {@link ContentProviderClient} for the given {@link Uri}.
 * Takes care of closing the client upon disposal.
 *
 * @author Gabor Keszthelyi
 */
public class ContentProviderClientSource implements SingleSource<ContentProviderClient>
{
    private final Context mContext;
    private final Uri mUri;


    public ContentProviderClientSource(Context context, Uri uri)
    {
        mContext = context;
        mUri = uri;
    }


    public ContentProviderClientSource(Context context)
    {
        this(context, TaskContract.getContentUri(AuthorityUtil.taskAuthority(context)));
    }


    @Override
    public void subscribe(@NonNull SingleObserver<? super ContentProviderClient> observer)
    {
        Single.create(new SingleOnSubscribe<ContentProviderClient>()
        {
            @Override
            public void subscribe(@NonNull SingleEmitter<ContentProviderClient> emitter) throws Exception
            {
                ContentProviderClient client = mContext.getContentResolver().acquireContentProviderClient(mUri);
                emitter.setDisposable(new ContentProviderClientDisposable(client));
                emitter.onSuccess(client);
            }
        }).subscribe(observer);
    }

}
