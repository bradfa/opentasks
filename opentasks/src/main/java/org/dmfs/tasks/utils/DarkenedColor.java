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

package org.dmfs.tasks.utils;

import android.graphics.Color;

import org.dmfs.jems.single.Single;


/**
 * {@link Single} to get a darkened version of color, typically to use as status bar color.
 *
 * @author Gabor Keszthelyi
 */
public final class DarkenedColor implements Single<Integer>
{
    private final int mOriginalColor;


    public DarkenedColor(int originalColor)
    {
        mOriginalColor = originalColor;
    }


    @Override
    public Integer value()
    {
        float[] hsv = new float[3];
        Color.colorToHSV(mOriginalColor, hsv);
        hsv[2] = hsv[2] * 0.75f;
        return Color.HSVToColor(hsv);
    }
}
