/*
 * Copyright (c) 2023 Gary Deken
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cyl.framwork;

/**
 * Title: Updatable
 *
 * <p>
 * Description: </p>
 *
 * Copyright (c) Feb 17, 2023
 * @author Gary Deken
 * @version 0.5
 */
public interface Updatable {

   /**
    * This method takes the elapse time between rendered frames.
    *
    * @param deltaTime float
    */
   void update(float deltaTime);
}
