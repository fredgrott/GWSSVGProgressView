/*
 * Copyright (C) 2015 Jorge Castillo PÃ©rez
 * Modifications Copyright (C) 2015 Fred Grott(GrottWorkShop)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.shareme.gwssvgprogressview.library;

/**
 * Lifecycle states for {@link FillableLoader}.
 * Created by fgrott on 8/26/2015.
 */
public class State {
    public static final int NOT_STARTED = 0;
    public static final int TRACE_STARTED = 1;
    public static final int FILL_STARTED = 2;
    public static final int FINISHED = 3;
}