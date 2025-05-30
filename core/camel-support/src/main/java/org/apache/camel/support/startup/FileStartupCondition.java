/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.support.startup;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.StartupCondition;
import org.apache.camel.util.ObjectHelper;

/**
 * Startup condition that waits for a file to exists.
 */
public class FileStartupCondition implements StartupCondition {

    private final File file;

    public FileStartupCondition(String name) {
        ObjectHelper.notNullOrEmpty(name, "File");
        this.file = new File(name);
    }

    @Override
    public String getName() {
        return "File";
    }

    @Override
    public String getWaitMessage() {
        return "Waiting for file: " + file;
    }

    @Override
    public String getFailureMessage() {
        return "File: " + file + " does not exist";
    }

    protected boolean fileExists(File file) {
        return file.exists();
    }

    @Override
    public boolean canContinue(CamelContext camelContext) throws Exception {
        return fileExists(file);
    }

}
