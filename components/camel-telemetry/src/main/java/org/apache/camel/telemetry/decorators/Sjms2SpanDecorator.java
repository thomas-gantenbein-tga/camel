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
package org.apache.camel.telemetry.decorators;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;

public class Sjms2SpanDecorator extends AbstractMessagingSpanDecorator {

    @Override
    public String getComponent() {
        return "sjms2";
    }

    @Override
    protected String getDestination(Exchange exchange, Endpoint endpoint) {
        // when using toD for dynamic destination then extract from header
        String destination = exchange.getMessage().getHeader("CamelJMSDestinationName", String.class);
        if (destination == null) {
            destination = super.getDestination(exchange, endpoint);
        }
        return destination;
    }

    @Override
    public String getComponentClassName() {
        return "org.apache.camel.component.sjms2.Sjms2Component";
    }

}
